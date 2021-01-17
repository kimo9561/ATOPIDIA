<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>recipemain</title>
    <style type="text/css">
    <%@include file="../css/default.css"%>
    
    
    </style>
    <script type="text/javascript">
    <%@include file ="jquery.min.js"%>
    </script>
    
    </head>
    <body>
    <%@include file="../nav/navlogin.jsp"%>

        <div>
        <a href="userrecipecontroller.do?command=writeform" class="button">새 글 작성하기</a>
        </div>
       
        
        <div id="wrapper">
        
        <div><br></div>
        <div id="extra" class="container">
            <h2 style="text-align: center;">추천 정보</h2>
        </div>
        <div id="featured-wrapper">
            <div id="featured" class="container">
            <c:forEach var="dto" items="${list}">
                       <div class="column5">
                          <a href="userrecipecontroller.do?command=detail&recipe_No=${dto.recipe_No}"><img class="image-center" src="img/${dto.recipe_Image_Realname}" width="300px" height="300px"></a>
                           <br><br><br>
                           <div>
                               <h2><a href="userrecipecontroller.do?command=detail&recipe_No=${dto.recipe_No}">${dto.recipe_Title}</a></h2>
                               <br>
                               <P>조회수: ${dto.recipe_Hits }</P>
                           </div>
                           
                       </div>
              </c:forEach>
          	</div>
           </div>
           
<!--                      Pagination
	<ul class="pagination my justify-content-center mb-4">
		<li><a class="page-link text-dark" href="#">◀</a></li>
		<li><a class="page-link text-dark" href="#">1</a></li>
		<li><a class="page-link text-dark" href="#">2</a></li>
		<li><a class="page-link text-dark" href="#">3</a></li>
		<li><a class="page-link text-dark" href="#">4</a></li>
		<li><a class="page-link text-dark" href="#">5</a></li>
		<li><a class="page-link text-dark" href="#">▶</a></li>

	</ul>
            -->
           	<!-- Footer -->
    <div id="copyright" class="container">
        <p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
    </div>
   
		<!-- /.container -->

          </div>
          

   
</body>
</html>