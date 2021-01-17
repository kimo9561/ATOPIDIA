<%@page import="model.Dto.AdminRecipeDto"%>
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
    <%@include file="../../css/default.css"%>
    
    
    </style>
    <script type="text/javascript">
    <%@include file ="jquery.min.js"%>
    </script>
    
    </head>
    <body>
    <%@include file="../../nav/navlogin.jsp"%>
 
   

        <div>
        <a href="adminrecipecontroller.do?command=writeform" class="button">새 글 작성하기</a>
        </div>
       
        
        <div id="wrapper">
        
        <div><br></div>
        <div id="extra" class="container">
            <h2 style="text-align: center;">아토피디아가 추천하는 레시피</h2>
        </div>
        <div id="featured-wrapper">
            <div id="featured" class="container">
            <c:forEach var="main" items="${list}">
                       <div class="column5">
                          <a href="adminrecipecontroller.do?command=detail&recipe_No=${main.recipe_No}"><img class="image-center" src="img/${main.recipe_Image_Realname}" width="300px" height="300px"></a>
                           <br><br><br>
                           <div>
                               <h2><a href="adminrecipecontroller.do?command=detail&recipe_No=${main.recipe_No}">${main.recipe_Title}<input type="hidden" onclick=""></a></h2>
                               <br>
                               <P>조회수: ${main.recipe_Hits }</P>
                           </div>
                           
                       </div>
              </c:forEach>
          	</div>
           </div>
           
  <div id="copyright" class="container">
        <p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
    </div>
    
   
</body>
</html>