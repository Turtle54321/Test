<%@page import="com.service.bean.Comment"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.ibatis.scripting.xmltags.ForEachSqlNode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价列表</title>
<link rel="stylesheet" href="../commentView_files/css/main.css">
<link rel="stylesheet" href="../commentView_files/css/comment.css">
<style>
.bg {
	display: inline-block;
	width: 93px;
	height: 16px;
	background: url(../commentView_files/image/bg.png);
}

.starview {
	display: inline-block;
	left: 0;
	top: 0;
	background: url(../commentView_files/image/starview.png);
	width: 0;
	height: 16px;
}
</style>
</head>
<body>
	<div id="content">
		<div class="body">
			<div class="teacher-content">
				<div class="teacher-comment">
					<div class="teacher-content-header teacher-comment-header">
						<h1>课程评价</h1>
					</div>
					<div class="teacher-comment-content">
						<div id="teacher-comment" class="show-teacher-comment">
							<div class="overview">
								<div class="action">
									<button class="btn btn-primary" id="comment-more"
										onclick="window.location='writeComment.jsp'">写评价</button>
								</div>
								<div class="score">
								<% 
            						float avescore = (Float)request.getAttribute("avescore");
            						String ave = avescore + "";
            						String wid = avescore/0.05+"%";
            						%>
									<strong><%=ave.substring(0, 3) %></strong> <span class="bg"> <span
										class="starview" id="star"></span>
									</span>
									<script>
            						var star = document.getElementById("star");
            						
            						
            						star.style.width = "<%=wid%>";
        							</script>
								</div>
								<div class="detail single">
									<div class="star-comment">主动评价星级分布</div>
									<ul>
										<li><span class="text">5星</span>
											<div class="progress primary">
												<div class="meter" style="width: ${score5}"></div>
											</div> <span style="margin-left: 3px"> ${score5} </span></li>
										<li><span class="text">4星</span>
											<div class="progress primary">
												<div class="meter" style="width: ${score4}"></div>
											</div> <span style="margin-left: 3px"> ${score4} </span></li>
										<li><span class="text">3星</span>
											<div class="progress primary">
												<div class="meter" style="width: ${score3}"></div>
											</div> <span style="margin-left: 3px"> ${score3}</span></li>
										<li><span class="text">2星</span>
											<div class="progress primary">
												<div class="meter" style="width: ${score2}"></div>
											</div> <span style="margin-left: 3px"> ${score2} </span></li>
										<li><span class="text">1星</span>
											<div class="progress primary">
												<div class="meter" style="width: ${score1}"></div>
											</div> <span style="margin-left: 3px"> ${score1} </span></li>
									</ul>
								</div>
							</div>
							<div class="tab-nav-info">
								<div class="top-nav">
									<div class="dropdown sort-select">
										<button class="btn-default">
											<i class="caret"></i><span>推荐排序</span>
										</button>
										<ul class="dropdown-menu">
											<li data-value="display_order" class="active">推荐排序</li>
											<li data-value="create_time">最近评价</li>
										</ul>
										<input type="hidden" name="sort" value="display_order">
									</div>
									<b class="item nav-active" data-type=""><span
										data-comment="0" data-type="1" id="all"><a>全部评价</a></span></b><b
										class="item" data-type=""><span data-comment="1"
										data-type="2"><a>1对1评价</a></span></b><b class="item" data-type=""><span
										data-comment="3" data-type="3"><a>班课评价</a></span></b><b class="item"
										data-type=""><span data-comment="4" data-type="4"><a>视频课评价</a></span></b>
								</div>
							</div>
							<div class="nav-content">
								<div class="list">
								<%
								List<Comment> cList = (List<Comment>)request.getAttribute("clist");
								int count = (Integer)request.getAttribute("count");
								for(int i= count-5>0? count-5:0 ;i<count;i++) {
									Comment comment = cList.get(i);
									String name = (String)request.getAttribute("name"+i);
								%>
									<div class="item">
										<div class="content"><%=name %> </div>
										<div class="comment">
											<div class="score-info">
												<span class="bg"> <span class="starview" id='<%= "star"+i%>'></span>
												</span>
												<script>
            						var star = document.getElementById('<%= "star"+i%>');
            						<% wid = comment.getCom_star()/0.05+"%";%>
            						star.style.width = "<%=wid%>";
        							</script>
											</div>
											<div class="content"><%=comment.getCom_text()%></div>
											<div class="order-info">
												<span></span><span></span><span><span class="date"><%=comment.getCom_date() %></span>
											</div>
										</div>
									</div>
									<%} %>
									
								</div>
								<div class="pager-block">
									<div class="pager">
									<c:if test="${full != -1}">
										<a href="commentView.do?count=${count}&type=1"
											data-page="1" rel="nofollow"> 下一页</a>
									</c:if>
									<c:if test="${count>5}">
										<a href="commentView.do?count=${count}&type=-1"
											data-page="3" rel="nofollow">上一页 </a>
									</c:if>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="teacher-sidebar">
				<div class="teacher-recommend-course">
					<div class="teacher-content-header teacher-comment-header">
						<h1>课程推荐</h1>
					</div>
					<div class="teacher-course-content" style="height: 500px;"></div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>