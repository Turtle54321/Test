package com.recruit.controller;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.exportExcel.StudentExportService;
import com.recruit.pojo.Message2student;
import com.recruit.service.M2sService;


@Controller
@RequestMapping("/teacher")
public class StudentExportController {
	
	@Resource  
    private M2sService m2sDAO;
	
	@RequestMapping(value = "/export")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int messageId = Integer.parseInt(request.getParameter("messageId"));

//		MessageDAO mDAO = (MessageDAO) MyBeansFactory.getBeans("messagedao");
//		Message message = (Message) mDAO.getMessageById(messageId);

		List<Message2student> infoViewList = m2sDAO.getAllStudent(messageId);

		HSSFWorkbook wb = StudentExportService.export(infoViewList);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=student.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}