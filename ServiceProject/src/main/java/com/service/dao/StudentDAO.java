package com.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.service.bean.Student;

public interface StudentDAO {
	@Select("select * from student where stu_phone=#{stu_phone}")
	public Student getStuByStu_phone(int stu_phone);
	
	@Select("select * from student where stu_phone=#{0} and stu_password=#{1}")
	public Student getStuByPhoAndPas(int stu_phone, String stu_password);
	
	@Select("select * from student")
	public List<Student> getAllStu();
	
	@Insert("insert into student values(#{stu_phone}, #{stu_nickname}, #{stu_password}, #{stu_sex}, #{stu_name}, #{stu_level}, #{stu_inter)")
	public void insertStu(Student student);
	
	@Delete("delete from student where stu_phone=#{stu_phone}")
	public void deleteStuByStu_phone(int stu_phone);
	
}
