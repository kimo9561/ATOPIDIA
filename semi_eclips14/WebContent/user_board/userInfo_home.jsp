<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>Atopidia Info</title>

<style type="text/css">
<%@include file ="../css/bootstrap.css"%>
<%@include file ="vendor/bootstrap/css/bootstrap.min.css"%> 
<%@include file ="vendor/jquery/jquery.min.js"%>

</style>

</head>

<body>

	<!-- Navigation -->
<jsp:include page="../nav/navbootstrap.jsp"></jsp:include>

	<br>
	<br>
	<br>
	<br>
	<br>

	
	<div class="container">
		<h1 style="text-align: center;">우리끼리 공유하는 정보</h1>
		<br> <br>
		<!-- Page Content -->
			<div class="container">

			<div class="row">

				<c:forEach var="dtos" items="${pageDto.data}">

					<div class="col-6">
						<div class="card">
							<div class="card-header" id="review">${dtos.info_Title}</div>
							<img class="card-img-top" src="img/${dtos.info_Image_Realname}" width="300px" height="300px">
							<div class="card-body">
								<p class="card-text">작성자:${dtos.info_Writer}</p>
								<a href="userinfocontroller.do?command=detail&info_No=${dtos.info_No}" class="button3" id="reviewbutton">리뷰 더보기 &rarr;</a>
							</div>
							<div class="card-footer text-muted">조회수:  ${dtos.info_Hits}</div>
						</div>
					</div>

				</c:forEach>
			</div>
		</div>
	</div>

	<div class="container"></div>

	<br>
	<br>
	<div id="extra2" class="container">
		<input type="button" class="button3" value="글쓰기" style="float: right;"
			onclick="location.href='userinfocontroller.do?command=writeform'">
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>

	<!-- Pagination -->
 	<ul class="pagination my justify-content-center mb-4">
 		<c:if test="${pageDto.startPage != 1}">
			<li><a class="page-link text-dark" href="userinfocontroller.do?command=list&page=${pageDto.startPage-1}">◀</a></li>
 		</c:if>
		
		<c:forEach var="i" begin="${pageDto.startPage}" end="${pageDto.endPage}">
			<c:if test="${pageDto.page == i}"><p class="page-link text-dark">${i}</p></c:if>
			<c:if test="${pageDto.page != i}">
				<li><a class="page-link text-dark" href="userinfocontroller.do?command=list&page=${i}">${i}</a></li>
			</c:if>
		</c:forEach>

		<c:if test="${pageDto.endPage != pageDto.totalPage}">
			<li><a class="page-link text-dark" href="userinfocontroller.do?command=list&page=${pageDto.endPage+1}">▶</a></li>
		</c:if>

	</ul>
 -->



	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center" style="color: grey;">Copyright &copy;
				Atopidia
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script type="text/css">
   <%@include file="vendor/bootstrap/js/bootstrap.bundle.min.js"%>
	</script>
	
	<!-- Footer -->
	   <div id="copyright" class="container">
        <p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
    </div>
   
</body>

</html>