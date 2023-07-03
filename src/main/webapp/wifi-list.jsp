<%@page import="pub_db.PubWifi"%>
<%@page import="pub_db.DBuse"%>
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
	String myLat = request.getParameter("mylat");
	String myLnt = request.getParameter("mylnt");
	WifiService wifiService = new WifiService();
	List<PubWifi> wifiList = wifiService.getList(myLnt, myLat);
	%>
	<h1> 와이파이 정보 구하기 </h1>
		<ul> <!--  ul 태그, 순서가 없는 목차 정의 -->
			<li> <a href="index.jsp">홈 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="history.jsp"> 위치 히스토리 목록 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="wifi_number.jsp"> Open API 와이파이 정보 가져오기 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="bmlist.jsp"> 북마크 보기 </a> </li>
			<li> &nbsp;|&nbsp; </li>
			<li> <a href="bmgroup.jsp"> 북마크 그룹 관리 </a> </li>
		</ul>
		<div style="line-height: 230%;"> <br> </div>
		<form autocomplete = "off" action="http://localhost:8080/wifi-list.jsp" accept-charset="UTF-8" name="loc_info" method = "get">
			LAT: <input type = "text" name = "mylat" value =<%=myLat%>>,
			LNT: <input type = "text" name = "mylnt" value =<%=myLnt%>>
			<input type = "button" value = "내 위치 가져오기" onclick="location.href = 'home-position.jsp';"/>
			<input type = "submit" value = "근처 WIFI 정보 보기">
		</form> 
		<div style="line-height: 100%;"> <br> </div>
		<table> 
			<thead>
				<tr height = "40">
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외 구분</th>
				<th>WIFI 접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
				</tr>
			</thead>
			
			<tbody>
				<% 
					for(PubWifi pubWifi : wifiList) {
				%>
				<tr>
					<td> <%=pubWifi.getDist()%> </td>
					<td> <%=pubWifi.getMgrNo()%> </td>
					<td> <%=pubWifi.getWrdOfC()%> </td>
					<td>
					 <a href="detail.jsp?mgrNo=<%=pubWifi.getMgrNo()%>&wifiNm=<%=pubWifi.getMainNm()%>"> 
					 <%=pubWifi.getMainNm()%>
					</a>
					</td>
					<td> <%=pubWifi.getAdres1()%> </td>
					<td> <%=pubWifi.getAdres2()%> </td>
					<td> <%=pubWifi.getInstlFloor()%> </td>
					<td> <%=pubWifi.getInstlTy()%> </td>
					<td> <%=pubWifi.getInstlMby()%> </td>
					<td> <%=pubWifi.getSvcSe()%> </td>
					<td> <%=pubWifi.getCmcwr()%> </td>
					<td> <%=pubWifi.getCnstcYear()%> </td>
					<td> <%=pubWifi.getInoutDoor()%> </td>
					<td> <%=pubWifi.getRemars3()%> </td>
					<td> <%=pubWifi.getLat()%> </td>
					<td> <%=pubWifi.getLnt()%> </td>
					<td> <%=pubWifi.getWorkDttm()%> </td>
				</tr>
				<% 
				}
				%>
			</tbody>
		</table>
			

</body>
</html>