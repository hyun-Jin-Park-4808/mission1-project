<%@page import="pub_db.PubWifi"%>
<%@page import="pub_db.DBuse"%>
<%@page import="pub_db.WifiService"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="pub_db.*"%>

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
	DBuse db = new DBuse();
	List<HistPos> posList = db.showHistPos();
	%>
	<h1> 위치 히스토리 목록 </h1>
		<ul> <!--  ul 태그, 순서가 없는 목차 정의 -->
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
		<div style="line-height: 230%;"> <br> </div>
		<div style="line-height: 100%;"> <br> </div>
		<form action="http://localhost:8080/history-delete.jsp" accept-charset="UTF-8" name="bm_gr_info" method = "get" onsubmit="return confirm('삭제하였습니다.')">
		<table> 
			<thead>
				<tr height = "40">
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
				</tr>
			</thead>
			
			<tbody>
				<% 
					for(HistPos histPos : posList) {
				%>
				<tr>
					<td> <%=histPos.getHistNo()%> </td>
					<td> <%=histPos.getLat()%> </td>
					<td> <%=histPos.getLnt()%> </td>
					<td> <%=histPos.getSerDttm()%> </td>
					<td>	
					<input type = "hidden" name = "histNo" value ="<%=histPos.getHistNo()%>">
					<div align="center">
					<input type = "submit" value = "삭제">
					</div> 
					</td>
				</tr>
				<% 
				}
				%>
			</tbody>
		</table>
		</form> 

</body>
</html>