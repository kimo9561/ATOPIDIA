
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>사용자 리뷰</title>


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
          <br><br><br><br><br><br>
<hr>

<div class="container">
	<form action="userinfocontroller.do?command=infoupdate&member_No=${dto.member_No}&info_No=${one.info_No}" method="post" enctype="multipart/form-data">
			<!-- 리뷰쓰기 -->
			<h1 class="mt-4">리뷰 수정</h1>
			<hr>
			<p>작성자: ${one.info_Writer}</p>
			
			<!-- 제목 -->
			<p>제목:</p>
			
			<textarea rows="2" cols="100"
				name="info_Title"  id="info_Title">${one.info_Title}</textarea>

			<hr>

			<input type="file" name="file" id="imageFileOpenInput"
				accept="image/*">

			<hr>

			<p>내용:</p>
			<textarea rows="10" cols="100" 
				name="info_Text">${one.info_Text}</textarea>
				
			<hr>
			<input type="submit" value="수정하기" class="button"
			 style="float: right;" >
		</form>
	</div>		
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center">Copyright &copy; Atopidia 2020</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
	<script type="text/css">
   <%@include file="vendor/bootstrap/js/bootstrap.bundle.min.js"%>
	</script>

</body>

</html>