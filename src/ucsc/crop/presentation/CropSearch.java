package ucsc.crop.presentation;



import ucsc.crop.objects.Parameters;
import ucsc.crop.webservice.WebService;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;








public class CropSearch extends Activity {
	ProgressDialog progressDialog;
    /** Called when the activity is first created. */
int indexSelectedCrop;
	TextView tv;
	EditText   text1;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.crop_autosearch);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);

       

       // prepare the alert box
      final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

       // set the message to display
       alertbox.setMessage("Please Enter A Crop First");

       // add a neutral button to the alert box and assign a click listener
       alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

           // click listener on the alert box
           public void onClick(DialogInterface arg0, int arg1) {
               // the button was clicked
               Toast.makeText(getApplicationContext(), "Enter The Crop ID", Toast.LENGTH_LONG).show();
           }
       });

       
       
       final AlertDialog.Builder alertbox2 = new AlertDialog.Builder(this);

       // set the message to display
       alertbox2.setMessage("Please Enter Valid Crop");

       // add a neutral button to the alert box and assign a click listener
       alertbox2.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

           // click listener on the alert box
           public void onClick(DialogInterface arg0, int arg1) {
               // the button was clicked
               Toast.makeText(getApplicationContext(), "Enter The Crop ID", Toast.LENGTH_LONG).show();
           }
       });
       
       
       // show it
      
        
        
        //Auto complete String array is in d Strings.xml
        
        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_crop);
        String[] cropsArray = getResources().getStringArray(R.array.crops_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, cropsArray);
        textView.setAdapter(adapter);
       
        final Button button = (Button) findViewById(R.id.search_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click Search 
            	// add the "autocomplete_crop" srting here n search
         String s= textView.getText().toString(); 	
         if (s.length()==0){
        	 alertbox.show();
         }
         else {
        	 Resources res = getResources();
        	 String[] cropss = res.getStringArray(R.array.crops_array);
        	 
     		 indexSelectedCrop=getIndex(cropss,s);
     		 if(indexSelectedCrop>0){
     		 Log.i("iddddddddddddddddddddddd",Integer.toString(indexSelectedCrop));
     		//System.out.println(indexSelectedCrop);
     		final String[] cropCode = res.getStringArray(R.array.crop_code);
         	
     		String cropname=cropss[indexSelectedCrop];
     		String cropcode=cropCode[indexSelectedCrop];
     		
     		
        	

     		WebServiceCrop ct = new WebServiceCrop(cropname, cropcode);
	  		 progressDialog = new ProgressDialog(CropSearch.this);				
         		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         		progressDialog.setMessage(getResources().getString(R.string.loading));
              progressDialog.show();
              ct.execute();
     		 }
     		 else{
     			 final AlertDialog.Builder alertbox3 = new AlertDialog.Builder(CropSearch.this);

     		       // set the message to display
     		       alertbox3.setMessage("Please Enter Valid Crop");

     		       // add a neutral button to the alert box and assign a click listener
     		       alertbox3.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

     		           // click listener on the alert box
     		           public void onClick(DialogInterface arg0, int arg1) {
     		               // the button was clicked
     		               Toast.makeText(getApplicationContext(), "The auto complete will show you the available crops when you type", Toast.LENGTH_LONG).show();
     		           }
     		       });
     		      alertbox3.show();
     		 }
             //}
         }	
         
            }
        });
              
     
        
  
   
        
        
        
    }
    
    
    
//Search method
    
    
	

    
	public void selfDestruct(View view) {
	    
	
	 }
	
	public int getIndex(String[] array, String specificValue){

		for(int i=0; i<array.length; i++){

		if(array[i].equals(specificValue)){

		return i;

		}

		}

		return -1;

		} 
	class WebServiceCrop extends AsyncTask<Object, Object, Parameters> {
		 String cropname, cropcode;
	    	//String selectedPlace;
	    	WebServiceCrop(String cropname, String cropcode){
	    	//	selectedPlace=selected;
	    		this.cropcode= cropcode;
	    		this.cropname= cropname;
	    	}
	    	
			@Override
			protected Parameters doInBackground(Object... params) {
				Parameters para = null;
				WebService service = new WebService();
				try{
				 para= service.search(cropname,cropcode);
				 Message message = webServiceCallHandler.obtainMessage();
					Bundle bundle = new Bundle();
					bundle.putSerializable("result", para);
					message.setData(bundle);
					webServiceCallHandler.sendMessage(message);
					
				}
				catch(Exception e){
					
				}
		      
		              		       
				return para;
				
				
				
			}
			@Override
			protected void onPostExecute(Parameters para){
				
			}
	    	
	    }


 	final Handler webServiceCallHandler = new Handler() {
   	public void handleMessage(Message message) {				
		//	@SuppressWarnings("unchecked")
			Parameters result = (Parameters) message.getData().getSerializable("result");
//			Log.v("rrrrrrrrrrrrrrr",result);
			
		    
		//    progressDialog.dismiss();

           Intent nextScreen = new Intent(CropSearch.this, Screen2.class);
           nextScreen.putExtra("object", result );
           nextScreen.putExtra("index", indexSelectedCrop );
           progressDialog.dismiss();
           CropSearch.this.startActivity(nextScreen);
		    //System.out.print(result);
		    // here i have used a text view to show only one from the retrieved text. U can use these get methods to show the data to the client. Check the WeatherParams class for further reference.
		    
   	}
   	
   };


}