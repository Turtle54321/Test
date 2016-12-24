package com.recruit.controller;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.exportExcel.OfferOwnerService;
import com.recruit.pojo.Message2student;
import com.recruit.service.M2sService;

@Controller
@RequestMapping("/teacher")
public class OfferSum {

	@Resource  
    private M2sService m2sDAO;
	
	@RequestMapping("/offerowner")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String sheetName = "";
		List<Message2student> infoViewList = null;
		if ("-1".equals(type)) {
			infoViewList = m2sDAO.getOfferOwner();
			response.setHeader("Content-disposition", "attachment;filename=AllOfferOwner.xls");
			sheetName = "AllOfferOwner";
		} else if ("0".equals(type)) {
			infoViewList = m2sDAO.getOfferOwnerBreak();
			response.setHeader("Content-disposition", "attachment;filename=OfferOwnerBreak.xls");
			sheetName = "OfferOwnerBreak";
		} else if ("1".equals(type)) {
			infoViewList = m2sDAO.getOfferOwnerNotBreak();
			response.setHeader("Content-disposition", "attachment;filename=AllOfferOwnerNotBreak.xls");
			sheetName = "OfferOwnerNotBreak";
		} else {
			return;
		}

		HSSFWorkbook wb = OfferOwnerService.export(infoViewList, sheetName);
		response.setContentType("application/vnd.ms-excel");

		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}
