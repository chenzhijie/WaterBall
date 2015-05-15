package com.example.waterball;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.mobstat.StatService;

public class WB_Delay extends BaseActivity {
	private LinearLayout WB_Bg_Animal;
	private int place;
	private int hp_num;
	private int score;
	private int wudi;
	private int bomb;
	private int capa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wb_delay);

		WB_Bg_Animal = (LinearLayout) findViewById(R.id.id_delay_layo);
		Intent intent=getIntent();
		 place=Integer.parseInt(intent.getStringExtra("place"));
		 if(place!=0)
		 {
		 hp_num=Integer.parseInt(intent.getStringExtra("hp"));
		  score=Integer.parseInt(intent.getStringExtra("score"));
		 wudi=Integer.parseInt(intent.getStringExtra("wudi"));
        bomb=Integer.parseInt(intent.getStringExtra("bomb"));
        capa=Integer.parseInt(intent.getStringExtra("capa"));
		 }
		Handle_Delay(place,1);
		Handle_Delay(place,1000);
		Handle_Delay(place,2000);
		Handle_Delay(place,3000);
		Handle_Delay(8,3500);
//
//		WB_Bg_Animal.setBackgroundResource(R.drawable.procsbar_1);
//         int a = 0;
//         System.out.println(a);


//		Handle_Delay(2, 2500);
//		Handle_Delay(3, 3000);
	}

	private void Handle_Delay(int chage, int time) {

		final int _Chage = chage;
		Handler _Animal_Handler = new Handler();
		
		_Animal_Handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				switch(_Chage)
				{
				case 0:
					WB_Bg_Animal.setBackgroundResource(R.drawable.guangzhou);
					
					
					break;
				case 1:
					WB_Bg_Animal.setBackgroundResource(R.drawable.beijing);
					break;
				case 2:
					WB_Bg_Animal.setBackgroundResource(R.drawable.shanghai);
					break;
				case 3:
					WB_Bg_Animal.setBackgroundResource(R.drawable.agela);
					break;
				case 4:
					WB_Bg_Animal.setBackgroundResource(R.drawable.bali);
					break;
				case 5:
					WB_Bg_Animal.setBackgroundResource(R.drawable.luoma);
					break;
				case 6:
					WB_Bg_Animal.setBackgroundResource(R.drawable.kailuo);
					break;
				case 7:
					WB_Bg_Animal.setBackgroundResource(R.drawable.newyork);
					break;
				case 8:
				   {
					Intent intent = new Intent(WB_Delay.this, WB_Main.class);
					intent.putExtra("place", String.valueOf(place));
					intent.putExtra("score", String.valueOf(score));
					intent.putExtra("hp", String.valueOf(hp_num));
					intent.putExtra("capa", String.valueOf(capa));
					intent.putExtra("wudi", String.valueOf(wudi));
					intent.putExtra("bomb", String.valueOf(bomb));
					startActivity(intent);
					finish();
				   }
					break;
				}
				
				
			}
		}, time);
	}
	@Override
	public void onResume() {
		super.onResume();
		StatService.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		StatService.onPause(this);
	}

}
