package pub_db;
import java.sql.*;
import java.util.*;

public class DBuse {
	// DB에 와이파이 정보 저장하기 메소드
	public void insert(PubWifi pubWifi) {
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "insert into pub_wifi(MGR_NO, WRDOFC, MAIN_NM, ADRES1, ADRES2, "
            			+ "INSTL_FLOOR, INSTL_TY, INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, " 
            			+ "INOUT_DOOR, REMARS3, LNT, LAT, WORK_DTTM) " +  // 테이블 각 칼럼에 values에 해당하는 값 넣을 것이다.
            			 "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"; 
            
            preStat = con.prepareStatement(sql);
         // 오픈 API에서 와이파이 정보 하나씩 pubWifi 객체에 담기고, 해당 객체에서 getter로 각 값 가져와서, 해당 순서대로 물음표에 값 넣음.
            preStat.setString(1, pubWifi.getMgrNo()); 
            preStat.setString(2, pubWifi.getWrdOfC());
            preStat.setString(3, pubWifi.getMainNm());
            preStat.setString(4, pubWifi.getAdres1());
            preStat.setString(5, pubWifi.getAdres2());
            preStat.setString(6, pubWifi.getInstlFloor());
            preStat.setString(7, pubWifi.getInstlTy());
            preStat.setString(8, pubWifi.getInstlMby());
            preStat.setString(9, pubWifi.getSvcSe());
            preStat.setString(10, pubWifi.getCmcwr());
            preStat.setString(11, pubWifi.getCnstcYear());
            preStat.setString(12, pubWifi.getInoutDoor());
            preStat.setString(13, pubWifi.getRemars3());
            preStat.setString(14, pubWifi.getLat());
            preStat.setString(15, pubWifi.getLnt());
            preStat.setString(16, pubWifi.getWorkDttm());
            
            int affected = preStat.executeUpdate();
            if(affected < 0) {
            	System.out.println("저장 실패");
            }
        } catch(SQLException e) {
        	e.printStackTrace();
        } finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	// 목록DB에서 20개 데이터 리스트 형태로 받기
	public List<PubWifi> select20() { 
		List<PubWifi> list = new ArrayList<>();
		
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성
        ResultSet rs = null;

        try { // 4. 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword);
            // 하버사인 공식 사용
            String sql = "select * "
            		+ "from pub_wifi_list "
            		+ "order by dist ";
            
            preStat = con.prepareStatement(sql);
            
            rs = preStat.executeQuery();

            while (rs.next()) { // 5. 결과 수행 => 쿼리 수행한 결과 각 변수에 넣기 
                String dist = rs.getString("dist");
                String mgrNo = rs.getString("mgr_no");
                String wrdOfC = rs.getString("wrdofc");
                String mainNm = rs.getString("main_nm");
                String adres1 = rs.getString("adres1");
                String adres2 = rs.getString("adres2");
                String instlFloor = rs.getString("instl_floor");
                String instlTy = rs.getString("instl_ty");
                String instlMby = rs.getString("instl_mby");
                String svcSe = rs.getString("svc_se");
                String cmcwr = rs.getString("cmcwr");
                String cnstcYear = rs.getString("cnstc_year");
                String inoutDoor = rs.getString("inout_door");
                String remars3 = rs.getString("remars3");
                String lnt = rs.getString("lnt");
                String lat = rs.getString("lat");
                String workDTTM = rs.getString("work_dttm");
                
                // PubWifi 객체 만들어서 위에서 테이블에서 받아서 변수에 넣은 값 객체에 넣기  
                PubWifi pubWifi = new PubWifi();
                pubWifi.setDist(dist); 
                pubWifi.setMgrNo(mgrNo);
                pubWifi.setWrdOfC(wrdOfC);
                pubWifi.setMainNm(mainNm);
                pubWifi.setAdres1(adres1);
                pubWifi.setAdres2(adres2);
                pubWifi.setInstlFloor(instlFloor);
                pubWifi.setInstlTy(instlTy);
                pubWifi.setInstlMby(instlMby);
                pubWifi.setSvcSe(svcSe);
                pubWifi.setCmcwr(cmcwr);
                pubWifi.setCnstcYear(cnstcYear);
                pubWifi.setInoutDoor(inoutDoor);
                pubWifi.setRemars3(remars3);
                pubWifi.setLnt(lnt);
                pubWifi.setLat(lat);
                pubWifi.setWorkDttm(workDTTM);
                
                list.add(pubWifi); // 멤버리스트에 각 와이파이 정보 더하기 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();;
            }
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return list;
	}

	// 목록 20개 저장
	public void insert20(String myLnt, String myLat) {
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성
 
        try { // 4. 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword);
            // 하버사인 공식 사용
            String sql = "insert into pub_wifi_list (mgr_no, wrdofc, main_nm, adres1, adres2, instl_floor, "
            		+ "instl_ty, instl_mby, svc_se, cmcwr, cnstc_year, inout_door, remars3, lnt, lat, work_dttm, dist) "
            		+ "select *, round(6371*acos(cos(radians(?))"
            		+ "*cos(radians(LAT))"
            		+ "*cos(radians(LNT)-radians(?))"
            		+ "+sin(radians(?))*sin(radians(LAT))), 4) as dist "
            		+ "from pub_wifi "
            		+ "order by dist "
            		+ "limit 20;";
            
            preStat = con.prepareStatement(sql);
            preStat.setString(1, String.valueOf(myLnt)); // 물음표 첫 번째에 들어가는 변수(함수 실행할 때 인자로 받음)
            preStat.setString(2, String.valueOf(myLat));
            preStat.setString(3, String.valueOf(myLnt));
            
            int affected = preStat.executeUpdate();
            if(affected < 0) {
            	System.out.println("저장 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}
	
	// 목록 초기화
	public void deleteList() {
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement prestat = null; // 스테이트먼트 객체 생성

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "delete "
            		+ "from pub_wifi_list;"; 
            
            prestat = con.prepareStatement(sql);
            
            int affected = prestat.executeUpdate();
            if(affected < 0) {
            	System.out.println("삭제 실패");
            }
        } catch(SQLException e) {
        	e.printStackTrace();
        } finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (prestat != null && !prestat.isClosed()) {
                    prestat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
    	
	// DB 데이터 전체 초기화
	public void deleteAll() {
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement prestat = null; // 스테이트먼트 객체 생성

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "delete "
            		+ "from pub_wifi;"; 
            
            prestat = con.prepareStatement(sql);
            
            int affected = prestat.executeUpdate();
            if(affected < 0) {
            	System.out.println("삭제 실패");
            }
        } catch(SQLException e) {
        	e.printStackTrace();
        } finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (prestat != null && !prestat.isClosed()) {
                    prestat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
    	
	// 내 위치 저장
	public void insertMyPos(String myLnt, String myLat) {
			String url = "jdbc:mariadb://localhost/pubWifidb";
	        String dbUserId = "wifiuser";
	        String dbPassword = "zerobase";
	        try { // 드라이버 로드 
	            Class.forName("org.mariadb.jdbc.Driver");
	        } catch(ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        Connection con = null; // 커넥션 객체 생성
	        PreparedStatement preStat = null; // 스테이트먼트 객체 생성

	        try { // 쿼리 실행
	            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
	           
	            String sql = "insert into lo_history(lnt, lat, ser_dttm) "
	            		+ "values (?, ?, now());";
	            
	            preStat = con.prepareStatement(sql);
	            preStat.setString(1, String.valueOf(myLnt)); 
	            preStat.setString(2, String.valueOf(myLat));   
	            
	            int affected = preStat.executeUpdate();
	            if(affected < 0) {
	            	System.out.println("저장 실패");
	            }
	        } catch(SQLException e) {
	        	e.printStackTrace();
	        } finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
	            try {
	                if (preStat != null && !preStat.isClosed()) {
	                    preStat.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            try {
	                if (con != null && !con.isClosed()) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		}	
	
	// 내 위치 저장 목록 보기
	public List<HistPos> showHistPos() { 
		
		List<HistPos> list = new ArrayList<>();
		
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성
        ResultSet rs = null;

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "select * "
            		+ "from lo_history;";
            
            preStat = con.prepareStatement(sql);
            rs = preStat.executeQuery();
       
            while (rs.next()) { // 5. 결과 수행 => 쿼리 수행한 결과 각 변수에 넣기 
                int histNo = rs.getInt("hist_no");
                String lnt = rs.getString("lnt");
                String lat = rs.getString("lat");
                String serDttm = rs.getString("ser_dttm");
                
                HistPos histPos = new HistPos();
                histPos.setHistNo(histNo);
                histPos.setLnt(lnt);
                histPos.setLat(lat);
                histPos.setSerDttm(serDttm);
                
                list.add(histPos); // 멤버리스트에 각 와이파이 정보 더하기 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();;
            }
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return list;
	}
	
	// 내 위치 불러오기
	public String[] selectMyPos() {
		
		String[] myPos = new String[2];
		
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성
        ResultSet rs = null;

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "select LNT, LAT "
            		+ "from lo_history "
            		+ "order by HIST_NO desc "
            		+ "limit 1;";
            
            preStat = con.prepareStatement(sql);
            rs = preStat.executeQuery();
       
            while (rs.next()) { // 5. 결과 수행 => 쿼리 수행한 결과 각 변수에 넣기 
                String lnt = rs.getString("lnt");
                String lat = rs.getString("lat");
               
                myPos[0] = lnt; // 멤버리스트에 각 와이파이 정보 더하기
                myPos[1] = lat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();;
            }
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		return myPos;
	}
	
	// 내 위치 삭제
	public void deleteHistPos(int histNo) {
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "delete "
            		+ "from lo_history "
            		+ "where hist_no = ?;";
            
            preStat = con.prepareStatement(sql);
            preStat.setInt(1, Integer.valueOf(histNo));
        
            int affected = preStat.executeUpdate();
            if(affected < 0) {
            	System.out.println("삭제 실패");
            }
        } catch(SQLException e) {
        	e.printStackTrace();
        } finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	// 와이파이 상세정보 보기
	public PubWifi detail(String mgrNo) {
		
		PubWifi pubWifi = null;
		
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성
        ResultSet rs = null;

        try { // 4. 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword);
            
            String sql = "select * "
            		+ "from pub_wifi_list "
            		+ "where mgr_no = ?;";
            
            preStat = con.prepareStatement(sql);
            preStat.setString(1, String.valueOf(mgrNo));
            
            rs = preStat.executeQuery();

            if (rs.next()) { // 5. 결과 수행 => 쿼리 수행한 결과 각 변수에 넣기 
            	pubWifi = new PubWifi();

                pubWifi.setDist(rs.getString("dist")); 
                pubWifi.setMgrNo(rs.getString("mgr_no"));
                pubWifi.setWrdOfC(rs.getString("wrdofc"));
                pubWifi.setMainNm(rs.getString("main_nm"));
                pubWifi.setAdres1(rs.getString("adres1"));
                pubWifi.setAdres2(rs.getString("adres1"));
                pubWifi.setInstlFloor(rs.getString("instl_floor"));
                pubWifi.setInstlTy(rs.getString("instl_ty"));
                pubWifi.setInstlMby(rs.getString("instl_mby"));
                pubWifi.setSvcSe(rs.getString("svc_se"));
                pubWifi.setCmcwr(rs.getString("cmcwr"));
                pubWifi.setCnstcYear(rs.getString("cnstc_year"));
                pubWifi.setInoutDoor(rs.getString("inout_door"));
                pubWifi.setRemars3(rs.getString("remars3"));
                pubWifi.setLnt(rs.getString("lnt"));
                pubWifi.setLat(rs.getString("lat"));
                pubWifi.setWorkDttm(rs.getString("work_dttm"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();;
            }
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return pubWifi;
	}
	
	// 즐겨찾기 그룹 관리 -> 북마크 그룹 추가
	public void insertBmGr(String bmName, String order) {
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "insert into bm_gr(BM_NM, BM_ORDER, ADD_DTTM) "
            		+ "values (?, ?, now());";
            
            preStat = con.prepareStatement(sql);
            preStat.setString(1, String.valueOf(bmName)); 
            preStat.setString(2, String.valueOf(order));
        
            int affected = preStat.executeUpdate();
            if(affected < 0) {
            	System.out.println("저장 실패");
            }
        } catch(SQLException e) {
        	e.printStackTrace();
        } finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}	
		
	// 즐겨찾기 그룹 관리 -> 북마크 그룹 수정 
	public void updateBmGr(String preBmName, String newBmName, String order) {
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "update bm_gr "
            		+ "set "
            		+ "BM_NM = ? "
            		+ ", BM_ORDER = ? "
            		+ ", EDIT_DTTM = now() "
            		+ "where BM_NM = ?";
            
            preStat = con.prepareStatement(sql);
            preStat.setString(1, String.valueOf(newBmName)); 
            preStat.setString(2, String.valueOf(order));
            preStat.setString(3, String.valueOf(preBmName));
            
        
            int affected = preStat.executeUpdate();
            if(affected < 0) {
            	System.out.println("수정 실패");
            }
        } catch(SQLException e) {
        	e.printStackTrace();
        } finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}
	
	// 즐겨찾기 그룹 관리 -> 북마크 그룹 삭제
	public void deleteBmGr(String bmName, String order) {
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "delete "
            		+ "from bm_gr "
            		+ "where BM_NM = ? and BM_ORDER = ?;";
            
            preStat = con.prepareStatement(sql);
            preStat.setString(1, String.valueOf(bmName));
            preStat.setString(2, String.valueOf(order));
        
            int affected = preStat.executeUpdate();
            if(affected < 0) {
            	System.out.println("삭제 실패");
            }
        } catch(SQLException e) {
        	e.printStackTrace();
        } finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	// 즐겨찾기 그룹 관리 -> 북마크 그룹 보기
	public List<BMgr> showBmGr() {
		
		List<BMgr> list = new ArrayList<>();
		
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성
        ResultSet rs = null;

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "select * "
            		+ "from bm_gr;";
            
            preStat = con.prepareStatement(sql);
            rs = preStat.executeQuery();
       
            while (rs.next()) { // 5. 결과 수행 => 쿼리 수행한 결과 각 변수에 넣기 
                int addNo = rs.getInt("add_no");
                String bmNm = rs.getString("bm_nm");
                String bmOrder = rs.getString("bm_order");
                String addDttm = rs.getString("add_dttm");
                String editDttm = rs.getString("edit_dttm");
                
                BMgr bmGr = new BMgr();
                bmGr.setAddNo(addNo);
                bmGr.setBmNm(bmNm);
                bmGr.setBmOrder(bmOrder);
                bmGr.setAddDttm(addDttm);
                bmGr.setEditDttm(editDttm);
                
                list.add(bmGr); // 멤버리스트에 각 와이파이 정보 더하기 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();;
            }
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return list;
	}
	
	// 북마크 그룹에서 BM-NM 받아서 ADD_NO 받아오기
	public int getAddNo(String bmNm) {
		
		int addNo = 0;
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성
        ResultSet rs = null;

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "select add_no "
            		+ "from bm_gr "
            		+ "where bm_nm = ?;";
            
            preStat = con.prepareStatement(sql);
            preStat.setString(1, String.valueOf(bmNm)); 
            rs = preStat.executeQuery();
       
            while (rs.next()) { // 5. 결과 수행 => 쿼리 수행한 결과 각 변수에 넣기 
                addNo = rs.getInt("add_no");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();;
            }
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return addNo;
	}
	
	// 북마크 목록 추가하기 -> 와이파이 정보를 추가 (상세정보 보기에서 인자 넘겨받기)
	public void insertBmList(String wifiNm, String bmName, int id) {
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "insert into bm_list(BM_NM, MAIN_NM, ADD_NO, ADD_DTTM) "
            		+ "values (?, ?, ?, now());";
            
            preStat = con.prepareStatement(sql);
            preStat.setString(1, String.valueOf(bmName)); 
            preStat.setString(2, String.valueOf(wifiNm));
            preStat.setInt(3, id);
        
            int affected = preStat.executeUpdate();
            if(affected < 0) {
            	System.out.println("저장 실패");
            }
        } catch(SQLException e) {
        	e.printStackTrace();
        } finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	// 북마크 목록 삭제하기 -> 와이파이 정보 삭제 
	public void deleteBmList(String wifiNm, String bmName, int id) {
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "delete "
            		+ "from bm_list "
            		+ "where BM_NM = ? and MAIN_NM = ? and ADD_NO = ?;";
            
            preStat = con.prepareStatement(sql);
            preStat.setString(1, String.valueOf(bmName));
            preStat.setString(2, String.valueOf(wifiNm));
            preStat.setInt(3, id);
        
            int affected = preStat.executeUpdate();
            if(affected < 0) {
            	System.out.println("삭제 실패");
            }
        } catch(SQLException e) {
        	e.printStackTrace();
        } finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	//  북마크 목록 보기
	public List<BMList> showBmList() {
		
		List<BMList> list = new ArrayList<>();
		
		String url = "jdbc:mariadb://localhost/pubWifidb";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";
        
        try { // 드라이버 로드 
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null; // 커넥션 객체 생성
        PreparedStatement preStat = null; // 스테이트먼트 객체 생성
        ResultSet rs = null;

        try { // 쿼리 실행
            con = DriverManager.getConnection(url, dbUserId, dbPassword); // DB 접속
           
            String sql = "select * "
            		+ "from bm_list;";
            
            preStat = con.prepareStatement(sql);
            rs = preStat.executeQuery();
       
            while (rs.next()) { // 5. 결과 수행 => 쿼리 수행한 결과 각 변수에 넣기 
                int addNo = rs.getInt("add_no_list");
                String bmNm = rs.getString("bm_nm");
                String mainNm = rs.getString("main_nm");
                String addDttm = rs.getString("add_dttm");
                
                BMList bmList = new BMList();
                bmList.setAddNo(addNo);
                bmList.setBmNm(bmNm);
                bmList.setMainNm(mainNm);
                bmList.setAddDttm(addDttm);
                
                list.add(bmList); // 멤버리스트에 각 와이파이 정보 더하기 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally { // 6. 객체 연결 해제 -> 사용한 객체 닫아줘야 함.
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();;
            }
            try {
                if (preStat != null && !preStat.isClosed()) {
                    preStat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return list;
	}

	}

