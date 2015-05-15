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

public class WB_Win extends BaseActivity{
	private ImageButton winbtn;

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
		setContentView(R.layout.wb_win);
		back=(RelativeLayout)findViewById(R.id.id_win_layo);
		capaText=(TextView)findViewById(R.id.id_win_capa);
        scoreText=(TextView)findViewById(R.id.id_win_score);
        winbtn=(ImageButton)findViewById(R.id.id_win_btn);
        

		Intent intent=getIntent();
	     place=Integer.parseInt(intent.getStringExtra("place"));
		  hp_num=Integer.parseInt(intent.getStringExtra("hp"));
		  score=Integer.parseInt(intent.getStringExtra("score"));
		 wudi=Integer.parseInt(intent.getStringExtra("wudi"));
         bomb=Integer.parseInt(intent.getStringExtra("bomb"));
         capa=Integer.parseInt(intent.getStringExtra("capa"));
        
        capaText.setText(String.valueOf(capa));
        scoreText.setText("ÓÎÏ··ÖÊý:"+String.valueOf(score));
    	back.setBackgroundResource(R.drawable.win_bg);
        
        winbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
			
				Intent goDelay=new Intent(WB_Win.this,WB_Menu.class);
				
				startActivity(goDelay);
				finish();
				
			}
	    });

	}
}
