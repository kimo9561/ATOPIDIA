<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
 
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
<style type="text/css">
	<%@include file="../../css/default.css" %>	
</style>
</head>
<body>
<%@include file="../../nav/navlogin.jsp"%>
 	 <div id="write">
       
         
          <a href="../adminrecipecontroller.do?command=list" class="button"> 취소</a>
        </div>
    <div><br><br><br></div>
 
    <h2 id="title1">레시피 작성하기</h2>
    <form action="../adminrecipecontroller.do?command=write&member_No=${dto.member_No}" method="post" enctype="multipart/form-data">
	
      <div id="info">
      <div id="featured-wrapper">
    <h2>제목: <textarea rows="1" cols="100" name="recipe_title"></textarea>
    </h2>
    <br>
    <div id=about> 	
        작성자: <input name="recipe_author" value="${dto.member_Id }" readonly="readonly">
        <br><br>
     </div>
    
    <br><br><br><br>
    <div >
   사진 파일:<input id=input_img type="file" name="file" id="imageFileOpenInput"
				accept="image/*">
				<br>
		  <img id="img" width="90%" height="440px">	
		</div>
    <div id="content">
    <h2 id="title2">재료</h2>
    <br>
    <textarea rows="20" cols="130" name="ingredient"></textarea>
    <br>
    <br>
    <br>
    </div>
    
    <h2 id="title2">내용</h2>
    <textarea rows="50" cols="130" name="content"></textarea>
    <br><br>
    <input class="button" type="submit" value="저장"> 
    <input class="button" type="button" value="취소" onclick="location.href='../adminrecipecontroller.do?command=list'">
    <br><br>

    
    </div>
    </div> 
    </form>
    <div id="copyright" class="container">
        <p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
    </div>
   


    
</body>
</body>
</html>