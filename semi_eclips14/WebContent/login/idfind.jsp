<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
function idsearch() { //이름,이메일로 '찾기' 버튼
	var doc=document.getElementsByName("name")[0];
	
	var emailtitle = $("#email1").attr('title');
	var email = $("#email1").val()+"@"+$("#email2").val();
	if(doc.value.trim()==""||doc.value==null){
		alert("이름을 입력해주세요");
		return false;
	}
	 if(emailtitle=="n"){
		alert("이메일인증번호 확인해주세요");
		return false;
	}else{
		   var target="../membercontroller.do?command=searchid&name="+doc.value.trim()+"&email="+email;
		   open(target,"","width=900,height=700");
		   self.close();
	}
}
function cancel(){
	self.close();
}

function sendnum() {
	 var email = $("#email1").val()+"@"+$("#email2").val();
	 if(email==""||email==null){
			alert("이메일을 입력해주세요");
			return false;
		}
	 if(! email_check(email)){
		 alert("올바른 이메일을 입력해주세요");
		return false;
	 }
       $.ajax({
          type: 'GET',
          url: "../membercontroller.do?command=sendnum",
          data:{email: email},
          success:function(ramdom){
             var msg = "존재하지않는이메일";
             if(ramdom===msg){
                alert(msg);
             }else{
                $("#code").html("<td id='ssss'><input type='text' id='num'><input type='button' value='인증확인' id='btn1' onclick='numchk();'><input type='hidden' id='num2' value='"+ramdom+"'><td>");
                alert("인증번호가 전송되었습니다.");
                document.getElementById("email1").title="n";
             }
          },
          error:function(){
             alert("ajax 통신 실패");
          }
    }); 
 }
 function numchk() {
    var num = $("#num").val();
    var num2 = $("#num2").val();
    console.log(num);
    console.log(num2);
    if(num===num2){
       alert("인증되었습니다");
       $("#ssss").css('display','none');
       document.getElementById("email1").title="y";
    }else{
       alert("인증번호가 틀립니다");
    }
 }
 function email_check( email ) {
	    
	    var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	    return (email != '' && email != 'undefined' && regex.test(email));

	}
</script>
 <style type="text/css">
<%@include file="idfind.css"%>
</style>
</head>
<body>
	<div id="login_title">
      <h1>
            아이디 찾기 
       </h1>
	</div>
   <table id="searchid">
          <tr>
           <td ><img src="../img/search.png">아이디 찾기&nbsp;&nbsp;|&nbsp;회원정보에 등록한 정보로 인증.&nbsp;<br> &nbsp;</td>
  
          </tr>
      <col width="450">
             <tr>
              <td>&nbsp;이름,이메일로 찾기</td>
             </tr>
            <tr>
             <td >이름&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name" id="name1"title="n"></td>
            </tr>

            <tr class="email">
             <td>email&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="email"id="email1" > @ <input type="text" name="email" id="email2" > </td>
             <td>
              <input type="button" id="btn"value="인증번호 전송" onclick="sendnum();">
             </td>
         <tr id="code">
             </tr>
           
            </tr>

             <tr>
              <td><input type="button" id="search" value="찾기  " onClick="idsearch();">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"value="취소  "id="cancel" onClick="cancel();"></td>
             </tr>


    </table>





</body>
</html>