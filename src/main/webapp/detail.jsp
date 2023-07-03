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
	String mgrNo = request.getParameter("mgrNo");
	String wifiNm = request.getParameter("wifiNm");
	WifiService wifiService = new WifiService();
	PubWifi pubWifi = wifiService.getDetail(mgrNo);
	List<BMgr> bmGrList = wifiService.getBMgr();
	%>
	
	<h1> 와이파이 정보 구하기 </h1>
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
		<div style="line-height: 200%;"> <br> </div>
		<form action="http://localhost:8080/bm-list-add.jsp" accept-charset="UTF-8" name="loc_info" method = "get" onsubmit="return confirm('추가하였습니다.')">
			<input type = "hidden" name = "wifiName" value ="<%=wifiNm%>">
			<select name = "bmName" size="1">
			<option value ="" selected>북마크 그룹 이름 선택</option>
			<% for(BMgr bmGr : bmGrList) { %>
			<option value ="<%=bmGr.getBmNm()%>"><%=bmGr.getBmNm()%></option>
			<%
			} 
			%>
			</select>
			<input type = "submit" value = "북마크 추가하기"/>
		</form> 
		
		<div style="line-height: 100%;"> <br> </div>
		<table style="width: 100%"> 
		<colgroup>
			<col style="width: 20%;"/>
			<col style="width: 80%;"/>
		</colgroup>
		
			<tbody>
				<tr>
				<th>거리(Km)</th>
				<td style="background-color: white;">
				<%=pubWifi.getDist() %>
				</td>
				</tr>
				
				<tr>
				<th>관리번호</th>
				<td style="background-color: #F5F5F5;">
				<%=pubWifi.getMgrNo() %>
				</td>
				</tr>
				
				<tr>
				<th>자치구</th>
				<td style="background-color: white;">
				<%=pubWifi.getWrdOfC() %>
				</td>
				</tr>
				
				<tr>
				<th>와이파이명</th>
				<td style="background-color: #F5F5F5;">
				<%=pubWifi.getMainNm() %>
				</td>
				</tr>
				
				<tr>
				<th>도로명주소</th>
				<td style="background-color: white;">
				<%=pubWifi.getAdres1() %>
				</td>
				</tr>
				
				<tr>
				<th>상세주소</th>
				<td style="background-color: #F5F5F5;">
				<%=pubWifi.getAdres2() %>
				</td>
				</tr>
				
				<tr>
				<th>설치위치(층)</th>
				<td style="background-color: white;">
				<%=pubWifi.getInstlFloor() %>
				</td>
				</tr>
				
				<tr>
				<th>설치유형</th>
				<td style="background-color: #F5F5F5;">
				<%=pubWifi.getInstlTy() %>
				</td>
				</tr>
				
				<tr>
				<th>설치기관</th>
				<td style="background-color: white;">
				<%=pubWifi.getInstlMby() %>
				</td>
				</tr>
				
				<tr>
				<th>서비스구분</th>
				<td style="background-color: #F5F5F5;">
				<%=pubWifi.getSvcSe() %>
				</td>
				</tr>
				
				<tr>
				<th>망종류</th>
				<td style="background-color: white;">
				<%=pubWifi.getCmcwr() %>
				</td>
				</tr>
				
				<tr>
				<th>설치년도</th>
				<td style="background-color: #F5F5F5;">
				<%=pubWifi.getCnstcYear() %>
				</td>
				</tr>
				
				<tr>
				<th>실내외 구분</th>
				<td style="background-color: white;">
				<%=pubWifi.getInoutDoor() %>
				</td>
				</tr>
				
				<tr>
				<th>WIFI 접속환경</th>
				<td style="background-color: #F5F5F5;">
				<%=pubWifi.getRemars3() %>
				</td>
				</tr>
				
				<tr>
				<th>X좌표</th>
				<td style="background-color: white;">
				<%=pubWifi.getLat() %>
				</td>
				</tr>
				
				<tr>
				<th>Y좌표</th>
				<td style="background-color: #F5F5F5;">
				<%=pubWifi.getLnt() %>
				</td>
				</tr>
				
				<tr>
				<th>작업일자</th>
				<td style="background-color: white;">
				<%=pubWifi.getWorkDttm() %>
				</td>
				</tr>
			<tbody>
		</table>	
	
</body>
</html>