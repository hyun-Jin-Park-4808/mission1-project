package pub_db;
import java.sql.*;

public class DBcon {
	 public static void main(String[] args) {
	        String url = "jdbc:mariadb://localhost/pubWifidb";
	        String dbUserId = "wifiuser";
	        String dbPassword = "zerobase";
	        try { // 드라이버 로드 
	            Class.forName("org.mariadb.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        Connection con = null; // 커넥션 객체 생성
	        PreparedStatement prestat = null; // 스테이트먼트 객체 생성
	        ResultSet rs = null;

	        try { // 쿼리 실행
	            con = DriverManager.getConnection(url, dbUserId, dbPassword);
	            if( con != null ) {
	                System.out.println("DB 접속 성공");
	            }
	        } catch (SQLException e) {
	            System.out.println("DB 접속 실패");
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
}
