<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charsert=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
   //페이지 로드 되면 실행
   onload=function(){
      var id =opener.document.getElementsByName("userId")[0].value;
      document.getElementsByName("id")[0].value=id;
   }
   
   function confirm(bool){
      if(bool=="true"){
         opener.document.getElementsByName("userPw")[0].focus();
         opener.document.getElementsByName("userId")[0].title="y";
      }else{
         opener.document.getElementsByName("userId")[0].focus();
         opener.document.getElementsByName("userId")[0].title="n";
      }
      self.close();
   }
</script>

</head>
<% String isnotused = request.getParameter("isnotused"); %>
<body>
   <table border="1">
      <tr>
         <td><input type="text" name="id" readonly="readonly"></td>
      </tr>
      <tr>
         <td><%=(isnotused.equals("true"))? "아이디 생성가능 ":"중복 아이디 존재" %></td>
      </tr>
      <tr>
         <td>
           <input type="button"  value="확인" onclick="confirm('<%=isnotused%>')">
         </td>
      </tr>
   </table>
</body>
</html>