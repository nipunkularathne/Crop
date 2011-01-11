package ucsc.crop.presentation;







import ucsc.crop.objects.Parameters;
import ucsc.crop.webservice.WebService;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CropList extends ListActivity {
	ProgressDialog progressDialog;
	int cropPosition;
	private static class EfficientAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
      //  String cropCode;
     //   String code, name;
    //	String[] words;
    //	Parameters para=new Parameters();
        
        private static final int[] Icons = {
       		 R.drawable.abk,
       		 R.drawable.arc,
       	     R.drawable.asp,
       	     R.drawable.bns,
       	     R.drawable.bon,
       	     R.drawable.bon,
       	     R.drawable.brj,
       	     R.drawable.bigonion,
       	     R.drawable.btg,
       	     R.drawable.btr,
       	     R.drawable.cbg,
    		 R.drawable.cdm,
    		 R.drawable.cfe,
    	     R.drawable.cin,
    	     R.drawable.cin,
    	     R.drawable.cin,
    	     R.drawable.cin,
    	     R.drawable.clv,
    	     R.drawable.cnt,
    	     R.drawable.cps,
    	     R.drawable.crt,
    	     R.drawable.cuc,
       		 R.drawable.cwp,
       	     R.drawable.drc,
       	     R.drawable.elb,
       	     R.drawable.gcn,
       	     R.drawable.ggl,
       	     R.drawable.ggr,
       	     R.drawable.grc,
       	     R.drawable.grg,
       	     R.drawable.gtk,
       	     R.drawable.kki,
    		 R.drawable.kkn,
    	     R.drawable.kkr,
    	     R.drawable.klk,
    	     R.drawable.knk,
    	     R.drawable.cbg,
       	     R.drawable.kth,
       	     R.drawable.ktt,
       	     R.drawable.lbn,
       	     R.drawable.lfa,
       	     R.drawable.lks,
       	     R.drawable.lme,
    		 R.drawable.maz,
    	     R.drawable.mrg,
    	     R.drawable.ndr,
    	     R.drawable.okr,
    	     R.drawable.pay,
    	     R.drawable.pkn,
    	     R.drawable.ppr,
    	     R.drawable.ppr1,
       	     R.drawable.pto,
       		 R.drawable.pto,
       	     R.drawable.rds,
       	     R.drawable.ron,
       	     R.drawable.ron,
       	     R.drawable.sbr,
       	     R.drawable.sng,
    		 R.drawable.snk,
    	     R.drawable.swp,
    	     R.drawable.syb,
    	     R.drawable.tbt,
       		 R.drawable.tmr,
       	     R.drawable.tmrt,
       	     R.drawable.tmt,
       	     R.drawable.wib       	     
       		};
        Context adaptercontext;

        public EfficientAdapter(Context context) {
                       
           adaptercontext=context;
        	mInflater = LayoutInflater.from(adaptercontext);
          
        }

      
        public int getCount() {
            return crops.length;
        }

      
        public Object getItem(int position) {
            return position;
        }

     
        public long getItemId(int position) {
            return position;
        }

      
        public View getView(int position, View convertView, ViewGroup parent) {
         
            ViewHolder holder;

         
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.croplist_items, null);

                // Creates a ViewHolder and store references to the two children views
                // we want to bind data to.
                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.text);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);

                convertView.setTag(holder);
            } else {
                // Get the ViewHolder back to get fast access to the TextView
                // and the ImageView.
                holder = (ViewHolder) convertView.getTag();
            }

            // Bind the data efficiently with the holder.
            holder.text.setText(crops[position]);
            //holder.icon.setImageBitmap((position & 1) == 1 ? mIcon1 : mIcon2);
           holder.icon.setImageDrawable(adaptercontext.getResources().getDrawable(Icons[position])); 
            return convertView;
        }

        static class ViewHolder {
            TextView text;
            ImageView icon;
        }
    }
	static String[] crops ;
	
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		  
		 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		 setContentView(R.layout.crop_list);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
	  
	     
	         crops = getResources().getStringArray(R.array.crops_array);
	         final String[] cropCode = getResources().getStringArray(R.array.crop_code);
	       
	         setListAdapter(new EfficientAdapter(this));

	           
	           ListView l1 =  getListView();
	           l1.setAdapter(new EfficientAdapter(this));
   	 
	           l1.setOnItemClickListener(new OnItemClickListener() {
		   
		  	public void onItemClick(AdapterView<?> arg0, View view, int cropPosition,long arg3) {
		 
		  		String cropname=crops[cropPosition];
	     		String cropcode=cropCode[cropPosition];
	     		
		  		
	     		WebServiceCrop ct = new WebServiceCrop(cropname, cropcode);
		  		 progressDialog = new ProgressDialog(CropList.this);				
	          		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	          		progressDialog.setMessage(getResources().getString(R.string.loading));
	               progressDialog.show();
            	ct.execute();
            	
            	
        	       	       	 
            	
                
		  }
	  });
	     
	     
	        
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

            Intent nextScreen = new Intent(CropList.this, Screen2.class);
            nextScreen.putExtra("object", result );
            nextScreen.putExtra("index", cropPosition );
            progressDialog.dismiss();
            CropList.this.startActivity(nextScreen);
		    //System.out.print(result);
		    // here i have used a text view to show only one from the retrieved text. U can use these get methods to show the data to the client. Check the WeatherParams class for further reference.
		    
    	}
    	
    };


}
