<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Dto.MemberDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%MemberDto dto=(MemberDto)session.getAttribute("dto");
	if(dto==null){
%>
	<script type="text/javascript">
	alert("로그인을 하고 사용해 주세요.");
	location.href="maincontroller.do?command=mainlogout"
	</script>
<% 
	}
%>
</head>
<body>
	<header>
		<div id="header-wrapper">
			<div id="header" class="container">
				<div id="logo">
					<h1>
						<a href="maincontroller.do?command=main">Atopidia</a>
					</h1>
					<span>By <a>이태원 캐찹</a></span>
				</div>
				<div id="menu">
					<ul>
						<li class="current_page_item a"><a href="maincontroller.do?command=main">HOME</a></li>
						<li><a>레시피 보러가기</a>
							<ul>
								<li><a href="userrecipecontroller.do?command=list">사용자</a></li>
								<li><a href="adminrecipecontroller.do?command=list">관리자</a></li>
							</ul></li>
						<li><a>공유정보 보러가기</a>
							<ul>
								<li><a href="userinfocontroller.do?command=list">사용자</a></li>
								<li><a href="admininfocontroller.do?command=list">공유정보 보기</a></li>
							</ul></li>
						<li><a>나만의 켈린더</a>
							<ul>
								<li><a href="calendarcontroller.do?command=calendarmain&member_no=${dto.member_No }">달력 보러가기</a></li>
								<li><a href="diarycontroller.do?command=diarylist&member_no=${dto.member_No }">다이어리 보러가기</a></li>
							</ul></li>
						<li><a href="mapcontroller.do?command=map">병원찾기</a></li>
                		<li><a href="membercontroller.do?command=logout" style="cursor: pointer;">&nbsp;로그아웃</a>
                			<ul>
                				<li><a href="membercontroller.do?command=changepw" style="cursor: pointer;" id="forbootstrap4">&nbsp;비밀변호<br>변경</a></li>
                			</ul>
                		</li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<h2>
	<%if(dto.getGrade_Code() == 1){ %>
	<a style="color: white; float: left; margin-right: 1%; padding: 1%" href="membercontroller.do?command=adminpage">관리자 마이페이지</a>
	<%} %>
	</h2>
	<h2 style="color: white; float: right; margin-right: 1%; padding: 1%" >${dto.member_Id }님 환영합니다 등급: 
	<c:if test="${dto.grade_Code == 1}">
		<c:out value="관리자"/>
	</c:if>
	<c:if test="${dto.grade_Code == 2}">
		<c:out value="일반회원"/>
	</c:if>
	</h2>
</body>
</html>