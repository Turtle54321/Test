package com.service.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.service.bean.Comment;

public interface CommentDAO {
	//与数据库的交互可参考StudentDAO类
	@Insert("insert into comment values  (#{stu_email},#{ cou_id},#{ com_text},#{ com_date},#{com_star})")
	public int insert(Comment comment);
	
	@Select("select * from comment where stu_email = #{0}")
	public List<Comment> selectByStu_email(String stu_email);
	
	@Select("select * from comment where cou_id = #{0}")
	public List<Comment> selectByCou_id(String cou_id);
	
	@Select("select * from comment")
	public List<Comment> selectAll();
	
	@Select("select sum(com_star)/count(com_star) from comment where cou_id=#{0}")
	public float getAveScoreByCou_id(String cou_id);
	
	@Select("select "
			+"(select count(*) from comment where com_star = 5 and cou_id=#{0}) as score5,"
			+"(select count(*) from comment where com_star = 4 and cou_id=#{0}) as score4,"
			+"(select count(*) from comment where com_star = 3 and cou_id=#{0}) as score3,"
			+"(select count(*) from comment where com_star = 2 and cou_id=#{0}) as score2,"
			+"(select count(*) from comment where com_star = 1 and cou_id=#{0}) as score1")
	public Map getNumForEachScore(String cou_id);
	
}
