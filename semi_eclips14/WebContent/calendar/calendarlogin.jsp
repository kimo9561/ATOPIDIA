<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>  
<% response.setContentType("text/html; charset=UTF-8"); %> 
<%@ page import="java.util.List" %> 
<%@ page import="model.Dto.CalendarDto" %> 
<%@ page import="model.Dto.CalendarDiaryDto" %> 
<%@page import="model.Dto.MemberDto" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Calendar</title>
<% List<CalendarDto> list = (List<CalendarDto>)request.getAttribute("list");%>
<% List<CalendarDiaryDto> dlist = (List<CalendarDiaryDto>)request.getAttribute("dlist");%>
<%MemberDto dto=(MemberDto)session.getAttribute("dto");
   if(dto==null){
%>
   <script type="text/javascript">
   alert("로그인을 하고 사용해 주세요.");
   location.href="recipecontroller.do?command=mainlogout"
   </script>
<% 
   }
%>

<style type="text/css">
   <%@include file="../css/default.css"%>
   <%@include file="fullcalendar.min.css"%>
   #wrapper {
      padding: 50px;
      margin: 85px;
   }
   input{
      width: 140px;
      height: 35px;
      -webkit-border-radius: 5px;
      -moz-border-radius: 5px;
      border-radius: 5px;
      font-size: 15px;
   }
   .btn{
      width: 95px;
      height: 38px;
      color: #fff;
      text-align: center;
      font-weight: 600;
      background: #2C383B;
      -moz-transition: opacity 0.25s ease-in-out;
      -webkit-transition: opacity 0.25s ease-in-out;
      -o-transition: opacity 0.25s ease-in-out;
      -ms-transition: opacity 0.25s ease-in-out;
      transition: opacity 0.25s ease-in-out;
      letter-spacing: 0.20em;
      text-decoration: none;
      text-transform: uppercase;
   }  
   .btn:hover{
      background-color:  rgb(34, 43, 46);
      color: #ECECEC;
   }  
</style>

<style media="print">
   <%@include file="fullcalendar.print.min.css"%>
</style>
<script type="text/javascript">
   <%@include file="jquery-3.5.1.min.js"%>
</script>
<script>
<%@include file="moment.min.js"%>
<%@include file="fullcalendar.min.js"%>
<%@include file="jquery.bpopup.min.js"%>
<jsp:include page="../calendar/ko.js"/>
</script>

