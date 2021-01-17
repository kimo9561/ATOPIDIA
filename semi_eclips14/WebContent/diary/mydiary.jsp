<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%>
<%@
	page import="com.oreilly.servlet.MultipartRequest"
%>
<%@
 	page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Diary</title>
<style type="text/css">
   <%@include file="../css/default.css"%>
   <%@include file="diarycss.css"%>
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="diaryjs.js">
</script>   
<script type="text/javascript">
	var sel_file;
	$(document).ready(function(){
		
	    var today = new Date();
	      var dd = today.getDate();
	      var mm = today.getMonth() + 1; //January is 0!
	      var yyyy = today.getFullYear();

	      if (dd < 10) {
	         dd = '0' + dd;
	      }
	      if (mm < 10) {
	         mm = '0' + mm;
	         
	      }
	      today = yyyy + '-' + mm + '-' + dd;
	      document.getElementById("datePicker").defaultValue = today + "";
	      $("#sibal").on("change", handleImgFileSelect);
	});
	
	function handleImgFileSelect(e){
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		
		/* if(files.name == null){
			resetInputFile();
		} */
		filesArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}
			sel_file = f;
			
			var reader = new FileReader();
			reader.onload = function(e){
				$("#img").attr("src", e.target.result);
				$("#img").attr("width", "500px");
				$("#img").attr("height", "500px");
			}
			reader.readAsDataURL(f);
		})
	}
	
	function resetInputFile(){
		$("#img").empty();
	}
	function morningChk(){
	      $("#right_title").html("아침");
	      $("#morning").css("display", "block");
	      $("#lunch").css("display", "none");
	      $("#dinner").css("display", "none");
	      $("#comment").css("display", "none");
	      $("#img").css("display", "none");
	   }
	   function lunchChk(){
	      $("#right_title").html("점심");
	      $("#lunch").css("display", "block");
	      $("#morning").css("display", "none");
	      $("#dinner").css("display", "none");
	      $("#comment").css("display", "none");
	      $("#img").css("display", "none");
	   }
	   function dinnerChk(){
	      $("#right_title").html("저녁");
	      $("#dinner").css("display", "block");
	      $("#morning").css("display", "none");
	      $("#lunch").css("display", "none");
	      $("#comment").css("display", "none");
	      $("#img").css("display", "none");
	   }
	   function commentChk(){
	      $("#right_title").html("코멘트");
	      $("#comment").css("display", "block");
	      $("#lunch").css("display", "none");
	      $("#dinner").css("display", "none");
	      $("#morning").css("display", "none");
	      $("#img").css("display", "none");
	   }
	   function imageChk(){
	      $("#right_title").html("사진");
	      $("#morning").css("display", "none");
	      $("#lunch").css("display", "none");
	      $("#dinner").css("display", "none");
	      $("#comment").css("display", "none");
	      $("#img").css("display", "block");
	   }

</script>
</head>
<body>
   <%@ include file="../nav/navlogin.jsp" %>
 	   <form action="../diarycontroller.do?command=insertdiary&diary_No=${ddto.diary_No}&member_No=${dto.member_No}" method="post" enctype="multipart/form-data">
   <div id="header-wrapper">
 	   <div style="background-color:#2C383B;"><br><br><br></div>
          <h2 style="text-align: center; color: black;">나만의 다이어리</h2>
          <hr>
       <div>
           <input type="submit" class="btnRelativezoom" value="저장">
          <input type="button" class="btnRelativezoom" value="취소" onclick="location.href='../diarycontroller.do?command=diarylist&member_no=${dto.member_No}'">
           <input type="button" class="btnRelativezoom" value="메인" onclick="location.href='../diarycontroller.do?command=diarylist&member_no=${dto.member_No}'">
       </div>
       <div>
       </div>
       <h1 style="color: black; text-align: center;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;나만의 식단과 일정을 적어보세요 </h1>
   </div>
        <div id="diaryTitle">
            <input type="text" name="diary_Title" placeholder="제목읍 입력해주세요." style="text-align:center;">
         </div>
         <div id="diary_session">
         <div class="btnRelativezoom">
             <span>날짜 입력 : </span><input type="date" name="diary_Date" id="datePicker" readonly="readonly">
         </div>
            <div id="diary_left">
               <ul>
                  <li onclick="morningChk();">아침</li>
                  <li onclick="lunchChk();">점심</li>
                  <li onclick="dinnerChk();">저녁</li>
                  <li onclick="commentChk();">코멘트</li>
               </ul>
               <table id="diary_button">
                  <tr>
                     <td>
                        <input type="file" id="sibal" name="uploadFile" value="사진 고르기" onclick="imageChk();">
                     </td>
                  </tr>
               </table>
            </div>
            <div id="diary_right">
            <p id="right_title"></p>
            <textarea class="diaryclass" id="morning" rows="15" cols="30" name="diary_Content_Morning"></textarea>
            <textarea class="diaryclass" id="lunch"rows="15" cols="30" name="diary_Content_Lunch"></textarea>
            <textarea class="diaryclass" id="dinner"rows="15" cols="30" name="diary_Content_Dinner"></textarea>
            <textarea class="diaryclass" id="comment" rows="15" cols="30" name="diary_Content_Recipe"></textarea> 
         <img id="img">
            <input type="hidden" name="previous_image" value="${ddto.diary_Image_RealName}">
         </div>

      </div>
   </form>

   	<div id="copyright" class="container" style="position: relative; top:50%;">
		<p>
			&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.
		</p>
   </div>

</body>
</html>