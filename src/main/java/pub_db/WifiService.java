package pub_db;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.*;

public class WifiService {
	
	public String KEY = "797576557367757335356579784945";
	// 와이파이 정보 전체 개수 구하기
	public int getTotal() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
		urlBuilder.append("/" +  URLEncoder.encode(KEY,"UTF-8") ); 
		urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); 
		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); 
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-type", "application/xml");
		
		BufferedReader br;

		if(con.getResponseCode() >= 200 && con.getResponseCode() <= 300) { // 서비스코드가 정상이면 200~300사이의 숫자 나옴. 
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
				sb.append(line);
		}
		br.close();
		con.disconnect();
		String total = sb.substring(40, 45);
		return Integer.parseInt(total);
	}
	
	// 와이파이 정보 DB에 저장하기 
	public void savePubWifi() {
		try {
			int totalCnt = getTotal();
			BufferedReader br;
			StringBuilder sb = new StringBuilder();
			String line;
			
			int idx = 1; // 1000개씩 끊는 위치 받기 위한 인덱스 
			for (int i = 0; i < totalCnt/1000 + 1; i++) { // 한 번에 데이터 1000 개씩 받을 수 있음. 
				StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
				urlBuilder.append("/" +  URLEncoder.encode(KEY,"UTF-8") ); 
				urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); 
				urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); 
				urlBuilder.append("/" + URLEncoder.encode(String.valueOf(idx),"UTF-8")); 
				urlBuilder.append("/" + URLEncoder.encode(String.valueOf(idx+999),"UTF-8")); // 데이터 1000개씩 받기
				
				URL url = new URL(urlBuilder.toString());
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-type", "application/xml");

				if(con.getResponseCode() >= 200 && con.getResponseCode() <= 300) { 
						br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {
						br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				while((line = br.readLine()) != null) {
						sb.append(line);
				}
				JSONObject result = (JSONObject) new JSONParser().parse(sb.toString()); // 전체 결과 받아오고
				JSONObject totalData = (JSONObject) result.get("TbPublicWifiInfo"); // 결과 안에 공공와이파이 관련 데이터 받고
				JSONArray array = (JSONArray) totalData.get("row"); // 위에서 설정한 것처럼 1000개씩 와이파이 정보들 받기 
				JSONObject infor; // 와이파이 정보 하나씩 담을 변수 
				
				for(int j = 0; j < array.size(); j++) { // 1000번 반복
					PubWifi pubWifi = new PubWifi();
					infor = (JSONObject) array.get(j);
					pubWifi.setMgrNo(String.valueOf(infor.get("X_SWIFI_MGR_NO")));
					pubWifi.setWrdOfC(String.valueOf(infor.get("X_SWIFI_WRDOFC")));
					pubWifi.setMainNm(String.valueOf(infor.get("X_SWIFI_MAIN_NM")));
					pubWifi.setAdres1(String.valueOf(infor.get("X_SWIFI_ADRES1")));
					pubWifi.setAdres2(String.valueOf(infor.get("X_SWIFI_ADRES2")));
					pubWifi.setInstlFloor(String.valueOf(infor.get("X_SWIFI_INSTL_FLOOR")));
					pubWifi.setInstlTy(String.valueOf(infor.get("X_SWIFI_INSTL_TY")));
					pubWifi.setInstlMby(String.valueOf(infor.get("X_SWIFI_INSTL_MBY")));
					pubWifi.setSvcSe(String.valueOf(infor.get("X_SWIFI_SVC_SE")));
					pubWifi.setCmcwr(String.valueOf(infor.get("X_SWIFI_CMCWR")));
					pubWifi.setCnstcYear(String.valueOf(infor.get("X_SWIFI_CNSTC_YEAR")));
					pubWifi.setInoutDoor(String.valueOf(infor.get("X_SWIFI_INOUT_DOOR")));
					pubWifi.setRemars3(String.valueOf(infor.get("X_SWIFI_REMARS3")));
					pubWifi.setLat(String.valueOf(infor.get("LAT")));
					pubWifi.setLnt(String.valueOf(infor.get("LNT")));
					pubWifi.setWorkDttm(String.valueOf(infor.get("WORK_DTTM")));
					
					DBuse dbUse = new DBuse();
					dbUse.insert(pubWifi);
				}
				idx += 1000;
				br.close();
				con.disconnect();
			}
		} catch (Exception e) {
					System.out.println(e.getClass());
					System.out.println(e.getMessage());
				}
			}

	
	// 와이파이 정보 20개 목록 불러오기 + 위치 정보 저장 + 거리정보 저장 
	public List<PubWifi> getList(String myLnt, String myLat) {
		DBuse dbUse = new DBuse();
		dbUse.insertMyPos(myLnt, myLat);
		dbUse.deleteList();
		dbUse.insert20(myLnt, myLat);
		return dbUse.select20();
	}
	
	
	
	
	public void resetList() {
		DBuse dbUse = new DBuse();
		dbUse.deleteList();
	}
	
	//  OpenAPI 와이파이 정보 누르면 리셋시키고 다시 불러오기
	public int resetDb() throws IOException {
		DBuse dbUse = new DBuse();
		dbUse.deleteAll();
		WifiService wifiService = new WifiService();
		wifiService.savePubWifi();
		return getTotal();
	}
	
	// 상세 정보 보기 
	public PubWifi getDetail(String mgrNo) {
		DBuse dbUse = new DBuse();
		return dbUse.detail(mgrNo);
	}
	
	//  북마크 그룹 리스트 가져오기
	public List<BMgr> getBMgr(String bmName, String bmOrder) {
		DBuse dbUse = new DBuse();
		dbUse.insertBmGr(bmName, bmOrder);
		return dbUse.showBmGr();
	}
	
	// 상세보기에서 북마크 그룹 리스트 가져오기
	public List<BMgr> getBMgr() {
		DBuse dbUse = new DBuse();
		return dbUse.showBmGr();
	}
	
}
