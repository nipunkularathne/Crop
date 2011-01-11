package ucsc.crop.presentation;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Crop extends Activity {
    /** Called when the activity is first created. */
	private Button startButton;
	private Button selectButton;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);

        this.startButton = (Button) this.findViewById(R.id.start_button);
        
        this.startButton.setOnClickListener(new View.OnClickListener() {
  	
			public void onClick(View view) {
			
					Intent cropActivity = new Intent(Crop.this, CropSearch.class);
				
					startActivity(cropActivity);
				
			}
			 
		});
        
 this.selectButton = (Button) this.findViewById(R.id.select_button);
        
        this.selectButton.setOnClickListener(new View.OnClickListener() {
  	
			public void onClick(View view) {
			
					Intent cropActivity = new Intent(Crop.this, CropList.class);
				
					startActivity(cropActivity);
				
			}
			 
		});
    }
}