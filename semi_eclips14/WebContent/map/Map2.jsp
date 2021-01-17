<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>지도 api</title>

<script type="text/javascript">
   <%@include file="jquery-3.5.1.min.js"%>
</script>
<script type="text/javascript">
	<%@include file="hospital_list.js"%>
</script>
<style type="text/css">
	<%@include file="../css/default.css"%>
</style>

</head>
<body>
	<form action="../mapcontroller.do" method="post" name="hos">
		<input type="hidden" name="command" value="list">
	</form>
</body>
</html>