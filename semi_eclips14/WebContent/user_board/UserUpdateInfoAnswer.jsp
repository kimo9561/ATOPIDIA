<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<%@ page import="model.Dto.MemberDto" %>
<%@ page import="model.Dto.UserInfoDto" %>
<%@page import="model.Dto.UserInfoAnswerDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>사용자 리뷰</title>
  
<%
 	UserInfoDto inf =(UserInfoDto)request.getAttribute("list");
%> 
<%
	UserInfoAnswerDto anw=(UserInfoAnswerDto)request.getAttribute("anw");
%>
 
<style type="text/css">
<%@include file ="../css/default.css"%>
<%@include file ="vendor/bootstrap/css/bootstrap.min.css"%> 
<%@include file ="vendor/jquery/jquery.min.js"%>
</style>

<!-- Bootstrap core JavaScript -->
<script type="text/css">
   	<%@include file="vendor/bootstrap/js/bootstrap.bundle.min.js"%>
</script>
	
</head>
<body>

  <!-- Navigation -->
<%@include file="../nav/navlogin.jsp"%>
          <br><br><br><br><br><br>
<hr>
  <!-- Page Content -->


<%
	if( inf.getInfo_Writer().equals( dto.getMember_Id() ) ) {
%> 
     
	<input type="button" value="수정" class="button4" onclick="location.href='userinfocontroller.do?command=updateInfoForm&info_No=${list.info_No}'">
	<input type="button" value="삭제" class="button4" onclick="location.href='userinfocontroller.do?command=delete&info_No=${list.info_No}'">   
	
<%	
	} 
%> 
   <input type="button" value="저장" class="button4" onclick="location.href='userinfocontroller.do?command=save&info_No=${list.info_No}&member_No=${dto.member_No }'">
   <div class="container">
        <!-- Title -->
        <h1 class="mt-4" style="color: black; text-align: center;">${list.info_Title}</h1>

        <!-- Author -->
        <p class="lead">
          by <div>${list.info_Writer}</div>
          <p style="float: right;">조회수: ${list.info_Hits}</p>
        <hr>
        <!-- Date/Time -->
        <p style="color: black;">${list.info_Date}</p>
        <hr>

        <!-- Preview Image -->
        <div class="container"  style="text-align: center;">
        <img class="img" src="user_board/upload1/<%=inf.getInfo_Image_Realname()%>" width="620px" height="420px">
      	</div>
        <hr>

        <!-- Post Content -->
        <p class="lead" style="color: black;">${list.info_Text}</p>
        <hr>
        
	</div>
        <br>
      <!-- Comments Form -->
      <div class="card my-4 p-auto">
          <h5 class="card-header">댓글:</h5>
          <div class="card-body">
            <form action="userinfocontroller.do" method="post">
				<input type="hidden" name="command" value="answer">
				<input type="hidden" name="info_No" value="${list.info_No}">
              		<div class="form-group">
              			<img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
          				<div class="media-body">
            				<h5 class="mt-0">${list.info_Writer}</h5>
            				<input type="hidden" name="answer_writer" value="${list.info_Writer}">
          				</div>
                	<textarea class="form-control" rows="3" name="answer_content"></textarea>
              	</div>
              <button type="submit" class="button3" style="float: right;">올리기</button>
            </form>
          </div>
        </div>

        <!-- 댓글 -->
        <c:forEach var="ans" items="${ans}">
        <div class="media mb-4">
          	<img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
          		<div class="media-body">
            		<h5 class="mt-0">${ans.answer_Writer}</h5>
                    <p class="lead">${ans.answer_Content}</p>
		         	<form action="admininfocontroller.do" method="post">
             	<input type="hidden" name="command" value="updateanswer">
             	<input type="hidden" name="info_No" value="${list.info_No}">
             	<input type="hidden" name ="answer_No" value ="${anw.answer_No }">
             	<input type="hidden" name ="answer_Writer" value="${anw.answer_Writer}">
                
                 <%if(anw.getAnswer_Writer().equals(dto.getMember_Id())){ %>
             	<table>
             	<tr>	
             		<td>
             		작성자: ${anw.answer_Writer}
             		</td>
             	</tr>
             	<tr>
             		<td colspan="2">
             		<textarea class="form-control" rows="3" name="updateanswer_content">${anw.answer_Content}</textarea>
             		</td>
             	</tr>
             	<tr>
       				<td></td>
       				<td>
       				<button type="submit" class="smallbutton">저장</button>
       				</td>
             	</tr>
           		
             </table>
             </form>
               <%}else{%>
          		<script type="text/javascript">
    			alert("작성한 회원만 답글을 수정 할 수 있습니다.");
    			location.href="userinfocontroller.do?command=detail&info_No="+${list.info_No};
    			</script>
              <%} %>
        </div>
  		</div>
  		</c:forEach>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center">Copyright &copy; Atopidia 2020</p>
    </div>
    <!-- /.container -->
  </footer>
</body>
</html>