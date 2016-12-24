package com.recruit.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.pojo.Message;
import com.recruit.pojo.Message2student;
import com.recruit.service.MessageService;

@Controller
public class Test {
	@Resource
	private MessageService messageService;
	@RequestMapping("test")
	public String test(HttpServletRequest request,Model model){
		System.out.println("!!!!!!!!!!");
		int id = Integer.parseInt(request.getParameter("id"));
		Message message = new Message();
		message.setTitle("啦啦啦");
		int row = messageService.insertSelective(message);
		System.out.println(row);
		System.out.println("!!!!!!!!!!");
		System.out.println(message.getMessageId());
//		List<Message> messagelist = messageService.selectAll();
//		for(Message message : messagelist){
//			System.out.println(message.getTitle());
//		}
		model.addAttribute("message",message);
		return "Test.jsp";
	}
}
