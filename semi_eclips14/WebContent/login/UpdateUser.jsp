<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html;charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js">
</script>
<style type="text/css">
	<%@include file="../css/default.css"%>
</style>
<style type="text/css">
#submit{
	  width:100px;
      height:44px;
      color:#FFF;
      background-color:#2C383B;
}
#id{
	background-color:#2C383B;
}
</style>
</head>

<body>
<%@include file="../nav/navlogin1.jsp" %>
	<br><br><br><br>
	<div id=featured-wrapper style="padding:3%">
	 <div id="extra" class="container">
	<h1 style="color: white">
		비밀번호 변경 
	</h1>
	</div>
	

	<form action="../membercontroller.do" method="POST">
		<input type="hidden" name="command" value="update">
		<input type="hidden" name="memberno" value="<%=dto.getMember_No() %>">
		
		<div>
		<h2 style="padding: 1%">아이디: <%=dto.getMember_Id() %></h2>
		<h2 style="padding: 1%">비밀번호: <input type="password" name="updateval"></h2>
		<h2 style="padding: 1%">이름: <%=dto.getMember_Name() %></h2>
		<h2 style="padding: 1%">이메일 : <%=dto.getMember_Email() %></h2>
		<h2 style="padding: 1%">주민번호: <%=dto.getMember_RRN() %></h2>
		<br><br><br>
		<input type="submit" value="수정완료" class="button">
		</div>		
	
	</form>
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