package ucsc.crop.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class JSONHandler {
	public static JSONArray loadFromJSON(String jsonString, String arrayName) {	
		JSONArray jArray = null;
		try {
			jArray = new JSONObject(jsonString).getJSONArray(arrayName);			
		} catch (JSONException e) {
			Log.v("Application Error", e.getMessage());
		}
		return jArray;
	}
}
