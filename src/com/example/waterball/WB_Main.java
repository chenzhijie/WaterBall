package com.example.waterball;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.ccColor4B;

import android.content.Intent;
import android.os.Bundle;

import com.baidu.mobstat.StatService;


public class WB_Main extends BaseActivity {

	private CCGLSurfaceView _WB_Surface;
	private CCScene _WB_Scene;
	private int place;
	private int hp_num;
	private int score;
	private int wudi;
	private int bomb;
	private int capa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_WB_Surface = new CCGLSurfaceView(this);
		setContentView(_WB_Surface);
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
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		CCDirector.sharedDirector().end();
		CCTextureCache.sharedTextureCache().removeAllTextures();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		CCDirector.sharedDirector().pause();
		StatService.onPause(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		CCDirector.sharedDirector().resume();
		StatService.onResume(this);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// cocos2d里习惯以左下角作为原点,时间单位是秒
		CCDirector.sharedDirector().attachInView(_WB_Surface);// 把cocos2d绑定在GLSurfaceView这个载体上
		// CCDirector.sharedDirector().setDeviceOrientation(
		// CCDirector.kCCDeviceOrientationLandscapeLeft);
		// CCDirector.sharedDirector().setDisplayFPS(true);//显示 FPS
		// CCDirector.sharedDirector().setAnimationInterval(1.0f / 60.0f);//
		// 每秒的贞数
		_WB_Scene = CCScene.node();
        System.out.println("place:"+place);
        System.out.println("place:"+hp_num);
        System.out.println("place:"+capa);
        System.out.println("place:"+score);
        System.out.println("place:"+wudi);
        System.out.println("place:"+bomb);
        switch(place)
        {
        case 0:
		WB_Play _Layer = new WB_Play(ccColor4B.ccc4(255, 255, 255, 255));
//		_Layer.GetContext(FeiJi_Main.this);
		_WB_Scene.addChild(_Layer);
        break;
        
        case 1:
        	WB_Play1 _Layer1 = new WB_Play1(ccColor4B.ccc4(255, 255, 255, 255),place,hp_num,capa,score,wudi,bomb);
//    		_Layer.GetContext(FeiJi_Main.this);
    		_WB_Scene.addChild(_Layer1);
        	break;
        case 2:
        	WB_Play2 _Layer2 = new WB_Play2(ccColor4B.ccc4(255, 255, 255, 255),place,hp_num,capa,score,wudi,bomb);
//    		_Layer.GetContext(FeiJi_Main.this);
    		_WB_Scene.addChild(_Layer2);
        	break;
        case 3:
        	WB_Play3 _Layer3 = new WB_Play3(ccColor4B.ccc4(255, 255, 255, 255),place,hp_num,capa,score,wudi,bomb);
//    		_Layer.GetContext(FeiJi_Main.this);
    		_WB_Scene.addChild(_Layer3);
        	break;
        case 4:
        	WB_Play4 _Layer4= new WB_Play4(ccColor4B.ccc4(255, 255, 255, 255),place,hp_num,capa,score,wudi,bomb);
//    		_Layer.GetContext(FeiJi_Main.this);
    		_WB_Scene.addChild(_Layer4);
        	break;
        case 5:
        	WB_Play5 _Layer5= new WB_Play5(ccColor4B.ccc4(255, 255, 255, 255),place,hp_num,capa,score,wudi,bomb);
//    		_Layer.GetContext(FeiJi_Main.this);
    		_WB_Scene.addChild(_Layer5);
        	break;
        case 6:
        	WB_Play6 _Layer6= new WB_Play6(ccColor4B.ccc4(255, 255, 255, 255),place,hp_num,capa,score,wudi,bomb);
//    		_Layer.GetContext(FeiJi_Main.this);
    		_WB_Scene.addChild(_Layer6);
        	break;
        case 7:
        	WB_Play7 _Layer7= new WB_Play7(ccColor4B.ccc4(255, 255, 255, 255),place,hp_num,capa,score,wudi,bomb);
//    		_Layer.GetContext(FeiJi_Main.this);
    		_WB_Scene.addChild(_Layer7);
        	break;
        }
		CCDirector.sharedDirector().runWithScene(_WB_Scene);// 运行场景

		CCDirector.sharedDirector().pause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		CCDirector.sharedDirector().end();
	}
}
