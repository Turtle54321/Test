<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程评价</title>
</head>
<link rel="stylesheet" href="../commentView_files/css/main.css">
<link rel="stylesheet"
	href="../commentView_files/css/comment.css">
<style>

#writecomment {
	width: 60%;
	height: 500px;
	margin: 0 0 0 20%;
	background-color: #fff;
}

#center { 
	margin-left: 20%;
	background-color: #fff;
}

textarea:hover {
	border-color: #f90;
}
</style>
<body>

	<div id="content">
		<div id="body">
		<br><br>
			<div id="writecomment">
				<div id="center">
					<div class="teacher-content-header teacher-comment-header">
						<h1>课程评价</h1>
					</div>
					<form action="writeComment.do" method="post">
						<ul>
							<li><a href="#star1" class="star" id="star_0"></a></li>
							<li><a href="#star2" class="star" id="star_1"></a></li>
							<li><a href="#star3" class="star" id="star_2"></a></li>
							<li><a href="#star4" class="star" id="star_3"></a></li>
							<li><a href="#star5" class="star" id="star_4"></a></li>
						</ul>
						<br>
						<input id="score" type="hidden" name="score" />
						<textarea id="comment" rows="5" cols="60" name = "content"></textarea>
						<br> <input class="btn btn-primary" type="submit"
							style="margin-left: 60%" name="提交评价" />
					</form>
				</div>


			</div>

		</div>
	</div>
</body>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var click_num = -1; //记录点击的那个等级
		var disppear_num = -1; //为了显示当鼠标划过的时候可显示哪些等级
		var num;
		$(".star").hover(function(e) {
			e.preventDefault();
			/* 鼠标滑过是的效果*/
			var starId = $(this).attr("id");
			num = $(this).parent().index("li"); //记录是哪一个等级
			clearStar(); //先置空
			showStar(num); //根据等级来高亮显示
		}, function(e) {
			e.preventDefault();
			/*鼠标离开时的效果*/
			disppear_num = click_num;
			//当disppear_num==-1时，说明没有选择任何等级
			if (disppear_num == -1) {
				clearStar();
			} else {
				clearStar();
				// 否则有等级显示
				for (var i = 0; i <= disppear_num; i++) {
					$("#star_" + i).css("background", "url(../commentView_files/image/star.png) 0 -28px");
				}
			}
		}).click(function(e) {
			e.preventDefault();
			click_num = $(this).parent().index("li");
			document.getElementById("score").value = click_num;
			clearStar();
			for (var i = 0; i <= click_num; i++) {
				$("#star_" + i).css("background", "url(../commentView_files/image/star.png) 0 -28px");
			}
		});

	});
	function clearStar() {
		for (var i = 0; i <= 4; i++) {
			$("#star_" + i).css("background", "url(../commentView_files/image/star.png)");
		}
	}
	function showStar(starId) {

		for (var i = 0; i <= starId; i++) {
			$("#star_" + i).css("background", "url(../commentView_files/image/star.png) 0 -28px");

		}
	}
</script>
<style type="text/css">
ul li {
	float: left;
	list-style: none;
	margin-right: 10px;
}

.star {
	cursor: pointer;
	background: url(../commentView_files/image/starview.png);
	width: 19px;
	height: 19px;
	border: 0px solid red;
	float: left;
}
</style>
</html>