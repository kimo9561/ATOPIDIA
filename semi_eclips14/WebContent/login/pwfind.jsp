<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@page import="model.Dto.MemberDto" %>
<%
	MemberDto res=(MemberDto)request.getAttribute("selectPw");
%>

</head>
<body>

     <table>
      <tr>
       <td><%=res.getMember_Id()%>님의 패스워드는<h1><%=res.getMember_Pw()%></h1>입니다.</td>
      </tr>

       <tr>
        <td>
        	<input type="button" value="확인" class="btn " onclick="self.close();">
        </td>

       </tr>
	</table>
</body>
</html>