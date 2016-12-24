package com.recruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.pojo.Message;
import com.recruit.pojo.Message2student;
import com.recruit.pojo.Student;
import com.recruit.service.M2sService;
import com.recruit.service.MessageService;
import com.recruit.service.StudentService;
import com.recruit.service.TeacherService;

@Controller
@RequestMapping("/student")
public class StudentSide {
	
	@Resource  
    private TeacherService tDAO;
	@Resource  
    private MessageService messageDAO;
	@Resource  
    private M2sService message2studentDAO;
	@Resource  
    private StudentService studentDAO;
	
	@RequestMapping("/login")
	public String login(HttpServletResponse response, HttpSession session, String studentId, String password,
			Model model) throws IOException {
		Student student = studentDAO.check(Integer.parseInt(studentId), password);
		PrintWriter out = response.getWriter();
		if (student == null) {
			out.print("<script language='javascript'>alert('用户名或密码错误!!!');window.location='../slogin.jsp';</script>");
			return null;
		}
		session.setAttribute("studentId", studentId);
		return "index.do";
	}

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpSession session, Model model) {
		List<Message> list = messageDAO.selectAll();
		// System.out.println("-----------------------------");
		// System.out.println(list.size());
		model.addAttribute("list", list);
		int count = 10;
		if (request.getParameter("count") != null) {
			// 确保count为进一的整十
			count = (Integer.parseInt(request.getParameter("count").trim()) + 9) / 10 * 10;
			String type = request.getParameter("type");
			System.out.println(type);
			System.out.println(("1".equals(type)));
			if ("-1".equals(type))
				count = count - 10;
			if ("1".equals(type))
				count = count + 10;
		}
		if (count >= list.size()) {
			count = list.size();
			model.addAttribute("full", -1);
		}
		System.out.println(count);
		model.addAttribute("count", count);
		return "sgetNotices.jsp";
	}

	@RequestMapping("/getSelf")
	public String getSelf(HttpServletRequest request, HttpSession session, Model model) {
		String studentId = (String) session.getAttribute("studentId");
		// System.out.println(studentId);
		List<Message> list = studentDAO.getAllMessage(Integer.parseInt(studentId));
		model.addAttribute("list", list);
		int count = 10;
		if (request.getParameter("count") != null) {
			// 确保count为进一的整十
			count = (Integer.parseInt(request.getParameter("count").trim()) + 9) / 10 * 10;
			String type = request.getParameter("type");
			System.out.println(type);
			System.out.println(("1".equals(type)));
			if ("-1".equals(type))
				count = count - 10;
			if ("1".equals(type))
				count = count + 10;
		}
		if (count >= list.size()) {
			count = list.size();
			model.addAttribute("full", -1);
		}
		System.out.println(count);
		model.addAttribute("count", count);
		return "sgetSelfInformation.jsp";
	}

	@RequestMapping("/godetails")
	public String godetails(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws Exception {
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		Message message = messageDAO.getMessageById(messageId);
		int studentId = Integer.parseInt((String) session.getAttribute("studentId"));
		Message2student message2student = message2studentDAO.getMessage2studentById(messageId, studentId);
		if (message.getIsPrivate() == 1 && message2student == null) {
			PrintWriter out = response.getWriter();
			out.print("<script language='javascript'>alert('您没有访问权限！');window.location='index.do';</script>");
			out.flush();
			out.close();
			return null;
		}
		if (message2student != null && (message2student.getIsAcceptInterview() == -1
				&& message2student.getIsAcceptOffer() == -1 && message2student.getIsAcceptTest() == -1)) {
			model.addAttribute("has", 1);
		}
		List<Message2student> list = message2studentDAO.getAllStudent(messageId);
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "sinterviewInformation.jsp";
	}

	@RequestMapping("/accept")
	public String accept(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws Exception {
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		Message message = messageDAO.getMessageById(messageId);
		int studentId = Integer.parseInt((String) session.getAttribute("studentId"));
		if ("笔试通知".equals(message.getType().trim())) {
			message2studentDAO.acceptTest(messageId, studentId, 0);
		} else if ("面试通知".equals(message.getType().trim())) {
			message2studentDAO.acceptInterview(messageId, studentId, 0);
		} else {
			message2studentDAO.acceptOffer(messageId, studentId, 0);
		}
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>alert('您已成功接受此通知，请做好准备！');window.location='getSelf.do';</script>");
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping("/refuse")
	public String refuse(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws Exception {
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		Message message = messageDAO.getMessageById(messageId);
		int studentId = Integer.parseInt((String) session.getAttribute("studentId"));
		if ("笔试通知".equals(message.getType().trim())) {
			message2studentDAO.acceptTest(messageId, studentId, 1);
		} else if ("面试通知".equals(message.getType().trim())) {
			message2studentDAO.acceptInterview(messageId, studentId, 1);
		} else {
			message2studentDAO.acceptOffer(messageId, studentId, 1);
		}
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>alert('您已成功拒绝此通知！');window.location='getSelf.do';</script>");
		out.flush();
		out.close();
		return null;
	}

}
