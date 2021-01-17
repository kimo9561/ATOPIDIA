<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Diary</title>
<link href="../css/default.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	<%@include file="diaryjs.js"%>
</script>
<style type="text/css">
   <%@include file="../css/default.css"%>
   <%@include file="diarylistcss.css"%>
</style>
</head>
<body>
<%@include file="../nav/navlogin.jsp" %>
	<div class="content">
    	<div><br><br><br></div>
	 	<div>
        <div class="title" style="text-align: center;"><h1><strong>나만의 다이어리 목록</strong></h1></div>
	    <form action="diarycontroller.do" method="post">
        	<input type="hidden" name="command" value="deleteform">
        	<input type="hidden" name="member_No" value="${dto.member_No }">
 			<div>
	        <input type="submit" class="btnRelativezoom" value="삭제">
	        <input type="button" class="btnRelativezoom" value="전체선택" onclick="allChk();">
	        <input type="button" class="btnRelativezoom" value="전체해제" onclick="allClear();">
	        <input type="button" class="btnRelativezoom" value="새 글 작성하기" onclick="location.href='diarycontroller.do?command=insertdiaryform'">
        	</div>
        	<br><br>
        	 <div id="column" style="text-align: center;">
        			<c:choose>
        			<c:when test="${empty diarylist}">
        			<br><br><br>
						<h1 style="text-align:center; color:white;">글이 존재하지 않습니다. 나만의 다이어리를 만들어보세요.</h1>
        			</c:when>
        			<c:otherwise>
       			 		<c:forEach var="dlist" items="${diarylist}">        				
        					<div class="column5">
        						<img src="diary/upload/${dlist.diary_Image_RealName}" width="300px" height="300px" onclick="location.href='diarycontroller.do?command=detailform&diary_No=${dlist.diary_No}'">
        						<br>
        						<input type="checkbox" class="chk" name="chk" value="${dlist.diary_No}" style="float:left; ">        				
        						<h3>제목: ${dlist.diary_Title}</h3><br><br>
        						<h3>날짜 : ${dlist.diary_Date}</h3>
        					</div>
        				</c:forEach>
        			</c:otherwise>
        	</c:choose>
        </div>
        </form>
		</div>
	</div>
  
	    
	
   <div id="copyright" class="container">
		<p>
			&copy; Untitled. All rights reserved. | Photos by <a
				href="http://fotogrph.com/">Fotogrph</a> | Design by <a
				href="http://templated.co" rel="nofollow">TEMPLATED</a>.
		</p>
   </div>	
</body>
</html>