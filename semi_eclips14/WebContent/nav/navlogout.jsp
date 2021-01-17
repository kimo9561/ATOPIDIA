<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Dto.MemberDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%MemberDto dto=(MemberDto)session.getAttribute("dto");%>
</head>
<body>
	<header>
		<div id="header-wrapper">
			<div id="header" class="container">
				<div id="logo">
					<h1>
						<a href="../maincontroller.do?command=mainlogout">Atopidia</a>
					</h1>
					<span>By <a>이태원 캐찹</a></span>
				</div>
				<div id="menu">
				
						<ul>
						<li class="current_page_item a"><a href="../maincontroller.do?command=mainlogout">HOME</a></li>
						<li><a>레시피 보러가기</a>
							<ul>
								<li><a href="../userrecipecontroller.do?command=logout">사용자</a></li>
								<li><a href="../adminrecipecontroller.do?command=logout">관리자</a></li>
							</ul></li>
						<li><a>공유정보 보러가기</a>
							<ul>
								<li><a href="../userinfocontroller.do?command=logout">사용자</a></li>
								<li><a href="../admininfocontroller.do?command=logout">공유정보 보기</a></li>
							</ul></li>
						<li><a>나만의 켈린더</a>
							<ul>
								<li><a href="../calendarcontroller.do?command=logout">달력 보러가기</a></li>
								<li><a href="../diarycontroller.do?command=logout">나만의 캘린더</a></li>
							</ul></li>
						<li><a href="../mapcontroller.do?command=logout">병원찾기</a></li>
                		<li class="loginbutton"><a href="../membercontroller.do?command=loginform" style="cursor: pointer;">&nbsp;로그인</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
</body>
</html>