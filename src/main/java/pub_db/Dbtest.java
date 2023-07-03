package pub_db;

import java.io.IOException;

public class Dbtest {

	public static void main(String args[]) throws IOException {
		WifiService wifiService = new WifiService();
		//int total = wifiService.resetDb();
		//System.out.println(total);
		//wifiService.getList("37.50328806", "127.09764647");
		wifiService.getDetail("-WF121070");

	
	}
}
