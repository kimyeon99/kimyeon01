package scpark3;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

import jdk.internal.org.jline.utils.InputStreamReader;

public class HttpURLTest {

	public static void main(String[] args) {
		
		//get ���(�� �ּ� ���� ������ �߰��ϴ� ���)
		String site = "https://www.naver.com";
		URL url = new URL(site);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		
	}

}
