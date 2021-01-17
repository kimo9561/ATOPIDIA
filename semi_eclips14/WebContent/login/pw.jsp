<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function pw_search() { //아이디, 주민번호로 찾기 
   var doc=document.getElementsByName("id")[0];
   var doc2=document.getElementsByName("rrn")[0].value;
   var doc3=document.getElementsByName("rrn1")[0].value;
   
   var db=doc2+"-"+doc3;
   console.log(db);
   if(doc.value.trim()==""||doc.value==null){
      alert("아이디를  입력해주세요");
      return false;
   }
    if(doc2.trim()==""||doc2==null){
      alert("주민번호 앞자리를 입력해주세요");
      return false;
   }
    if(doc3.trim()==""||doc3==null){
        alert("주민번호 뒷자리를  입력해주세요");
        return false;
    }
    validate();

         var target="../membercontroller.do?command=searchPw&id="+doc.value.trim()+"&rrn="+db;
            open(target,"","width=800,height=700");

 
}
function validate(){
    var num1 = document.getElementsByName("rrn")[0];
     var num2 = document.getElementsByName("rrn1")[0];
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
          return false;
      }
      self.close();
  }
</script>
 <style type="text/css">
<%@include file="pw.css"%>
</style>

</head>
<body>
 <div id="login_title">
         <h1>비밀번호찾기 </h1>

</div>
   <table id="searchpw">
          <tr>
           <td><img src="../img/search.png">비밀번호찾기 | 회원정보에 등록한 정보로 인증.&nbsp;<br> &nbsp;</td>
          </tr>

    <tr>
        <td>아이디,주민등록번호로 찾기 </td>
    </tr>

      <tr>
          <td>아이디&nbsp;<input type="text" name="id"></td>
      </tr>
	<col width="500">
   <tr>   
   		
      <td>주민등록번호 <input type="text" name="rrn" maxlength="6">-<input type="password" name="rrn1"maxlength="7"></td>
     
   </tr>   
   <tr>
          <td><input type="button" id="btn" value="찾기  "onClick="pw_search();"> &nbsp;<input type="button"value="취소 " id="btn" onclick="self.close();"></td>
   </tr>

    </table>




</body>
</html>