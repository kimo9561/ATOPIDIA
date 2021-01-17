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
<title>Login</title>

<style type="text/css">
       <%@include file="../../css/default.css"%>
         <%@include file="login.css"%>

</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	function login(){
		
		var id = $("#id").val();
		var pw = $("#pw").val();
		console.log(id);
		console.log(pw);
		
		if(!id){
	         alert("아이디를 입력하세요");
	         return false;
	      }
		
		if(!pw){
			 alert("비밀번호를 입력하세요");
	         return false;
		}		 
		var form = document.loginchk;
		
		if(id() != null && pw() != null){
		form.submit();
		}
		 
	}
 
function searchid(){
	window.open("idfind.jsp","_blank","width=900px,height=700px");
}
function searchpw(){
	window.open("pw.jsp","_blank","width=800px,height=900px");
}
</script>
</head>
<body>
   <%@include file="../nav/navlogout.jsp"%>
   
   
   <h1 id="login_title">
      로그인 
   </h1>
   <div id="featured-wrapper">
   <div id="loginform">
   <form action="../membercontroller.do" method="post">
   <input type="hidden" name="command" value="login">
   <div>
      I D:&nbsp;
      <input type="text" name="id" id="id" class="logintext">

   <br>
   <br>
      PW:
   <input type="password" name="PW" id="pw" class="logintext"> 
   <br><br><br>
      
      <input type="submit" class="button" value="로그인" onclick="login();" style="margin: 1%">
      
      <input type="button" class="button" value="회원가입"  style="margin: 1%" onclick="location.href='../membercontroller.do?command=signupform'">
     <Br>
	  <input type="button" class="button" value="아이디찾기"   style="margin: 1%" onclick="searchid();">
      
      <input type="button" class="button" value="비밀번호찾기 "  style="margin: 1%" onclick="searchpw();">
      
      
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