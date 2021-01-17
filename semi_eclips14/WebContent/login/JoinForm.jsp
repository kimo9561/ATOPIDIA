<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
%>
<%
   response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html>
<html >   
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
   <style type="text/css">
   <%@include file="../css/default.css"%>
   <%@include file="login.css"%>
   <%@include file="signupform.css" %>
   </style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">


//아이디 중복체크
function ChkConfirm() {
	var chk = document.getElementsByName("userId")[0].title;
	if (chk =="n") {
		alert("아이디 중복체크를 해주세요");
		document.getElementsByName("userId")[0].focus();
	}
}
function ChkConfirm2() {
	var chk2 = document.getElementById("userPwConfirm").title;
	var chk = document.getElementsByName("userId")[0].title;
	if (chk2 =="n") {
		alert("비밀번호확인을 해주세요");
		document.getElementsByName("userPw")[0].focus();
	}
	if(chk =="n") {
		document.getElementsByName("userId")[0].focus();
	}
}
function chkId(){
   var doc=document.getElementsByName("userId")[0];
   //trim:공백제거
   if(doc.value.trim()=="" || doc.value==null){
      alert("아이디를 입력해주세요");
   }else{
      var target="../membercontroller.do?command=IdChk&id="+doc.value.trim();
      open(target,"","width=200,height=200");//타겟을 띄우면서 콘트롤러에 저장
   }
}
   //비밀번호 확인.
   function checkpass() {
      
      
      var objuserPw = document.getElementById("userPw");
      var objusePwConfirm = document.getElementById("userPwConfirm");

      if (objuserPw.value != objusePwConfirm.value || objuserPw.value == "") {

         alert("패스워드가 다릅니다. 다시입력해주세요");
         objuserPw.value = "";
         objusePwConfirm.value = "";
         objuserPw.focus();
         return false;

      } else {
    	  var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

    	  if(false === reg.test(objuserPw.value)) {
    	  alert('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
    	  document.getElementById("userPwConfirm").title="n";
    	  }else {
    	  alert('올바른 비밀번호 입니다.');
          document.getElementById("userName").focus();
          document.getElementById("userPwConfirm").title="y";
          return true;
    	  }

      }
   }
   function validate(){
         var num1 = document.getElementById("unum1");
          var num2 = document.getElementById("unum2");
          var arrNum1 = new Array(); // 주민번호 앞자리숫자 6개를 담을 배열
          var arrNum2 = new Array();
          
          for (var i=0; i<num1.value.length; i++) {
               arrNum1[i] = num1.value.charAt(i);
           } // 주민번호 앞자리를 배열에 순서대로 담는다.

           for (var i=0; i<num2.value.length; i++) {
               arrNum2[i] = num2.value.charAt(i);
           } // 주민번호 뒷자리를 배열에 순서대로 담는다.

           var tempSum=0;

           for (var i=0; i<num1.value.length; i++) {
               tempSum += arrNum1[i] * (2+i);
           } // 주민번호 검사방법을 적용하여 앞 번호를 모두 계산하여 더함

           for (var i=0; i<num2.value.length-1; i++) {
               if(i>=2) {
                   tempSum += arrNum2[i] * i;
               }
               else {
                   tempSum += arrNum2[i] * (8+i);
               }
           } // 같은방식으로 앞 번호 계산한것의 합에 뒷번호 계산한것을 모두 더함

           if((11-(tempSum%11))%10!=arrNum2[6]) {
               alert("올바른 주민번호가 아닙니다.");
               num1.value = "";
               num2.value = "";
               num1.focus();
               document.getElementById("unum1").title="n";
               return false;
           }else{
              document.getElementById("unum1").title="y";
              alert("올바른 주민등록번호 입니다.");
           }
       }
   
function signup() {
	var userIdconfirm = document.getElementById("userIdconfirm").title;
	var userPwconfirm = document.getElementById("userPwConfirm").title;
    var userName = $("#userName").val();   
    var email = document.getElementById("userMail").title; 
	var unum = document.getElementById("unum1").title;
	var signupform = document.getElementById("signupform");
    if(userIdconfirm=="n"){
       alert("아이디 중복확인해주세요");
       return false;
    }
    if(userPwconfirm=="n"){
       alert("비밀번호확인해주세요");
       return false;
    }
    if(!userName){
        alert("이름을 입력해주세요");
        return false;
     }
    if(email=="n"){
        alert("이메일 인증해주세요");
        return false;
     }
    if(unum=="n"){
        alert("주민번호 검사해주세요");
        return false;
     }
    signupform.submit();
}
</script>
<script type="text/javascript">
function email_check( email ) {
    
    var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return (email != '' && email != 'undefined' && regex.test(email));

}
function sendemail() {
	
   var email = $("#userMail").val()
   if(! email_check(email)){
		 alert("올바른 이메일을 입력해주세요");
		return false;
	 }
      $.ajax({
         type: 'GET',
         url: "../membercontroller.do?command=sendemail",
         data:{email: email},
         success:function(ramdom){
            $("#code").html("<input type='text' id='num'><input type='button' value='인증확인' onclick='numchk();'><input type='hidden' id='num2' value='"+ramdom+"'>");
            document.getElementById("userMail").title="n"
            alert("인증번호가 전송되었습니다.");

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
      document.getElementById("userMail").title="y";
      $("#ssss").css('display','none');
   }else{
      alert("인증번호가 틀립니다");
   }
}
</script>



<%@include file="../nav/navlogout.jsp" %>
<body>
   <div id=wrapper>
      <div id="login_title">
      <h1 id="login_title">
              회원가입 
         </h1>

      <p class="sub">
         <span class="ico">
            *
         </span>
         필수입력 
      </p>
      </div>
   

   <div style="padding:7%; ">
   <form action="../membercontroller.do"  method="post" id="signupform"> 
   <input type="hidden" name="command" value="signup"> 
   <table border="0" cellpadding="0" cellspacing="0">
   <caption align="right">
   <font color="white">
      ..
   </font>

   
   </caption>

   <tr>
       <th align="right" width="100">
       아이디
       <span class="ico">
           *
        </span>
      <td colspan="4">
             <input type="text" name="userId" maxlength="12"
             id="userIdconfirm" placeholder="4~12자리의 영문으로 입력 " title="n" >
        <input type="button" id="id"  value="중복확인" onclick="chkId();"><br>
          </td>
    
        <td valign="top">&nbsp;&nbsp;<font color="red">
        
        </font></td>
        <td width="600">

   </tr>
   
   <tr>
    <th align="right" width="100">
         비밀번호
            <span class="ico">
               *
             </span>
          <td colspan="4">
             <input type="password" name="userPw" maxlength="20" id="userPw"
                placeholder="영문 대소문자+숫자+특수문자 조합으로 8자리 ~ 20자리 입력 " onclick="ChkConfirm();" >
          </td>
       <td valign="top">&nbsp;&nbsp;<font color="red">
       
       </font></td>
       <td width="600">  
        
      </tr>
        <tr>
    <th align="right" width="100">
         비번확인 
          <span class="ico">
               *
             </span>
         <br>
          <td colspan="4">
             <input type="password" name="userPwConfirm" maxlength="20" id="userPwConfirm" title="n" onclick="ChkConfirm();">
             <input type="button" id="password" value="비밀번호 확인 " onclick="checkpass();">  
          </td>
       <td valign="top">&nbsp;&nbsp;<font color="red">
       
       </font></td>
       <td width="600">  
        
      </tr>
     <tr>
    <th align="right" width="100">
                이름 
            <span class="ico">
               *
             </span>
          <td colspan="4">
             <input type="text" name="userName" maxlength="20" id="userName"
                placeholder="" onclick="ChkConfirm(),ChkConfirm2()"><br>  
          </td>
       <td valign="top">&nbsp;&nbsp;<font color="red">
       
       </font></td>
       <td width="600">  
        
      </tr>  
      
   <tr>
       <th align="right" width="100">
                 메일주소
            <span class="ico">
                  *
             </span>
 
       <td valign="top">
          <font color="red"></font>
          </td>
       <td width="600">  
        <input type="text" name="userMail" id="userMail" title="n" size="30" placeholder=예:id@naver.com>&nbsp;&nbsp;
        <input type="button" value="인증번호 전송 " onclick="sendemail();">  
        </td>
        
      </tr>
      <tr id="ssss">
         <th align="right" width="30">
               인증번호  
         <span class="ico">
            *
          </span>     
      
           <td valign="top"><font color="red">
           </font>   </td>
          <td width="600" id="code">
          </td>   
   </tr>
   <tr>
    <th align="right" >
               주민번호   
        <span class="ico">
            *
          </span>
    <td colspan="10">
         <input type="text" name="unum1" id="unum1" title="n" maxlength="6">-
         <input type="password" name="unum2" id="unum2"maxlength="7">
      <br><input type="button" id="chk" value="검사 " onclick="validate();"style="border-radius:5px;">
      <input type="reset" id="chk" value="다시입력" style="border-radius:5px;" />
       
       </td>
      
      
        <td valign="top"><font color="red">
       
       </font></td>
   </tr>

  </table>
      <br><br>
   
        <input type="button" id="sg" value="회원가입" onclick="signup();">
      
    </form>



   </div>
   </div>
  <div id="copyright" class="container">
      <p>
         &copy; Untitled. All rights reserved. | Photos by <a
            href="http://fotogrph.com/">Fotogrph</a> | Design by <a
            href="http://templated.co" rel="nofollow">TEMPLATED</a>.
      </p>
   </div>
   
</html>