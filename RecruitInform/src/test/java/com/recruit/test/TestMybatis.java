package com.recruit.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.recruit.pojo.Student;
import com.recruit.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
  
public class TestMybatis {  
    private static Logger logger = Logger.getLogger(TestMybatis.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private StudentService studentService = null;  
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
//    	List<Message> mList = studentService.getAllMessage(201456789);
//    	Message message = mList.get(0);
    	
//         System.out.println(user.getUserName());  
//         logger.info("值："+user.getUserName());  
    	Student student = studentService.check(201456789, "123");
        logger.info(JSON.toJSONString(student));  
    }  
}  