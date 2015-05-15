package com.example.waterball;


import com.baidu.mobstat.StatService;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
 
public class WB_Wel extends BaseActivity {

	private ImageView WB_Wel_Animal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wb_wel);

		WB_Wel_Animal = (ImageView) findViewById(R.id.id_wb_wel_animal);

		WB_Wel_Animal.setBackgroundResource(R.drawable.procsbar_1);
         int a = 0;
         System.out.println(a);
		Handle_Delay(0, 1000);
		Handle_Delay(1, 2000);
		Handle_Delay(2, 2500);
		Handle_Delay(3, 3000);
	}

	private void Handle_Delay(int chage, int time) {

		final int _Chage = chage;
		Handler _Animal_Handler = new Handler();
		_Animal_Handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (_Chage == 0) {
					WB_Wel_Animal
							.setBackgroundResource(R.drawable.procsbar_2);
				} else if (_Chage == 1) {
					WB_Wel_Animal
							.setBackgroundResource(R.drawable.procsbar_3);
				}  else if (_Chage == 2) {
					WB_Wel_Animal
					.setBackgroundResource(R.drawable.procsbar_4);
		         } 
				else {
					Intent intent = new Intent(WB_Wel.this, WB_Menu.class);
					startActivity(intent);
					finish();
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