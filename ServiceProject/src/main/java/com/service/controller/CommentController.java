package com.service.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.bean.Comment;
import com.service.bean.Course;
import com.service.bean.Student;
import com.service.dao.CommentDAO;
import com.service.dao.StudentDAO;
import com.service.factory.MyBeansFactory;

@Controller
@RequestMapping("comment")
public class CommentController {
	@RequestMapping("test")
	public String test(HttpSession session){
		//模拟进入评价模块的条件
		Student student = new Student();
		student.setStu_email("2365393243@qq.com");
		session.setAttribute("student", student);
		Course course = new Course();
		course.setCou_id("123");
		session.setAttribute("course", course);
		return "commentView.do";
	}

	@RequestMapping("writeComment")
	public String writeComment(int score, String content,HttpSession session, HttpServletResponse response,
			Model model) {
		CommentDAO commentDao = (CommentDAO) MyBeansFactory.getBeans("commentdao");
		Comment comment = new Comment();
		Date date = new Date(new java.util.Date().getTime());
		comment.setCom_date(date);
		// 从session获取当前的学生类
		Student student = (Student) session.getAttribute("student");
		comment.setStu_email(student.getStu_email());
		// 从session中获取当前的课程类
		Course course = (Course) session.getAttribute("course");
		comment.setCou_id(course.getCou_id());
		System.out.println("!!!");
		System.out.println(score);
		comment.setCom_star(score);
		comment.setCom_text(content);
		int result = commentDao.insert(comment);
		if (result == 0) {
			// 插入失败
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("<script language='javascript'>alert('评论失败');window.location='writeComment.jsp';</script>");
				return null;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("getWriter失败！");
				return "writeComment.jsp";
			}
		}
		
		return "commentView.do";
	}

	@RequestMapping("commentView")
	public String commentView(HttpServletRequest request,HttpSession session,Model model) {
		StudentDAO studentDao = (StudentDAO)MyBeansFactory.getBeans("studentdao");
		CommentDAO commentDao = (CommentDAO)MyBeansFactory.getBeans("commentdao");
		Course course = (Course) session.getAttribute("course");
		String cou_id = course.getCou_id();
		System.out.println("!!!!!!");
		System.out.println(cou_id);
		List<Comment> commentList = commentDao.selectByCou_id(cou_id);
		int sizeList = commentList.size();
		
		//计算每个评分的百分比
		Map map = commentDao.getNumForEachScore(cou_id);
		for (int i = 0; i < 5; i++) {
			String key = "score"+(i+1);
			long num = (Long) map.get(key);
			String score = num/(sizeList/100.0)+"";
			int point = score.indexOf(".");
			model.addAttribute(key, score.substring(0, point+2)+"%");
		}
		Float avescore = commentDao.getAveScoreByCou_id(cou_id);
		System.out.println(avescore);
		
		//实现翻页功能
		int count = 5;
		if (request.getParameter("count") != null) {
			System.out.println(request.getParameter("count") + "!!!");
			// 确保count为进一的整五
			count = (Integer.parseInt(request.getParameter("count").trim()) + 4) / 5 * 5;
			String type = request.getParameter("type");
			System.out.println(type);
			System.out.println(("1".equals(type)));
			if ("-1".equals(type))
				count = count - 5;
			if ("1".equals(type))
				count = count + 5;
		}
		if (count >= sizeList) {
			count = sizeList;
			model.addAttribute("full", -1);
		}
		
		for(int i= count-5>0? count-5:0 ;i<count;i++){
			Comment comment = commentList.get(i);
			Student student = studentDao.getStuByStu_email(comment.getStu_email());
			String key = "name"+i;
			System.out.println(comment.getStu_email());
			System.out.println(student.getStu_nikename());
			model.addAttribute(key, student.getStu_nikename());
		}
		System.out.println(count);
		model.addAttribute("count", count);
		model.addAttribute("avescore", avescore);
		model.addAttribute("clist", commentList);
		
//		List<String> nameList= new ArrayList<String>();
//		for(Comment comment:commentList){
//			
//		}
		return "commentView.jsp";
		
	}
	
	@RequestMapping("changePage")
	public String changePage(int type, HttpServletRequest request, Model model){
		
		return "commentView.jsp";
	}
} 
