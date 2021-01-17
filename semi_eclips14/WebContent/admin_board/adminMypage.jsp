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

</script>
<style type="text/css">
   <%@include file="../css/default.css"%>
  
</style>
</head>
<body>
<%@include file="../nav/navlogin1.jsp" %>
	
	<div id="extra" class="container">
            <h2 style="text-align: center;">관리자 페이지</h2>
        </div>

	 	<div id="header-wrapper" style="padding:10%">
        <div style="text-align: center">
	        
	   		<input type="button" class="button" value="정보수정 " id="loginbutton" onclick="location.href='../membercontroller.do?command=updateuser&memberno=<%=dto.getMember_No()%>'"style="margin-right: 5%; font-size:  150%; margin-top: 3%">
 			<input type="button" class="button" value="정보조회 " id="loginbutton" 
	        onclick="location.href='../membercontroller.do?command=UserListAll'" style="margin-right: 5%; font-size:  150%">
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