<script>
   $(document).ready(function() {
      $('#calendar').fullCalendar({
         header: {
         left: 'prev,next today',
         center: 'title',
         right: 'month,listMonth'
         },

         navLinks: true,
         eventLimit: true,
         events:[
             <%
             for(CalendarDto clist : list){
          	%>
             {
                id: '<%=clist.getCalendar_no()%>',
                color: '#2C383B',
                title: '<%=clist.getCalendartitle()%>',
                  start: '<%=clist.getCalendardate()%>',
                  description: '<%=clist.getContent()%>'
             },
          <%
             }
          %>
          <%
          for(CalendarDiaryDto dclist : dlist){
			%>
          {
             
         	 title: '<%=dclist.getCalendartitle()%>',
         	color : 'gold',
         	textColor : '#2C383B',
         	 url: '<%=dclist.getUrl()%>',
              start: '<%=dclist.getCalendardate()%>'

          },
       <%
          }
       %>
       	{
           title: '나만의 다이어리',
           color : 'gold',
           textColor : '#2C383B',
           url : 'diarycontroller.do?command=diarylist&member_no=<%=dto.getMember_No()%>',
           start: '2020-11-01'         
        },
       	{
            title: '병원을 예약해보세요',
            color: '#2C383B',
            start: '2020-11-01'         
         }

          ],
          eventClick:function(event) {
        	  if(event.url) {
                  const result = confirm("다이어리 "+event.title+"로 이동합니다.");
                  if(result){
                     location.href = event.url;      
                  }else{
                     return false;
                  }
               }else if(event) {
                  if(event.title=="병원을 예약해보세요"){
                     addSchedule();
                     return false;
                        }else{
                             var times = moment(event.start).format('YYYY년 MM월 DD일 hh:mm');
                             bpopevents(event.title, times, event.description, event.id)
                        }

                     }
              }
       });
   });
   
   function addSchedule() {
      var htmlcode = "";
      htmlcode += "<br><div style='width: 100%; height: 50px'><div style='width: 25%; float: left; padding-top: 10px; padding-left: 30px'>병원 이름</div><div style='width: 65%; float: right'><input type='text' id='calendar_title' value=''><div style='float: right; padding-right: 10px'><button onclick='map();' class='btn'>병원찾기</button></div></div></div>";
      htmlcode += "<div style='width: 100%; height: 50px'><div style='width: 25%; float: left; padding-top: 10px; padding-left: 30px'>예약 시간</div><div style='width: 65%; float: right'><input type='date' id='r_date'></div></div>";
      htmlcode += "<div style='width: 100%; height: 50px'><div style='width: 25%; float: left; padding-top: 10px; padding-left: 30px'>예약 시간</div><div style='width: 65%; float: right'><input type='time' id='r_time' min='09:00' max='17:30' step='1800' value='09:00'></div></div>";
      htmlcode += "<div style='width: 100%; text-align:center; hright:30px; margin-bottom:15px; margin-top:10px'><button class='button' onclick=\"javascript:saveSchedule();\">예약하기</button></div>";
      openPopup("예약하기", htmlcode, 400);
   }
   function updateSchedule(id){
      var htmlcode = "";     
      htmlcode += "<br><div style='width: 100%; height: 50px'><div style='width: 25%; float: left; padding-top: 10px; padding-left: 30px'>예약 시간</div><div style='width: 65%; float: right'><input type='date' id='u_date'></div></div>";
      htmlcode += "<div style='width: 100%; height: 50px'><div style='width: 25%; float: left; padding-top: 10px; padding-left: 30px'>예약 시간</div><div style='width: 65%; float: right'><input type='time' id='u_time' min='09:00' max='17:30' step='1800' value='09:00'></div></div>";
      htmlcode += "<div style='width: 100%; text-align:center; hright:30px; margin-bottom:15px; margin-top:10px'><button class='button' onclick=\"javascript:r_update("+id+");\">예약변경</button></div>";
      openUpdate("예약변경", htmlcode, 400);
   }
   function bpopevents(title, start, content, id){
      console.log(id);
      var titlea = title;
      console.log(titlea);
      var htmlcode = "";
      htmlcode += "<br><div style='width: 100%; height: 30px'><div style='width: 30%; float: left; padding-left: 30px'>병원 이름</div><div style='width: 60%; float: right'>"+title+"</div></div>";
      htmlcode += "<div style='width: 100%; height: 30px'><div style='width: 30%; float: left; padding-left: 30px'>예약시간</div><div style='width: 60%; float: right'>"+start+"</div></div>";
      htmlcode += "<div style='width: 100%; height: 30px'><div style='width: 30%; float: left; padding-left: 30px'>병원정보</div><div style='width: 60%; float: right'>"+content+"</div></div>";
      htmlcode += "<br><div style='width: 100%; text-align:center; hright:30px; margin-bottom:15px; margin-top:10px; padding-top: 10px;'><button class='button' onclick=\"javascript:closeMessage('eventAlert');\">확인</button><button class='button' onclick=\"javascript:r_delete("+id+");\">예약취소</button><button class='button' onclick=\"javascript:updateSchedule("+id+");\">예약변경</button></div>";
      openEvents("예약정보", htmlcode, 500);
    }
   function openPopup(subject,contents,widths) {
      $("#alert_subject").html(subject);
      $("#alert_contents").html(contents);
      openMessage('winAlert',widths)
   }
    function openEvents(subject,contents,widths) {
      $("#c-title").html(subject);
      $("#c-content").html(contents);
      openMessage('eventAlert',widths)
   }
   function openUpdate(subject,contents,widths) {
      $("#u-title").html(subject);
      $("#u-content").html(contents);
      openMessage('updateAlert',widths)
   }
     function openMessage(IDS,widths) {
      $('#'+IDS).css("width",widths+"px");
      $('#'+IDS).bPopup();
   }
     function closeMessage(IDS) {
      $('#'+IDS).bPopup().close();
   }
    function saveSchedule() {
      var calendar_title = $("#calendar_title").val();
      var r_date = $("#r_date").val();
      var r_time = "T"+$("#r_time").val();   
      if(!calendar_title){
         alert("병원이름을 입력해주세요");
         return false;
      }
      if(!r_date){
         alert("날짜 입력해주세요");
         return false;
      }
      $.ajax({
         type: 'GET',
         url: "calendarcontroller.do?command=reservation&member_no=<%=dto.getMember_No()%>",
         data:{calendar_title: calendar_title, r_date: r_date, r_time: r_time},
         success:function(msg){
            alert(msg);
            console.log(msg);
            closeMessage('winAlert');
            location.reload();
         },
         error:function(){
            alert("ajax 통신 실패");
         }
      });   
   }
   function r_delete(id) {
      console.log(id);
         $.ajax({
            type: 'GET',
            url: "calendarcontroller.do?command=delete&member_no=<%=dto.getMember_No()%>",
            data:{id: id},
            success:function(msg){
               alert(msg);
               console.log(msg);
               closeMessage('eventAlert');
               location.reload();
            },
            error:function(){
               alert("ajax 통신 실패");
            }
      });      
   }
   function r_update(id) {
      var u_date = $("#u_date").val();
      var u_time = "T"+$("#u_time").val();
      console.log(id);
      if(!u_date){
      alert("날짜 입력해주세요");
      return false;
      }
      $.ajax({
         type: 'GET',
         url: "calendarcontroller.do?command=update&member_no=<%=dto.getMember_No()%>",
         data:{id: id, u_date: u_date, u_time: u_time},
         success:function(msg){
            alert(msg);
            console.log(msg);
            closeMessage('updateAlert');
            location.reload();
         },
         error:function(){
            alert("ajax 통신 실패");
         }
   });
}
   function map() {
      location.href="mapcontroller.do?command=map&memberId=${dto.member_Id }";
}
</script>
</head>
<body>
<jsp:include page="../nav/navlogin.jsp"></jsp:include>
<div id="wrapper">   
      <div id='calendar'></div>
      <div id="extra2" class="container">
         <a class="button" onclick="addSchedule();">병원 예약하기</a>
      </div>
      <div class="box box-success" style="width:500px; display:none; background-color:white" id="winAlert">      
         <div class="box-header with-border" style="background-color:white; padding-left:15px; padding-top: 10px">
            <h3 class="box-title" id="alert_subject" style="background-color:white"></h3>
         </div>
         <div class="box-body" id="alert_contents" style="font-size:15px; background-color:white"></div>
      </div>
      <div class="event box-success" style="width:500px; display:none; background-color:white" id="eventAlert">      
         <div class="box-header with-border" style="background-color:white; padding-left:15px; padding-top: 10px">
            <h3 class="box-title" id="c-title" style="background-color:white"></h3>
         </div>
         <div class="box-body" id="c-content" style="font-size:15px; background-color:white"></div>
      </div>
      <div class="update box-success" style="width:500px; display:none; background-color:white" id="updateAlert">      
         <div class="box-header with-border" style="background-color:white; padding-left:15px; padding-top: 10px">
            <h3 class="box-title" id="u-title" style="background-color:white"></h3>
         </div>
         <div class="box-body" id="u-content" style="font-size:15px; background-color:white"></div>
      </div>
   </div>
   <div id="copyright" class="container">
      <p>
         &copy; Untitled. All rights reserved. | Photos by <a
            href="http://fotogrph.com/">Fotogrph</a> | Design by <a
            href="http://templated.co" rel="nofollow">TEMPLATED</a>.
      </p>
   </div>
</body>
</html>