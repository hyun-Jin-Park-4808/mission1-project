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
	List<BMgr> bmGrList =  wifiService.getBMgr();
	%>
	<h1> 북마크 그룹 관리 </h1>
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
		<div style="line-height: 230%;"> <br> </div>
			<input type = "button" value = "북마크 그룹 이름 추가" onclick="location.href = 'bm-gr-add.jsp';"/>
		<div style="line-height: 100%;"> <br> </div>
		<table> 
			<thead>
				<tr height = "40">
				<th>ID</th>
				<th>북마크 이름</th>
				<th>순서</th>
				<th>등록일자</th>
				<th>수정일자</th>
				<th>비고</th>
				</tr>
			</thead>
			<tbody>
			<% if(bmGrList.isEmpty()) { %>
			<tr height = "100" style="border: 1px #D3D3D3; font-weight: bold;">
			<td colspan='6' style="text-align: center;"> <font size="2">정보가 존재하지 않습니다.</font></td>
			</tr>
			<%} else {%>
			<% 
					for(BMgr bmgr : bmGrList) {
				%>
				<tr>
					<td> <%=bmgr.getAddNo()%> </td>
					<td> <%=bmgr.getBmNm()%> </td>
					<td> <%=bmgr.getBmOrder()%> </td>
					<td> <%=bmgr.getAddDttm()%> </td>
					<td> <%=bmgr.getEditDttm()%> </td>
					<td>
					<div align="center">
					<a href="bm-gr-update.jsp?bmNm=<%=bmgr.getBmNm()%>&bmOrder=<%=bmgr.getBmOrder()%>">수정</a> 
					&nbsp; 
					<a href="bm-gr-delete.jsp?bmNm=<%=bmgr.getBmNm()%>&bmOrder=<%=bmgr.getBmOrder()%>">삭제</a> 
					</div></td>
				</tr>
				<% } %>
			<% } %>
			</tbody>
		</table>
			
</body>
</html>