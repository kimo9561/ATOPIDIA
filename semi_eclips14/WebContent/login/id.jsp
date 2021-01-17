<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<%@page import="model.Dto.MemberDto" %>
</head>
<%
      MemberDto res=(MemberDto)request.getAttribute("selectId");
   System.out.println("jsp : "+res.getMember_Id());
   
%>
<body>
  
     <table width="750px" align="center"style="background-color:white; margin-top:3%" >
      <tr>
       <td><%=res.getMember_Name() %>님의 아이디는</td>
      </tr>
      <tr>
      <td><h1><%=res.getMember_Id()%></h1>입니다.</td>
      </tr>
       <tr>
        <td><input type="button" value="확인" class="btn btn-primary" onclick="self.close();"></td>
       </tr>
   </table>

</body>
</html>