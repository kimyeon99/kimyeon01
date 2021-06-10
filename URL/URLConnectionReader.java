package scpark3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReader {

	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			URL site = new URL("https://www.naver.com");
			URLConnection url = site.openConnection();
			
			InputStreamReader stream = new InputStreamReader(url.getInputStream());
			// reader = new BufferedReader(new InputStreamReader(url.getInputStream()));
			reader = new BufferedReader(stream);
			
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			System.out.println();
			reader.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
				try { reader.close(); } catch(Exception ignore) { }
				
		}
	}
}
