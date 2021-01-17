<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>  


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    
  
    <link href="fonts.css" rel="stylesheet" type="text/css" media="all" />
    <link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">
    
    
    	<style type="text/css">
	<%@include file="../css/default.css"%>
	</style>
    
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script type="text/javascript">
	   var sel_file;
	   $(document).ready(function() {
	      $("#input_img").on("change", handleImgFileSelect);
	   });

	   function handleImgFileSelect(e) {
	      $("#img").empty(); //empty, remove 구분! remove는 태그 자체를 지운다 오키?
	      var files = e.target.files;
	      var filesArr = Array.prototype.slice.call(files);

	      /* if (files.name == null) {
	         resetInputFile();
	      } */
	      filesArr.forEach(function(f) {
	         if (!f.type.match("image.*")) {
	            alert("확장자는 이미지 확장자만 가능합니다.");
	            return;
	         }
	         sel_file = f;

	         var reader = new FileReader();
	         reader.onload = function(e) {
	            $("#img").attr("src", e.target.result);
	            $("#img").attr("width", "500px");
	            $("#img").attr("height", "500px");
	         }
	         reader.readAsDataURL(f);
	      })
	   }
	
	
	</script>
    

</head>
<body>
<%@include file="../nav/navlogin.jsp"%>
 
    <div><br><br><br></div>
 
    <h2 id="title1">레시피 작성하기</h2>
   <form action="userrecipecontroller.do?command=update&recipe_No=${list.recipe_No}" method="post" enctype="multipart/form-data">

      <div id="info">
      <div id="featured-wrapper">
    <h2>제목: 
          <textarea rows="2" cols="100" name="recipe_title">${list.recipe_Title }</textarea>
    </h2>
    <br>
    <div id=about> 	
        작성자: <p>${list.recipe_Writer }</p>
        <br><br>
     </div>
     
       파일: <input type="file" id="input_img" name="uploadFile" >
    	<div class="container"  style="text-align: center;">
        <img id="img" src="img/${list.recipe_Image_Realname}" width="90%" height="440px">
     	</div>
    	<input type="hidden" name="previous_image" value="${list.recipe_Image_Realname}">
     
    
    <br><br><br><br>
    <div id="content">
    <h2 id="title2">재료</h2>
    <br>
    <textarea rows="20" cols="130" name="ingredient">${list.recipe_Ingredient}</textarea>
    <br>
    <br>
    <br>
    </div>
    
    <h2 id="title2">내용</h2>
    <textarea rows="50" cols="130" name="content">${list.recipe_Text }</textarea>
    <br><br>
    
    <input class="button" type="submit" value="저장"> 
    <input class="button" type="button" value="취소" onclick="location.href='userrecipecontroller.do?command=list'">
    </div>
    </div> 
    </form>
    
    <div id="copyright" class="container">
        <p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
    </div>
   

</body>
</body>
</html>