<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>

<%@ page import="model.Dto.AdminInfoDto" %>
<%@ page import="model.Dto.AdminRecipeDto" %>
<%@ page import="model.Dto.UserInfoDto" %>
<%@ page import="model.Dto.UserRecipeDto" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style type="text/css">
	<%@include file="../css/default.css"%>
</style>
</head>
<% int i = 1; %>
<body>
	<%@include file="../nav/navlogin.jsp"%>
	<br><br><br>
	
	<div id="wrapper">
		<br>
		<div id="extra" class="container">
			<h2 style="text-align: center;">아토피디아가 추천하는 TOP3 레시피</h2>
			<span></span> 
		</div>
		
		<div id="featured-wrapper">
			<div id="featured" class="container">
			<c:forEach var="adminRecipe" items="${admin_RecipeList }">
				<div class="column<%=i%>">
					<span><a href="adminrecipecontroller.do?command=detail&recipe_No=${adminRecipe.recipe_No }">
					<img class="image-center" src="main/images/top<%=i++ %>.png" width="300px" height="300px"></a>
					</span>
				<div class="title">
					<h2>${adminRecipe.recipe_Title }</h2>
				</div>
			</div>
			</c:forEach>
			</div>
		</div>
		<%i = 1; %>
		<div id="extra" class="container">
			<h2 style="text-align: center;">아토피디아가 추천하는 TOP3 건강 정보</h2>
		</div>
		<div id="featured-wrapper">
			<div id="featured" class="container">
				<c:forEach var="adminInfo" items="${admin_InfoList }">
					<div class="column<%=i%>"> 
						<span><a href="admininfocontroller.do?command=detail&info_No=${adminInfo.info_No }">
							<img class="image-center" src="main/images/top<%=i++%>.png" width="300px" height="300px"></a>
						</span>
					<div class="title">
						<h2>${adminInfo.info_Title }</h2>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
		<% i = 1; %>
		<div id="extra" class="container">
			<h2 style="text-align: center;">아토피디아 사용자들이 추천하는 TOP3 레시피</h2>
		</div>
		<div id="featured-wrapper">
			<div id="featured" class="container">
			<c:forEach var="userRecipe" items="${user_RecipieList }">
					<div class="column<%=i%>"> 
						<span><a href="userrecipecontroller.do?command=detail&recipe_No=${userRecipe.recipe_No }">
							<img class="image-center" src="main/images/top<%=i++%>.png" width="300px" height="300px"></a>
						</span>
					<div class="title">
						<h2>${userRecipe.recipe_Title }</h2>
					</div>
					</div>
				</c:forEach>
			</div>
		</div>	
			
		<% i = 1; %>
		<div id="extra" class="container">
			<h2 style="text-align: center;">아토피디아 사용자들이 추천하는 TOP3 건강 정보</h2>
		</div>
		<div id="featured-wrapper">
			<div id="featured" class="container">
			<c:forEach var="userInfo" items="${user_InfoList }">
					<div class="column<%=i%>"> 
						<span><a href="userinfocontroller.do?command=detail&info_No=${userInfo.info_No }">
							<img class="image-center" src="main/images/top<%=i++%>.png" width="300px" height="300px"></a>
						</span>
					<div class="title">
						<h2>${userInfo.info_Title }</h2>
					</div>
					</div>
				</c:forEach>
			</div>
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