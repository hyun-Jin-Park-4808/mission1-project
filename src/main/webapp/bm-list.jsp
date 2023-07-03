<%@page import="pub_db.PubWifi"%>
<%@page import="pub_db.DBuse"%>
<%@page import="pub_db.*"%>
<%@page import="pub_db.WifiService"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>

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
			 table{
			 width: 100%;
			 }
			 th {
			 border-collapse;
			 text-align:center;
			 font-size:13px;
			 color:white;
			 background-color:#3CB371;
			 }

			 td {
			 font-weight: bold;
			 border:solid 1px #D3D3D3;
			 text-align:left;
			 font-size:12px;
			 color:black;
			 }
			tbody tr:nth-child(2n) {
    		background-color: #F5F5F5;
    		height: 35px;
  			}
  			tbody tr:nth-child(2n+1) {
    		background-color: white;
    		height: 35px;
  			}
		</style>
</head>
<body>
	<%
	WifiService wifiService = new WifiService();
	DBuse db =  new DBuse();
	List<BMList> bmList =  db.showBmList();
	%>
	<h1> 북마크 보기 </h1>
		<ul>
			<li> <a href="index.jsp"> 홈 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="history.jsp"> 위치 히스토리 목록 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="load-wifi.jsp"> Open API 와이파이 정보 가져오기 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="bm-list.jsp"> 북마크 보기 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="bm-gr.jsp"> 북마크 그룹 관리 </a> </li>
		</ul>
		<br>
		<table> 
			<thead>
				<tr height = "40">
				<th>ID</th>
				<th>북마크 이름</th>
				<th>와이파이명</th>
				<th>등록일자</th>
				<th>비고</th>
				</tr>
			</thead>
			<tbody>
			<% if(bmList.isEmpty()) { %>
			<tr height = "50" style="border:1px #DCDCDC; font-weight: bold;">
			<td colspan='17' style="text-align:center;"> <font size="2">정보가 존재하지 않습니다.</font></td>
			</tr>
			<%} else {%>
			<% 
					for(BMList bm : bmList) {
				%>
				<tr>
					<td> <%=bm.getAddNo()%> </td>
					<td> <%=bm.getBmNm()%> </td>
					<td> <%=bm.getMainNm()%> </td>
					<td> <%=bm.getAddDttm()%> </td>
					<td>
					<div align="center">
					<a href="bm-list-delete.jsp?bmNm=<%=bm.getBmNm()%>&wifiNm=<%=bm.getMainNm()%>&dttm=<%=bm.getAddDttm()%>">삭제</a> 
					</div></td>
				</tr>
				<% } %>
			<% } %>
			</tbody>
		</table>
			
</body>
</html>