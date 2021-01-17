<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Dto.MemberDto" %>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>

<%@ page import="model.Dto.MapDto" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%MemberDto dto=(MemberDto)session.getAttribute("dto");
	if(dto==null){
%>
	<script type="text/javascript">
	alert("로그인을 하고 사용해 주세요.");
	location.href="../maincontroller.do?command=mainlogout"
	</script>
<% 
	}
%>

<script type="text/javascript">
   <%@include file="jquery-3.5.1.min.js"%>
   <%@include file="jquery.bpopup.min.js"%>
</script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ec6f5fe1dd0c79d9c1641da8d96ad3e5" charset="euc-kr"></script>


<script type="text/javascript">
   $(function() {
       
      $("#fixed table tr").click(function() {
         var hos_name = $(this).children().eq(1).children().eq(0).val();
         var hos_addr = $(this).children().eq(1).children().eq(1).val();
         var hos_tel = $(this).children().eq(1).children().eq(2).val();
         var latitude = $(this).children().eq(1).children().eq(3).val();
         var longitude = $(this).children().eq(1).children().eq(4).val();
         
         // 병원 상세내용 영역 비우기
         $("#hospital_desc").empty();
         // 병원 상세내용 추가
         $("#hospital_desc").append(
            "<h2>"+hos_name+"</h2>"+"<br>"+
            "<div>"+
            "<h3>"+hos_addr+"</h3>"+"<br>"+
            "<h2>Tel: "+hos_tel+"</h2>"+
            "</div>"
         );
      
         $("#map").children().remove();
         
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
         mapOption = { 
              center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
              level: 3 // 지도의 확대 레벨
          };

      var map = new kakao.maps.Map(mapContainer, mapOption);

      // 마커가 표시될 위치입니다 
      var markerPosition  = new kakao.maps.LatLng(latitude, longitude); 

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
          position: markerPosition
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);

      var iwContent = '<div id="mapInfo">'+hos_name+' <br><a href="https://map.kakao.com/link/map/'+hos_name+','+latitude+','+longitude+'" target="_blank">큰 지도로 보기</a>&nbsp&nbsp&nbsp<a href="https://map.kakao.com/link/to/'+hos_name+','+latitude+','+longitude+'" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
          iwPosition = new kakao.maps.LatLng(latitude, longitude); //인포윈도우 표시 위치입니다

      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
          position : iwPosition, 
          content : iwContent 
      });
        
      // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
      infowindow.open(map, marker); 
      
      $("#mapInfo").parent().parent().children().first().remove();
      $("#mapInfo").parent().parent()
      .css({"width":"220px", "height":"60px", "opacity":"1", "padding":"0px", "margin":"0px"});
      $("#mapInfo").parent().css({"width":"220px", "height":"60px", "text-align":"center",
    	  		"font-weight":"bolder"});
      
      
      });// 병원 클릭 함수 
      
   });// 로딩 후 실행 코드
   
   function reservation() {
      if($.trim($("#hospital_desc").html()) == '') {
         alert("병원 선택 후 다시 예약해주세요!");
      }else {
         var hos_name = $("#hospital_desc").children().eq(0).text();
         var hos_desc = $("#hospital_desc").children().eq(2).text();

         var htmlcode = "";
            htmlcode += "<br><div style='width: 100%; height: 30px'><div style='width: 30%; float: left; padding-left: 30px'>병원 이름</div><div style='width: 60%; float: right'><input type='text' readonly='readonly' id='calendar_title' value='"+hos_name+"' style='width: 135px;'></div></div>";
            htmlcode += "<div style='width: 100%; height: 30px'><div style='width: 30%; float: left; padding-left: 30px'>예약 시간</div><div style='width: 60%; float: right'><input type='date' id='r_date'><input type='hidden' id='content' value='"+hos_desc+"'></div></div>";
            htmlcode += "<div style='width: 100%; height: 30px'><div style='width: 30%; float: left; padding-left: 30px'>예약 시간</div><div style='width: 60%; float: right'><input type='time' id='r_time' min='09:00' max='17:30' step='1800' value='09:00'></div></div>";
            htmlcode += "<div style='width: 100%; text-align:center; hright:30px; margin-bottom:15px; margin-top:10px'><button onclick=\"javascript:saveSchedule();\">예약하기</button></div>";
            openPopup("예약하기", htmlcode, 400);
      }
   }
   function openPopup(subject,contents,widths) {
         $("#alert_subject").html(subject);
         $("#alert_contents").html(contents);
         openMessage('winAlert',widths)
      }
   function openMessage(IDS,widths) {
         $('#'+IDS).css("width",widths+"px");
         $('#'+IDS).bPopup();
      }
   function saveSchedule() {
         var calendar_title = $("#calendar_title").val();
         var r_date = $("#r_date").val();
         var r_time = "T"+$("#r_time").val();
         var content = $("#content").val();
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
            data:{calendar_title: calendar_title, r_date: r_date, r_time: r_time, content: content},
            success:function(msg){
               alert(msg);
               console.log(msg);
               closeMessage('winAlert');
            },
            error:function(){
               alert("ajax 통신 실패");
            }
         });   
      }
   function closeMessage(IDS) {
         $('#'+IDS).bPopup().close();
      }
   
</script>
<style type="text/css">
   <%@include file="../css/default.css"%>
   <%@include file="map.css"%>
</style>
</head>
<body>
   <jsp:include page="../nav/navlogin.jsp"></jsp:include>
   <script type="text/javascript" src="map/jquery.bpopup.min.js"></script>
   <section>
      <div id="map"></div>
      <div id="hospital_info">
         <div id="hospital_desc"></div>
         <input type="button" id="hospital_btn" value= "예약하기" onclick="reservation();">
      </div>
   </section>
   
   <script type="text/javascript">
      <%@include file="map.js"%>
   </script>
   
   <div id="fixed">
      <form action="../mapcontroller.do" method="post" name="hos">
         <table>
            <c:choose>
               <c:when test="${empty list }">
                  <tr>
                     <td>----제공하는 리스트가 없습니다----</td>
                  </tr>
               </c:when>
               <c:otherwise>
                  <c:forEach items="${list }" var = "list">
                     <tr>
                        <th>${list.hospital_Name }</th>
                        <td>
                        <input type="hidden" name="hos_name" value="${list.hospital_Name}">
                        <input type="hidden" name="hos_addr" value="${list.hospital_Addr}">
                        <input type="hidden" name="hos_tel" value="${list.hospital_Tel}">
                        <input type="hidden" name="x" value="${list.latitude }">
                        <input type="hidden" name="y" value="${list.longitude }">
                        </td>
                     </tr>
                  </c:forEach>
               </c:otherwise>
            </c:choose>
         </table>
      </form>
    </div>
    <div class="box box-success" style="width:500px; display:none; background-color:white" id="winAlert">      
         <div class="box-header with-border" style="background-color:white; padding-left:15px">
            <h3 class="box-title" id="alert_subject" style="background-color:white"></h3>
         </div>
         <div class="box-body" id="alert_contents" style="font-size:15px; background-color:white"></div>
      </div>
</body>
</html>