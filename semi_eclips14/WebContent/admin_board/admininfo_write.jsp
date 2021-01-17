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
<!-- Bootstrap core JavaScript -->
	<script type="text/css">
   		<%@include file="vendor/bootstrap/js/bootstrap.bundle.min.js"%>
	</script>
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
	<form action="../admininfocontroller.do?command=boardwrite&member_No=${dto.member_No}&info_writer=${dto.member_Id}" method="post" enctype="multipart/form-data">
			<!-- 리뷰쓰기 -->
			<h1 class="mt-4 text-center">공유 정보 쓰기</h1>
			<hr>
			
			<!-- 제목 -->
			<p style="color: white;">제목:</p>
			<input type="text" name="info_Title" id="info_Title" placeholder="제목을 입력하세요" size=100 >
			<input type="text" name="info_Writer" value="${dto.member_Id}">
			<hr>

			<input type="file" name="file" id="imageFileOpenInput" accept="image/*">

			<hr>

			<p style="color: white;">내용:</p>
			<textarea rows="10" cols="100" placeholder="내용을 입력하세요." name="info_Text"></textarea>
				
			<hr>
			<input type="submit" value="올리기" class="button"
			 style="float: right;" >
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

</body>

</html>
