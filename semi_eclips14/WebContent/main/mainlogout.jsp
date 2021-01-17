<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>

.content{

 position: absolute;

 top:50%;

 left:50%;

 width: 1500px;

 padding: 20px;

 transform: translate(-50%, -50%);                                                                   

 color: white;

 z-index: 2;

 text-align: center;

 background-color: #ffffff;

 opacity: 0.5;

}

 

#qqq{

	color: black;

}

</style>
<style type="text/css">
	<%@include file="../css/default.css"%>
</style>
</head>
<body>
	
	<%@include file="../nav/navlogout.jsp"%>
	<div class="parallax">
	</div>
			<div class="content">

			<h1>아토피디아에 오신것을 환영합니다.</h1>

			<br>
			<br>

			<h3 id="qqq"><a href="../membercontroller.do?command=signupform" style="color: blue;">회원가입</a> 또는 <a href="../membercontroller.do?command=loginform" style="color: blue;">로그인</a>을 해주세요</h3>

		</div>
	<div class="parallax">
	</div>
	<div id="copyright" class="container">
		<p>
			&copy; Untitled. All rights reserved. | Photos by <a
				href="http://fotogrph.com/">Fotogrph</a> | Design by <a
				href="http://templated.co" rel="nofollow">TEMPLATED</a>
		</p>
	</div>
</body>
</html>