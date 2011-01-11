package ucsc.crop.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class HTTPHandler {

	public static String sendReqAndGetRes(StringBuffer url, HashMap<String, String> params){
		URL connectURL;
		
		if(params != null){
			url.append("?");
			for(String key : params.keySet()){
				url.append(key + "=" + params.get(key) + "&");
			}
		}
		
		try {
			connectURL = new URL(url.toString());
			
			HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
			return getResponse(conn);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static String getResponse(HttpURLConnection conn) {
		InputStream is = null;
		try {
			is = conn.getInputStream();
			// scoop up the reply from the server
			int ch;
			StringBuffer sb = new StringBuffer();
			while ((ch = is.read()) != -1) {
				sb.append((char) ch);
			}
			return sb.toString();
		} catch (Exception e) {

		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception e) {
			}
		}

		return "";
	}
}
