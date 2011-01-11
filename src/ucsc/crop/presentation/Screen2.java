package ucsc.crop.presentation;




import ucsc.crop.objects.Parameters;
import android.app.Activity;
import android.os.Bundle;

import android.view.Window;

import android.widget.ImageView;
import android.widget.TextView;


public class Screen2 extends Activity
{
	TextView tv;
	Parameters param;
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
   public void onCreate(Bundle icicle)
   {
      super.onCreate(icicle);
     
      requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
      setContentView(R.layout.screen2);
      getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
      
      
     /* Button b = (Button) findViewById(R.id.btnClick2);
      b.setOnClickListener(new View.OnClickListener() {
         public void onClick(View arg0) {
         setResult(RESULT_OK);
         finish();
         } 
      });*/
      
      
      TextView tv = (TextView) findViewById(R.id.crop_name);
      TextView tvloc = (TextView) findViewById(R.id.crop_location);
      TextView tvpr1 = (TextView) findViewById(R.id.crop_price1);
      TextView tvpr2 = (TextView) findViewById(R.id.crop_price2);
      ImageView im=(ImageView) findViewById(R.id.crop_image);
     
      param = (Parameters) getIntent().getSerializableExtra("object");
      int value = getIntent().getExtras().getInt("index");

  	tv.setText(param.getName());
  	tvloc.setText(param.getLocation());
  	tvpr1.setText(param.getPrice1());
  	tvpr2.setText(param.getPrice2());
  	im.setImageResource(Icons[value]);
      
   }
}