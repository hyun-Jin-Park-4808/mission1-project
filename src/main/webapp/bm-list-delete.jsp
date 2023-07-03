<%@page import="pub_db.PubWifi"%>
<%@page import="pub_db.DBuse"%>
<%@page import="pub_db.*"%>
<%@page import="pub_db.WifiService"%>
<%@page import="java.util.*"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
		<style>
			 li {
			 display:block;
			 float:left;
			 list-style:none;
			 
			 }
			 ul {
			 padding-left: 0;
			 font-size:12px;
			 }
			 form {
			 font-size:13px;
			 }
			 th {
			 border-collapse;
			 text-align:center;
			 font-size:13px;
			 color:white;
			 background-color:#3CB371;
			 height: 30px;
			 }
			 td {
			 font-weight: bold;
			 border:solid 1px #D3D3D3;
			 text-align:left;
			 font-size:12px;
			 color:black;
			 height: 30px;
			 }
		</style>
</head>
<body>
	<%
	String bmNm = request.getParameter("bmNm");
	String wifiNm = request.getParameter("wifiNm");
	String dttm = request.getParameter("dttm");
	DBuse db = new DBuse();
	%>
	<h1> 북마크 그룹 수정 </h1>
		<ul> 
			<li> <a href="index.jsp">홈 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="history.jsp"> 위치 히스토리 목록 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="wifi_number.jsp"> Open API 와이파이 정보 가져오기 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="bm-list.jsp"> 북마크 보기 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="bm-gr.jsp"> 북마크 그룹 관리 </a> </li>
		</ul>
		<div style="line-height: 200%;"> <br> </div>
		
		
		<div style="line-height: 100%;"> <br> </div>
		<form action="http://localhost:8080/bm-list-delete-home.jsp" accept-charset="UTF-8" name="bm_gr_info" method = "get" onsubmit="return confirm('삭제하였습니다.')">
		<input type = "hidden" name = "bmNm" value ="<%=bmNm%>">
		<input type = "hidden" name = "wifiNm" value ="<%=wifiNm%>">
		<table style="width: 100%"> 
		<colgroup>
			<col style="width: 20%;"/>
			<col style="width: 80%;"/>
		</colgroup>
		
			<tbody>
				<tr>
				<th>북마크 이름</th>
				<td style="background-color: white;">
				<%=bmNm%>
				</td>
				</tr>
				
				<tr>
				<th>와이파이명</th>
				<td style="background-color: #F5F5F5;">
				<%=wifiNm%>
				</td>
				</tr>
				
				<tr>
				<th>등록일자</th>
				<td style="background-color: #F5F5F5;">
				<%=dttm%>
				</td>
				</tr>
			<tbody>
		</table>	
		<div align="center">
		<a href="bm-list.jsp"> 돌아가기 </a>
		&nbsp;|&nbsp;
		<input type = "submit" value = "삭제">
		</div>
		</form> 
	
</body>
</html>