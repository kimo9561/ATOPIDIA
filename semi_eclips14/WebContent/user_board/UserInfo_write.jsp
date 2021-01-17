<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>글쓰기</title>

<style type="text/css">
<%@include file ="../css/bootstrap.css"%>
<%@include file ="vendor/bootstrap/css/bootstrap.min.css"%> 
<%@include file ="vendor/jquery/jquery.min.js"%>

</style>

</head>

<body>

	<!-- Navigation -->
<%@include file="../nav/navbootstrap.jsp"%>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>


	<!-- Page Content -->
		
	<div class="container">
	<form action="../userinfocontroller.do?command=boardwrite&member_No=${dto.member_No}&info_writer=${dto.member_Id}" method="post" enctype="multipart/form-data">
			<!-- 리뷰쓰기 -->
			<h1 class="mt-4 text-center">공유 정보 쓰기</h1>
			<hr>

			<!-- 제목 -->
			<div class="form-group">
    			<label class="col-sm-2 control-label">제목:</label>
     			<input type="text" class="form-control" name="info_Title" placeholder="제목을 입력해 주세요.">
    		</div>
			<br><br>
			<div class="form-group">
			<label class="col-sm-2 control-label">아이디: </label>
			<div class="col-sm-20">
     			<input type="text" class="form-control" value="${dto.member_Id}" placeholder="제목을 입력해 주세요." disabled>
    		</div>
    		</div>
			<br><br>
			<input type="file" name="file" id="imageFileOpenInput" accept="image/*">

			<br><br>

			<div class="form-group">
    			<label class="col-sm-2 control-label">제목:</label>
			<textarea class="form-control" rows="10" name="info_Text" placeholder="내용을 입력하세요."></textarea>
    		</div>
    		
			<br><br>
			<input type="submit" value="올리기" class="button" style="float: right;" >
			</form>
		</div>	
		
	
	<br>
	<br>
	<br>
	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center" style="color: grey;">Copyright &copy;
				Atopidia 2020</p>
		</div>
		<!-- /.container -->
	</footer>

	<script type="text/css">
   <%@include file="vendor/bootstrap/js/bootstrap.bundle.min.js"%>
	</script>

</body>

</html>
