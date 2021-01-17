
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@	page import="model.Dto.MemberDto" %>      
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
   
	<style type="text/css">
	<%@include file="../css/default.css"%>
	<%@include file="recipe.css"%>
	</style>
	
	
</head>
<body>
<%@include file="../nav/navlogin.jsp"%>
	
    <div id="write">
    	
        <a href="userrecipecontroller.do?command=writeform" class="button">새 글 작성하기</a>
        |
         <a href="userrecipecontroller.do?command=delete&recipe_No=${list.recipe_No}&member_Id=${dto.member_Id }&recipe_writer=${list.recipe_Writer}&grade_code=${dto.grade_Code}" class="button">삭제</a>
         |<a href="userrecipecontroller.do?command=updateform&recipe_No=${list.recipe_No}&member_Id=${dto.member_Id }&recipe_writer=${list.recipe_Writer}" class="button">수정</a>

        
 	</div>
    <div><br><br><br></div>
 		
       	<div id="info">
     	<div id="featured-wrapper">
      	 <h2>${list.recipe_Title}</h2>
      	
      	 <br><br>
      	  <div id=about> 	
        작성자:${list.recipe_Writer }
        <br><br>
        작성일: ${list.recipe_Date }
        <br><br>
   	조회수: ${list.recipe_Hits }
   		</div>
   		 <br><br><br><br><br><br>
       <hr>
       <div class="container"  style="text-align: center;">
        <img class="img" src="img/${list.recipe_Image_Realname}" width="90%" height="440px">
     	</div>
   		<br><br>
         <div id="recipe" class="container" style="text-align: center;">
		<pre>
		${list.recipe_Text}
		<br><br><br>
		<br><br><br>
		<br><br><br>
		<br><br><br>
		<br><br><br>
		</pre> 
		

		
		</div>
        <div id="ingredients">
            <h3 style="padding:1%">재료</h3>
            <c:forTokens items="${list.recipe_Ingredient}" delims="," var="inglist">
            <p style="padding:0%; margin: 0%"><c:out value="${inglist }"/></p>
            </c:forTokens>
        </div> 
 
        <!-- 댓글 -->
        
      
        <br><br><br>
        
       <div id="extra" class="container" style="width: 70%; float: left; margin-left: 5%;"  >
        <h2>댓글</h2> 
    	</div>
  		
  		<div id="recipe" style="padding: 5%;">
          <form action="userrecipecontroller.do?command=answer" method="post">

			<input type="hidden" name="recipe_No" value="${list.recipe_No}">
			
                     작성자: 
            <textarea rows="1" cols="60" name="answer_writer" readonly="readonly">${dto.member_Id }</textarea>
            
            <br><br>
        	 답글 내용: <br>
            <textarea rows="20" cols="100" name="answer_content"></textarea>
            
            <br><br>
            <button type="submit" class="button">올리기</button>
          </form>
         
         
          <br><br>
    	<c:forEach var="ans" items="${ans}">
          <table>
          		<col width="200"><col width="500">
              <tr>
                  <th>
                    ${ans.answer_Writer}
                  </th>
                  <td>
                    ${ans.answer_Content}
                  </td>
                  </tr>
               <tr>
               <td>
               </td>
                  <td>
                  작성일: ${ans.answer_Date}
                  </td>
                  
             	</tr>
             	<tr>
             	<td>
             	</td>
             	<td>
             	<button class="smallbutton" onclick="location.href='userrecipecontroller.do?command=deleteanswer&recipe_No=${list.recipe_No}&answer_No=${ans.answer_No}&grade_code=${dto.grade_Code}'">삭제</button>
             	<button class="smallbutton" onclick="location.href='userrecipecontroller.do?command=updateanswerform&recipe_No=${list.recipe_No}&answer_No=${ans.answer_No}&member_Id=${dto.member_Id}&answer_writer=${ans.answer_Writer}'">수정</button>
             	</td>
             	</tr>
            </table>
          </c:forEach>
           </div>
		</div>
        </div>


	
    <div id="copyright" class="container">
        <p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
    </div>
    
</body>
</body>
</html>