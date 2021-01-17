<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel='stylesheet' type='text/css'href='css/timepicki.css'/>
    <style type="text/css">
        p{
            text-align: left;
        }
        .btn{
            width: 148px;
            height: 48px;
            font-size: 16px;
            color: #fff;
            background-color: #2C383B;
            border: none;
            text-align: center;
        }
        #btn_cancel{
            margin-left: 30px;
        }
        .btn_div{
            text-align: center;
            margin-top: 25px;
            margin-bottom: 20px;
        }
        .inp_text{
            margin-top: 15px;
            margin-left: 25px;
            margin-right: 15px;
        }
        .inp_text>input{
            width: 150px;
            height: 45px;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            font-size: 15px;
            margin-left: 10px;
        }
        form{
            font-weight: 600;
            font-size: 15px;
            background-color: #fff;
        }
        body{
            background-color: #2C383B;
            margin: 30px;
        }
        .title{
            text-align: center;
            font-size: 20px;
            margin: 20px;
        }
        .map{
            width: 60px;
            height: 48px;
            font-size: 16px;
            color: #fff;
            background-color: #2C383B;
            border: none;
            text-align: center;
            margin-left: 23px;
        }
        fieldset{
            border: none;
        }
    </style>
</head>
<body>
    <form action="calendarcontroller.do"  method="get">
    	<input type="hidden" name="command" value="reservation">
    	<input type="hidden" name="content" value="">
        <fieldset>
            <div class="title">���� ����</div>
            <div class="inp_text">���� �̸�: <input type="text" name="title"></div>
            <div class="inp_text">��¥ ����: <input type="date" name="r_date"></div>
            <div class="inp_text">�ð� ����: <input type="time" name="r_time" min="09:00" max="18:00" step="1800" value="09:00"></div>
            <div class="btn_div"><input type="submit" class="btn" value="����">
            <input type="reset" class="btn" id="btn_cancel"value="���"></div>       
        </fieldset>
    </form>
</body>
</html>