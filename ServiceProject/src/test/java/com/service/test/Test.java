package com.service.test;

import com.service.bean.Student;
import com.service.dao.StudentDAO;
import com.service.factory.MyBeansFactory;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Date date = new Date(new java.util.Date().getTime());
//		System.out.println(date);
//		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String curTime=format.format(date);
//		
//		System.out.println(curTime);
//		System.out.println(2/5.0*100+"%");
//		Map map = new HashMap();
//		int i =1;
//		String s = i/0.05 +"%";
		StudentDAO sDao = (StudentDAO)MyBeansFactory.getBeans("studentdao");
		Student s = sDao.getStuByStu_email("1112@qq.com");
		System.out.println(s.getStu_nikename());
	}

}
