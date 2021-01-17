<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html;charset=UTF-8");%>
<%@page import="model.Dto.MemberDto"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
   function allChk(bool) {
      var chks = document.getElementsByName("chk");
      for(var i=0; i<chks.length; i++){
         chks[i].checked = bool;
      }
   }
   function deletemember(){
      if($("#info input:checked").length==0){
         alert("하나 이상 체크해 주세요!");
         return false;
      }
      var chks = document.getElementsByName("chk");
      var chkn = "";
      for(var i=0; i<chks.length; i++){
         if(chks[i].checked){
            chkn += chks[i].value;
            if(!(i==chks.length-1)){
               chkn += "/";
            }
         }
      }
      location.href="membercontroller.do?command=deletemember&chk="+chkn;
   }
</script> 
<style type="text/css" >
   		
        <%@include file="UserListAll.css"%>


</style>
</head>
<%List<MemberDto> list=(List<MemberDto>)request.getAttribute("list");%>

<body>
   <h1>
      회원 정보 조회 (All)
   </h1>

   <table border="3" id="info">

    <tr >
       <td rows="5" colspan="3" id="tab"><input type="checkbox" name="all" onclick="allChk(this.checked);"></td>
       <td rows="5" colspan="3" id="tab">번호</td>
       <td rows="5" colspan="3" id="tab">아이디 </td>
       <td rows="5" colspan="3" id="tab">비밀번호 </td>
       <td rows="5" colspan="3" id="tab"> 이름 </td>
       <td rows="5" colspan="3" id="tab">이메일</td>
       <td rows="5" colspan="3" id="tab">주민번호 </td>
       <td rows="5"colspan="3" id="tab">  등급  </td>
    </tr>
<% for (MemberDto dto:list){ %>
     <tr>
       <td colspan="3" id="tab"><input type="checkbox"name="chk" value="<%=dto.getMember_No() %>"></td>
       <td colspan="3" id="tab"><%=dto.getMember_No() %></td>
       <td colspan="3" id="tab"><%=dto.getMember_Id() %></td>
       <td colspan="3" id="tab"><%=dto.getMember_Pw() %></td>
       <td colspan="3" id="tab"><%=dto.getMember_Name() %></td>
       <td colspan="3" id="tab"><%=dto.getMember_Email() %></td>
		<td colspan="3" id="tab"><%=dto.getMember_RRN().substring(0, 7)%>*******</td>
       <td colspan="3" id="tab"><%=dto.getGrade_Code() %></td>
       
    </tr>

<%      
      } 
%>
      <tr>
         <td colspan="24" >
            <button class="button" onclick="location.href='membercontroller.do?command=adminpage'">메인</button>
            <button class="button" onclick="deletemember();">탈퇴처리</button>
         </td>
         
      </tr>    

   </table>

</body>
</html>