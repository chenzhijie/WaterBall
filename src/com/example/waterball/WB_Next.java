package com.example.waterball;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WB_Next extends BaseActivity{
	private ImageButton nextbtn;

	private TextView capaText;
	private TextView scoreText;
	private RelativeLayout back;
	private int place;
	private int hp_num;
	private int score;
	private int wudi;
	private int bomb;
	private int capa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wb_next);
		back=(RelativeLayout)findViewById(R.id.id_next_layo);
		capaText=(TextView)findViewById(R.id.id_gznext_capa);
        scoreText=(TextView)findViewById(R.id.id_gznext_score);
        nextbtn=(ImageButton)findViewById(R.id.id_gznext_btn);
        

		Intent intent=getIntent();
	     place=Integer.parseInt(intent.getStringExtra("place"));
		  hp_num=Integer.parseInt(intent.getStringExtra("hp"));
		  score=Integer.parseInt(intent.getStringExtra("score"));
		 wudi=Integer.parseInt(intent.getStringExtra("wudi"));
         bomb=Integer.parseInt(intent.getStringExtra("bomb"));
         capa=Integer.parseInt(intent.getStringExtra("capa"));
        
        capaText.setText(String.valueOf(capa));
        scoreText.setText("ÓÎÏ··ÖÊý:"+String.valueOf(score));
        switch(place)
        {
        case 1:
        	back.setBackgroundResource(R.drawable.guangzhouend);
        	break;
        case 2:
        	back.setBackgroundResource(R.drawable.beijingend);
        	break;
        case 3:
        	back.setBackgroundResource(R.drawable.shanghaiend);
        	break;
        case 4:
        	back.setBackgroundResource(R.drawable.agelaend);
        	break;
        case 5:
        	back.setBackgroundResource(R.drawable.baliend);
        	break;
        case 6:
        	back.setBackgroundResource(R.drawable.luomaend);
        	break;
        case 7:
        	back.setBackgroundResource(R.drawable.kailuoend);
        	break;
        }
        
        nextbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
			
				Intent goDelay=new Intent(WB_Next.this,WB_Delay.class);
				goDelay.putExtra("place", String.valueOf(place));
				goDelay.putExtra("score", String.valueOf(score));
				goDelay.putExtra("hp", String.valueOf(hp_num));
				goDelay.putExtra("capa", String.valueOf(capa));
				goDelay.putExtra("wudi", String.valueOf(wudi));
				goDelay.putExtra("bomb", String.valueOf(bomb));
				startActivity(goDelay);
				finish();
				
			}
	    });

	}
}
