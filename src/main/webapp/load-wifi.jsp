<%@page import="pub_db.PubWifi"%>
<%@page import="pub_db.DBuse"%>
<%@page import="pub_db.WifiService"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2 style="text-align: center;">
<%
WifiService wifiService = new WifiService();
int total = wifiService.resetDb();
out.write(total+"개의 WIFI 정보를 정상적으로 저장하였습니다.");
%>
</h2>
 <!-- <div style="line-height: 50%;"> <br> </div> -->
<div style="text-align: center;">
<a href="home.jsp"> 홈으로 가기 </a>
</div>
</body>
</html>