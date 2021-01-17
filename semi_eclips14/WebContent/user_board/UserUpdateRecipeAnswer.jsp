<%@page import="model.Dto.UserInfoAnswerDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    	request.setCharacterEncoding("UTF-8");
    %>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
  
<%@page import="model.Dto.MemberDto"%>
<%@page import="model.Dto.UserInfoAnswerDto" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

<%
	UserInfoAnswerDto anw=(UserInfoAnswerDto)request.getAttribute("anw");
%>

    
	<style type="text/css">
	<%@include file="../css/default.css"%>
	<%@include file="recipe.css"%>
	</style>
	
	
</head>
<body>
<%@include file="../nav/navlogin.jsp"%>
	
    <div id="write">
        <a href="../userrecipecontroller.do?command=writeform" class="button">새 글 작성하기</a>
        |
         <a href="../userrecipecontroller.do?command=delete&recipe_No=${list.recipe_No}"  class="button">삭제</a>
         |<a href="../userrecipecontroller.do?command=updateform&recipe_No=${list.recipe_No}" class="button">수정</a>
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
   		<br><br>
        <div id="recipe" class="container">
		<span>
		${list.recipe_Text}
		<br><br><br>
		<br><br><br>
		<br><br><br>
		<br><br><br>
		<br><br><br>
		</span> 
		

		
		</div>
        <div id="ingredients">
            <h3>재료</h3>
            <p>${list.recipe_Ingredient}</p>
                       
        </div> 
 
        <!-- 댓글 -->
        
      
        <br><br><br>
        
       <div id="extra" class="container" style="width: 70%; float: left; margin-left: 5%;"  >
        <h2>댓글</h2> 
    	</div>
  		
  		<div id="recipe" style="padding: 5%;">
          <form action="../userrecipecontroller.do" method="post">
			<input type="hidden" name="command" value="answer">
			<input type="hidden" name="recipe_No" value="${list.recipe_No}">
			
                     작성자: 
            <textarea rows="1" cols="60" name="answer_writer"></textarea>
            
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
             	</table>
             	
             	
          	</c:forEach> 
             	        	
             	<form action="userrecipecontroller.do" method="post">
             	<input type="hidden" name="command" value="updateanswer">
             	<input type="hidden" name="recipe_No" value="${list.recipe_No}">
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
             		<textarea rows="10" cols="50" name="updateanswer_content" >${anw.answer_Content}</textarea>
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
    			location.href="userrecipecontroller.do?command=detail&recipe_No="+${list.recipe_No};
    			</script>
              <%} %>
           </div>
		</div>
        </div>


	
    <div id="copyright" class="container">
        <p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
    </div>
    
</body>
</body>
</html>