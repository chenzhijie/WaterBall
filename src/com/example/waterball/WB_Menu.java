package com.example.waterball;


import com.baidu.mobstat.StatService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WB_Menu extends BaseActivity {

	private Button WB_Button_New, WB_Button_Score,
			WB_Button_Exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wb_menu);
		Init();
	}

	
	private void Init() {
		// TODO Auto-generated method stub
		WB_Button_New = (Button) findViewById(R.id.id_newgamebtn);
		WB_Button_Score = (Button) findViewById(R.id.id_scorebtn);
		WB_Button_Exit = (Button) findViewById(R.id.id_exitgamebtn);

		WB_Button_New.setOnClickListener(new OnClick());
		WB_Button_Score.setOnClickListener(new OnClick());
		WB_Button_Exit.setOnClickListener(new OnClick());
	}

	private class OnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.id_newgamebtn:
				Intent intent = new Intent(WB_Menu.this, WB_Delay.class);
				intent.putExtra("place","0");
				startActivity(intent);
				finish();
				break;

			case R.id.id_scorebtn:
				Intent i = new Intent(WB_Menu.this, WB_Score.class);
				startActivity(i);
				break;

			case R.id.id_exitgamebtn:
				finish();
				break;
            }
		}

	}
	
	@Override
	public void onResume() {
		super.onResume();

		/**
		 * 页面起始（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 不能与StatService.onPageStart一级onPageEnd函数交叉使用
		 */
		StatService.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();

		/**
		 * 页面结束（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 不能与StatService.onPageStart一级onPageEnd函数交叉使用
		 */
		StatService.onPause(this);
	}
}
