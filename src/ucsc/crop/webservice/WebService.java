package ucsc.crop.webservice;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ucsc.crop.objects.Parameters;
import ucsc.crop.util.JSONHandler;

public class WebService {
	String code, name;
	//String[] words;
	Parameters para=new Parameters();
	
	public Parameters search(String namein, String codein){
		

				name=namein;
				code=codein;
				
			
		   
		      
				try{      
		        HashMap<String, String> params = new HashMap<String, String>();
		        params.put("cropCode", code);
		       
		        StringBuffer stringBuffer = new StringBuffer();
		        stringBuffer.append("http://mobile.icta.lk/services/cropservice/getCropData.php");
		        String test=ucsc.crop.util.HTTPHandler.sendReqAndGetRes(stringBuffer, params);
		        
		 
		        JSONArray cropJSON = JSONHandler.loadFromJSON(test, "crops"); //load the main object
		      
		  
		        for(int i=0; i< cropJSON.length() ;i++){ //loop through objects
		        	 JSONObject crops = cropJSON.getJSONObject(i);
		      
		        	 para.setName(crops.getString("name"));
		        	 para.setLocation(crops.getString("location"));
		        	if(i==0){
		        		para.setPrice1(crops.getString("price"));
		        	}
		        	if(i==1){
		        		para.setPrice2(crops.getString("price"));
		        	}
		        
		        }
		}
		catch (JSONException e){
		}
		return para;
	}
}
