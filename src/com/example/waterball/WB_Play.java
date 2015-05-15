package com.example.waterball;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.cocos2d.actions.base.CCFiniteTimeAction;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;











import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;



public class WB_Play extends CCColorLayer {

	private List<WB_Sprite> _Drip = new CopyOnWriteArrayList<WB_Sprite>();//水滴
	private List<WB_Sprite> _BigDrip=new CopyOnWriteArrayList<WB_Sprite>();//瀑布
	private List<CCSprite> _Dec_Prop = new CopyOnWriteArrayList<CCSprite>();//水量减少道具
	private List<CCSprite> _Inc_Prop = new CopyOnWriteArrayList<CCSprite>();//水量增加道具
	private List<CCSprite> _Wudi_Prop = new CopyOnWriteArrayList<CCSprite>();//无敌道具
	private List<CCSprite> _Bomb_Prop = new CopyOnWriteArrayList<CCSprite>();//全秒炸弹道具
	private List<CCSprite> _IncDec_Prop = new CopyOnWriteArrayList<CCSprite>();
	private List<CCSprite> _WudiBomb_Prop = new CopyOnWriteArrayList<CCSprite>();
//	private List<WB_Sprite> _AllDrip = new CopyOnWriteArrayList<WB_Sprite>();//所有水滴
	private List<Long> Award_Score=new ArrayList<Long>();
	private CCLabel _ScoreLabel;//分数
	private CCLabel _Hp_Num;
	private CCLabel _Wudi_Num;//红色炸弹数字
	private CCLabel _Bomb_Num;//红色炸弹数字
	private CCLabel _Game_Date;
	private CCLabel _Capacity;
	private CCLabel _Tip;
	private CCLabel _PropTip;
	private CCLabel _Wudi_Jia1;
	private CCLabel _Bomb_Jia1;
	private CCLabel _Capa_UpDown;
	
	private CCSprite _WB_Back;
	private CCSprite _Wudi;//红色炸弹
	private CCSprite _Bomb;//红色炸弹
	private CCSprite _WB_Play;
	private CCSprite _WB_Pause;//暂停
	private CCSprite _WB_Hp1;
	private CCSprite _WB_Hp2;
	private CCSprite _WB_Hp3;
	private CCSprite  _Ban20;
//	private CCSprite  _Ban4;
	
	
	private String gameDate;
	private String originDate;
	
	private CGPoint _Touch_Location;//触摸坐标	
	
	private String _Cookie_Font = "Cookies.ttf";//字体
	
	private String _WB_Back_Path = "images/playgamebg.png";//屏幕背景
//	private String _WB_Back_Path1 = "images/playgamebg1.png";//屏幕背景
//	private String _WB_Back_Path2 = "images/playgamebg2.png";//屏幕背景
//	private String _WB_Back_Path3 = "images/playgamebg3.png";//屏幕背景
	private String _SmallDrip_Path = "images/drip.png";//小drip
	private String _BigDrip_Path = "images/bigdrip.png";//bigdrip
	private String _Inc_Down_Path="images/incre.png";
	private String _Dec_Down_Path="images/decre.png";
	private String _Play_Path = "images/waterball.png";
	private String _Play0_Path = "images/waterball0.png";
	private String _Play44_Path = "images/waterball44.png";
	private String _Play64_Path = "images/waterball64.png";
	private String _Play84_Path = "images/waterball84.png";
	private String _Play104_Path = "images/waterball104.png";
	private String _Play124_Path = "images/waterball124.png";
	
	private String _Pause_Path = "images/pause.png";
	private String _Hp1_Path="images/hp.png";
	private String _Hp2_Path="images/hp.png";
	private String _Hp3_Path="images/hp.png";
	private String _Bomb_Down_Path = "images/bombdown.png";
	private String _Bomb_Path = "images/bomb.png";
	
	private String _Wudi_Down_Path = "images/wudidown.png";
	private String _Wudi_Path = "images/wudi.png";
	
	
	private long _Get_Score = 0;// 获得的分数
//	private float _Shot_Du = 0.5f;// 子弹速度
	private boolean _Can_Move = false;// 是否在对象上移动
	private CGSize _WinSize;
//	private int _Play_Image_Chage = 1;// 飞机图片判断
	private float _Down_Distance=0;
    private float _Down_Duration=0;
	
	private String _Play_Sequence_Path = "images/play_seq.png";
	private int _Big_Life = 16, _Middle_Life = 8, _Small_life = 2;// 敌机生命
//	private int _ChangeImage_Delay = -1;// 图片改变延时
	private int _Pause_OR = -1;// 暂停点击判断
	private int Wudi_Num=0;
	private int Bomb_Num =0;// 红炸弹数量
	private int Hp_Num=3;
	private int Prop_Down_Time = 30;// 蓝子弹和红炸弹的随机数
	private int DripDown_Time = 8;// 敌机的下落速度
	private int Capa=44;//水的量
//	private int origin_capa=123;//用来对比的水量
	private int Season=1;
	private int non_drip=0;//此变量来判断是否在3秒内没有收水
	
	private int win_count=0;//通关变量
	private int punish4_count=0;
	private int punish20_count=0;
	private int wudi_count=0;
	private SharedPreferences _Share;
	
	private String ScoreList = "0;0;0;0;0;0;0;0;0;0";
	private Dialog _Dialog;
//	private boolean _Invincible = false;//无敌
   //两个布尔值判断是否在一次生命值内接受过20% 4%的水量惩罚
	private boolean _Recv_Punish20=false;
    private boolean _Recv_Punish4=false;
    private boolean usewudi=false;
    
    private boolean haveChangePic0=false;
    private boolean haveChangePic24=false;
    private boolean haveChangePic44=false;
    private boolean haveChangePic64=false;
    private boolean haveChangePic84=false;
    private boolean haveChangePic104=false;
    private boolean haveChangePic124=false;
    private String isWin;
	protected WB_Play(ccColor4B color) {
		super(color);
		// TODO Auto-generated constructor stub
		Init();
	}

	private void Init() {
		// TODO Auto-generated method stub
		_Share = CCDirector.sharedDirector().getActivity()
				.getSharedPreferences("Share", Context.MODE_PRIVATE);
		_WinSize = CCDirector.sharedDirector().displaySize();// 获取屏幕大小
		setIsTouchEnabled(true);
       
		genDate();
		setWinTipOr(originDate);
		CCSprite _WB_Back=CCSprite.sprite(_WB_Back_Path);
//		setSeason();
//		
//		switch(Season)
//		{
//		case 1:
//		    _WB_Back = CCSprite.sprite(_WB_Back_Path1);
//		     break;
//		case 2:
//			_WB_Back = CCSprite.sprite(_WB_Back_Path2);
//			break;
//		case 3:
//			 _WB_Back = CCSprite.sprite(_WB_Back_Path3);
//			break;
//		case 4:
//			 _WB_Back = CCSprite.sprite(_WB_Back_Path);
//			break;
//			
//		}
		// 背景按屏幕比例放大
		
		_WB_Back.setScaleX(_WinSize.width/ _WB_Back.getTexture().getWidth());
		_WB_Back.setScaleY(_WinSize.height/ _WB_Back.getTexture().getHeight());
		_WB_Back.setPosition(CGPoint.make(_WinSize.width / 2,_WinSize.height / 2));// 默认中心点为左下角，将背景中心点移到屏幕中间
		addChild(_WB_Back);// 将背景添加到场景
		
		for(int i=1;i<11;i++)
		{
			long mi = 2 <<(i-1);
			Award_Score.add(mi*10000);
		}
		
		AddHp();
       AddDate();
	
		_WB_Pause = CCSprite.sprite(_Pause_Path);
		_WB_Pause.setPosition(CGPoint.make(_WinSize.width-_WB_Pause.getContentSize().width , _WinSize.height
						- _WB_Pause.getContentSize().height -_WB_Hp1.getContentSize().height/ 2 - 1));
		System.out.println(_WinSize.height+","+_WB_Pause.getContentSize().height);
		addChild(_WB_Pause);// 添加暂停
       
		
		AddScore();
		
		AddCapa();
		
		this.schedule("GameDrips",0.5f);
		this.schedule("DateChange",1.0f);
		this.schedule("CapaChange1",0.5f);
		this.schedule("CapaChange2",2.0f);
//		//here
//		this.schedule("GameFoes", 0.5f);// 一秒执行一次
//		this.schedule("GameShot", 0.2f);
		GamePlay();
//		this.schedule("AddPlay", 0.2f);
		this.schedule("Detection", 0f);
		this.schedule("AddIncDecDown",2.0f);
//		this.schedule("AddBigFoe", 0.2f);
//		
		AddBomb();
		AddWudi();
	}
	private void genDate()
	{
		Random rand = new Random();
		int randomValue=1+rand.nextInt(12);
		int eachDay[]=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		int month=randomValue;
		int day=1+rand.nextInt(eachDay[month-1]);
		if(month<10)
		{
			   gameDate="0";
			   
		}	 
		else
		{	
			  gameDate="";
		}	  
		gameDate+=String.valueOf(month);
		
		if(day<10)
			{
			  gameDate+="0";
			}
		gameDate+=String.valueOf(day);
		setOriginDate(gameDate);
		
	}
	private void setWinTipOr(String s)
	{
		int eachDay[]=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		int month=Integer.parseInt(s)/100;
		int day=Integer.parseInt(s)%100;
		//月末
		if(day==1)
		{
			month--;
			day=eachDay[month-1];
		}
		else
		{
			day--;
		}
		if(month<10)
			isWin="0";
		else
			isWin="";
		isWin+=String.valueOf(month);
		if(day<10)
			isWin+="0";
		isWin+=String.valueOf(day);
		System.out.println("isWin:"+isWin);
	}
	private void setOriginDate(String s)
	{
		int eachDay[]=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		int month=Integer.parseInt(s)/100;
		int day=Integer.parseInt(s)%100;
		//月末
		if(day==1)
		{
			month--;
			day=eachDay[month-1];
		}
		else
		{
			day--;
		}
		if(month<10)
			originDate="0";
		else
			originDate="";
		originDate+=String.valueOf(month);
		if(day<10)
			originDate+="0";
		originDate+=String.valueOf(day);
		
		System.out.println("origin:"+originDate);
	}
	private void StopSchedule() {
		this.unschedule("GameDrips");
		this.unschedule("DateChange");
		this.unschedule("CapaChange1");
		this.unschedule("CapaChange2");
		this.unschedule("AddIncDecDown");
		this.unschedule("Dection");
	}
	public void AddIncDecDown(float t)
	{
		Random rand = new Random();
		int randomValue=rand.nextInt(50);//50
		CCSprite _Inc_Dec_Down = null;

		if (randomValue != 0 && randomValue != 1&& randomValue != 2&& randomValue != 3) {
			return;
		}
		if (_IncDec_Prop.size() >= 1&& randomValue != 2&& randomValue != 3) {
			return;
		}
		if(_WudiBomb_Prop.size()>=1&&randomValue != 0 && randomValue != 1)
		{
			return;
		}

		if (randomValue == 0 && _IncDec_Prop.size() < 1) {
			 _Inc_Dec_Down = CCSprite.sprite(_Inc_Down_Path);
			 _Inc_Dec_Down.setTag(0);
		} else if (randomValue == 1 && _IncDec_Prop.size() < 1) {
			_Inc_Dec_Down= CCSprite.sprite(_Dec_Down_Path);
			_Inc_Dec_Down.setTag(1);
		}
		else if (randomValue == 2 && _WudiBomb_Prop.size() < 1) {
			_Inc_Dec_Down= CCSprite.sprite(_Wudi_Down_Path);
			_Inc_Dec_Down.setTag(2);
		}else if (randomValue == 3 && _WudiBomb_Prop.size() < 1) {
			_Inc_Dec_Down= CCSprite.sprite(_Bomb_Down_Path);
			_Inc_Dec_Down.setTag(3);
		}
		int minX = (int) (_Inc_Dec_Down.getContentSize().width / 2.0f);
		int maxX = (int) (_WinSize.width - _Inc_Dec_Down.getContentSize().width / 2.0f);
		int rangeX = maxX - minX;
		int actualX = rand.nextInt(rangeX) + minX;
		_Inc_Dec_Down
				.setPosition(actualX, _Inc_Dec_Down.getContentSize().height
						/ 2.0f + _WinSize.height);
		addChild(_Inc_Dec_Down);
		switch(randomValue)
		{
		case 0:
			_Inc_Prop.add(_Inc_Dec_Down);
			System.out.println(_Inc_Prop.size()+",");
			_IncDec_Prop.add(_Inc_Dec_Down);
			break;
		case 1:
			_Dec_Prop.add(_Inc_Dec_Down);
			System.out.println(_Dec_Prop.size()+",");
			_IncDec_Prop.add(_Inc_Dec_Down);
			break;
		case 2:
			_Wudi_Prop.add(_Inc_Dec_Down);
			System.out.println(_Wudi_Prop.size()+",");
			_WudiBomb_Prop.add(_Inc_Dec_Down);
			break;
		case 3:
			_Bomb_Prop.add(_Inc_Dec_Down);
			System.out.println(_Bomb_Prop.size()+",");
			_WudiBomb_Prop.add(_Inc_Dec_Down);
			break;
		}
		
		CCFiniteTimeAction fs_timeAction = CCMoveTo.action(1,
				CGPoint.ccp(actualX, _WinSize.height / 3 * 2));// 时间内移动

		CCCallFuncN fs_back = CCCallFuncN.action(this, "Prop_Back");
		CCSequence fs_actions = CCSequence.actions(fs_timeAction, fs_back);
		_Inc_Dec_Down.runAction(fs_actions);
	}
	/**
	 * 道具下落后返回
	 */
	public void Prop_Back(Object sender) {
		CCSprite prop = (CCSprite) sender;
		prop.setPosition(prop.getPosition().x, _WinSize.height / 3 * 2);
		CCFiniteTimeAction fs_timeAction = CCMoveTo.action(
				0.5f,
				CGPoint.ccp(prop.getPosition().x, _WinSize.height / 3 * 2
						+ _WinSize.height / 6));// 时间内移动
		CCCallFuncN fs_back = CCCallFuncN.action(this, "Prop_Back2");
		CCSequence fs_actions = CCSequence.actions(fs_timeAction, fs_back);
		prop.runAction(fs_actions);
	}

	/**
	 * 道具下落返回后下降
	 */
	public void Prop_Back2(Object sender) {
		CCSprite prop = (CCSprite) sender;
		prop.setPosition(prop.getPosition().x,
				_WinSize.height / 3 * 2 + 50);
		CCFiniteTimeAction fs_timeAction = CCMoveTo.action(
				1.0f,
				CGPoint.ccp(prop.getPosition().x,
						-(prop.getContentSize().height / 2)));// 时间内移动
		CCCallFuncN fs_Over = CCCallFuncN.action(this, "Prop_Over");
		CCSequence fs_actions = CCSequence.actions(fs_timeAction, fs_Over);
		prop.runAction(fs_actions);
	}
   /*
    * 结束下落道具
    */
	public void Prop_Over(Object sender) {
		CCSprite prop_over = (CCSprite) sender;
		prop_over.removeSelf();
		//判断清除的是哪个道具
		if(_IncDec_Prop.size()>=1)
		_IncDec_Prop.remove(prop_over);
		else
			_WudiBomb_Prop.remove(prop_over);
		if(_Inc_Prop.size()>=1)
			_Inc_Prop.remove(prop_over);
		if(_Dec_Prop.size()>=1)
			_Dec_Prop.remove(prop_over);
		if(_Wudi_Prop.size()>=1)
			_Wudi_Prop.remove(prop_over);
		if(_Bomb_Prop.size()>=1)
			_Bomb_Prop.remove(prop_over);
	}
	public void DateChange(float t)
	{
		
		int eachDay[]=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		int month=Integer.parseInt(gameDate)/100;
		int day=Integer.parseInt(gameDate)%100;
		//月末
		if(day==eachDay[month-1])
		{
			day=1;
			if(month==12)
			month=1;
			else 
				month++;
		}
		else
		{
			day++;
		}
		if(month<10)
			gameDate="0";
		else
			gameDate="";
		gameDate+=String.valueOf(month);
		if(day<10)
			gameDate+="0";
		gameDate+=String.valueOf(day);
		
		System.out.println(gameDate+"!");
//		if(day==1)
//		{
//			if(month==12||month==3||month==6||month==9)
//			{
//			
//			switch(month)
//			{
//			case 3:
//				AddTip("春");
//				_Tip.removeSelf();
//				break;
//			case 6:
//				AddTip("夏");
//				_Tip.removeSelf();
//				break;
//			case 9:
//				AddTip("秋");
//				_Tip.removeSelf();
//				break;
//			case 12:
//				AddTip("冬");
//				_Tip.removeSelf();
//				break;
//			}
//			}
//		}
		
		System.out.println("gameDate:"+gameDate);
		System.out.println("originDate:"+originDate);
		String sp="0228";
		String su="0531";
		String au="0831";
		String wi="1131";
		
		
		if(gameDate.equals(sp))
			{
			
			   AddTip("春");
			   ReMoveAll();
			}
		if((gameDate.equals("0301")||gameDate.equals("0601")||gameDate.equals("0901")||gameDate.equals("1201"))&&_Tip!=null)
			_Tip.removeSelf();
		if(gameDate.equals(su))
		{
		
		   AddTip("夏");
		
		}
		if(gameDate.equals(au))
		{
		
		   AddTip("秋");
		 
		}
		if(gameDate.equals(wi))
		{
		
		   AddTip("冬");
		   
		}
		if(gameDate.equals(isWin))
			AddTip("通关");
		if(gameDate.equals(originDate))
		{
			
			WinGame();
		}
		
		AddDate();
	}
	private void ChangeCapa(int i)
	{
		Capa+=i;
		if(Capa<=0)Capa=0;
		if(Capa>=124)Capa=124;
		AddCapa();
		
	}
	private void ChangePlayPic(String s){
		float x=_WB_Play.getPosition().x;
		float y=_WB_Play.getPosition().y;
		_WB_Play.removeSelf();
		_WB_Play=CCSprite.sprite(s);
		_WB_Play.setPosition(x,y);
		addChild(_WB_Play);
	}
	public void CapaChange1(float t)
	{
		//差水晶球接触到水时，水量的增加代码。接触到障碍的代码 。使用了道具的代码;两种惩罚的实现代码；
		//容量减少为0时，生命降低
		if(Capa>0&&Capa<44&&!haveChangePic24)
		{
			ChangePlayPic(_Play_Path);
			haveChangePic24=true;
			haveChangePic0=false;
			haveChangePic44=false;
			haveChangePic64=false;
			haveChangePic84=false;
			haveChangePic104=false;
			haveChangePic124=false;
			
		}
		if(Capa>=44&&Capa<64&&!haveChangePic44)
		{
			ChangePlayPic(_Play44_Path);
			haveChangePic44=true;
			haveChangePic0=false;
			haveChangePic24=false;
			haveChangePic64=false;
			haveChangePic84=false;
			haveChangePic104=false;
			haveChangePic124=false;
		}
		if(Capa>=64&&Capa<84&&!haveChangePic64)
		{
			ChangePlayPic(_Play64_Path);
			haveChangePic64=true;
			haveChangePic0=false;
			haveChangePic44=false;
			haveChangePic24=false;
			haveChangePic84=false;
			haveChangePic104=false;
			haveChangePic124=false;
		}
		if(Capa>=84&&Capa<104&&!haveChangePic84)
		{
			ChangePlayPic(_Play84_Path);
			haveChangePic84=true;
			haveChangePic0=false;
			haveChangePic44=false;
			haveChangePic64=false;
			haveChangePic24=false;
			haveChangePic104=false;
			haveChangePic124=false;
		}
		if(Capa>=104&&Capa<124&&!haveChangePic104)
		{
			ChangePlayPic(_Play104_Path);
			haveChangePic104=true;
			haveChangePic0=false;
			haveChangePic44=false;
			haveChangePic64=false;
			haveChangePic84=false;
			haveChangePic24=false;
			haveChangePic124=false;
		}
    	if(Capa<=0&&!haveChangePic0)
    	{
    		
             ChangePlayPic(_Play0_Path);
             haveChangePic0=true;
 			haveChangePic24=false;
 			haveChangePic44=false;
 			haveChangePic64=false;
 			haveChangePic84=false;
 			haveChangePic104=false;
 			haveChangePic124=false;
    		Hp_Num--;
    		//两次惩罚效果更新为初始false，即新生命开始，会接受新的惩罚如果低于20%与4%,两个变量设置的目的是防止多次惩罚
    		_Recv_Punish20=false;
    		_Recv_Punish4=false;
    		punish20_count=0;
    		punish4_count=0;
    		if(Hp_Num>0)
    		{
    			AddHp();
    			Capa=44;
//    			origin_capa=24;
    		}
    		else
    		{
    			AddHp();
    			StopSchedule();
    			PlaySpriteAnimationFinished();
				
    			//lose Game
    		}
    	}
    	if(Capa>0&&Capa<=4&&!_Recv_Punish4)
    	{
    		_Recv_Punish4=true;
    	}
//    	System.out.println("Capa:"+Capa);
//    	System.out.println("punish20:"+_Recv_Punish20);
    	if(Capa>0&&Capa<=20&&!_Recv_Punish20)
    	{
    		_Recv_Punish20=true;
//    		System.out.println("punish20:"+_Recv_Punish20);
    	}
    	if(Capa>=124&&!haveChangePic124)
    	{
    		ChangePlayPic(_Play124_Path);
    		haveChangePic124=true;
			haveChangePic0=false;
			haveChangePic44=false;
			haveChangePic64=false;
			haveChangePic84=false;
			haveChangePic104=false;
			haveChangePic24=false;
    		Hp_Num++;
    		Bomb_Num++;
    		Capa=44;
//    		origin_capa=24;
    		_Recv_Punish20=false;
    		_Recv_Punish4=false;
    		punish20_count=0;
    		punish4_count=0;
    		AddHp();
    		AddBomb();
    		AddBombJia1();
    	}
    
	}
	/**
	 * 添加主机消失动画
	 */
//	private void AddPlaySpriteAnimal(CGPoint touchRect, String Path, int CutW,
//			int CutH, int Cut) {
//
//		CCSpriteSheet boomSheet = CCSpriteSheet.spriteSheet(Path);
//
//		this.addChild(boomSheet);
//
//		CCSprite Sprite = CCSprite.sprite(boomSheet.getTexture(),
//				CGRect.make(0, 0, CutW, CutH));
//		boomSheet.addChild(Sprite);
//		Sprite.setPosition(touchRect.x, touchRect.y);
//		int frameCount = 0;
//
//		ArrayList<CCSpriteFrame> boomAnimFrames = new ArrayList<CCSpriteFrame>();
//
//		for (int y = 0; y < 1; y++) {
//			for (int x = 0; x < Cut; x++) {
//				CCSpriteFrame frame = CCSpriteFrame.frame(
//						boomSheet.getTexture(),
//						CGRect.make(x * CutW, y * CutH, CutW, CutH),
//						CGPoint.ccp(0, 0));
//				boomAnimFrames.add(frame);
//				frameCount++;
//				if (frameCount == Cut)
//					break;
//			}
//		}
//		CCAnimation boomAnimation = CCAnimation.animation("", (float) 0.2,
//				boomAnimFrames);
//
//		CCAnimate boomAction = CCAnimate.action(boomAnimation);
//
//		CCCallFuncN actionAnimateDone = CCCallFuncN.action(this,
//				"PlaySpriteAnimationFinished");
//		CCSequence actions = CCSequence.actions(boomAction, actionAnimateDone);
//
//		Sprite.runAction(actions);
//	}
	public void PlaySpriteAnimationFinished() {
		if (_Share != null) {
			if (_Share != null)
				ScoreList = _Share.getString("SCORE", "0;0;0;0;0;0;0;0;0;0");
			String[] Scores = ScoreList.split(";");
			String[] Scores2 = ScoreList.split(";");
			String _Score_Value = "";
			boolean or = false;
			for (int i = 0; i < Scores.length; i++) {
				if (_Get_Score > Integer.valueOf(Scores[i]) && !or) {
					Scores[i] = _Get_Score + "";
					or = true;
					int j = i;
					while (j < Scores.length - 1) {
						Scores[j + 1] = Scores2[j];
						j++;
					}
				}
				if (i >= Scores.length - 1)
					_Score_Value += Scores[i];
				else
					_Score_Value += Scores[i] + ";";
			}
			Editor e = _Share.edit();
			e.putString("SCORE", _Score_Value);
			e.commit();
		}
		
		ShowDialog();
	}
	private void ShowDialog() {
		CCDirector.sharedDirector().getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(CCDirector
						.sharedDirector().getActivity());
				View v = inflater.inflate(R.layout.wb_dialog, null);// 得到加载view
				_Dialog = new Dialog(CCDirector.sharedDirector().getActivity(),
						R.style.Dialog_Style);
				_Dialog.setCancelable(false);
				_Dialog.setCanceledOnTouchOutside(false);
				_Dialog.setContentView(v);// 设置布局
				_Dialog.show();
				TextView _Score_TV = (TextView) v
						.findViewById(R.id.id_dialog_score);
				_Score_TV.setText(_Get_Score + "");
				Button _Return = (Button) v
						.findViewById(R.id.id_dialog_return);
				_Return.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						IntentToBack();
						_Dialog.dismiss();
					}
				});
			}
		});

	}

	private void IntentToBack() {
		Intent mainScore = new Intent(
				CCDirector.sharedDirector().getActivity(),WB_Menu.class);
		CCDirector.sharedDirector().getActivity().startActivity(mainScore);
		CCDirector.sharedDirector().getActivity().finish();
	}
	
	/*
	 * 每三秒判断是否长时间内没有接触到水滴，若是，则水量-1；
	 */
    public void CapaChange2(float t)
    {
    	//是否需要排除24待考虑
    	if(non_drip>=350)
    	{
    		setSeason();
    		//各个季节水量减少快慢
    		switch(Season)
    		{
    		case 1:
    			Capa--;
    			if(Capa<0)Capa=0;
    			break;
    		case 2:
    			Capa-=3;
    			if(Capa<0)Capa=0;
    			break;
    		case 3:
    			Capa-=2;
    			if(Capa<0)Capa=0;
    			break;
    		case 4:
    			Capa--;
    			if(Capa<0)Capa=0;
    			break;
    		}
    		
    		non_drip=0;
    	}
//    	origin_capa=Capa;
    	
    	AddCapa();
    	if(_Capa_UpDown!=null)
    	{
    		_Capa_UpDown.removeSelf();
    	}
    	 if(_Wudi_Jia1!=null)
			 _Wudi_Jia1.removeSelf();
    	 if(_Bomb_Jia1!=null)
 			_Bomb_Jia1.removeSelf();
    	 
    	 if(_PropTip!=null)
    	 {
    		 _PropTip.removeSelf();
    		 
    	 }
    }
	public void GameDrips(float t) {
 
               AddDrip();
  	}

	public void GamePlay() {

		AddPlay();
	}
	
	private void AddHp()
	{

		 if(_WB_Hp1!=null)
		 {
		 	_WB_Hp1.removeSelf();
		 }
		 if(_WB_Hp2!=null)
		 {
			_WB_Hp2.removeSelf();
		 }
		 if(_WB_Hp3!=null)
		 {
			_WB_Hp3.removeSelf();
		 }
		 
		
		 if(Hp_Num>0)
		 {
			
			 _WB_Hp1=CCSprite.sprite(_Hp1_Path);
	         _WB_Hp1.setPosition(CGPoint.make(_WB_Hp1.getContentSize().width / 2 + 1, _WinSize.height
						- _WB_Hp1.getContentSize().height / 2 - 1));
		      addChild(_WB_Hp1);// 添加hp1
		      if(Hp_Num==2||Hp_Num==3)
		      {
		    	  _WB_Hp2=CCSprite.sprite(_Hp2_Path);
			      _WB_Hp2.setPosition(CGPoint.make(_WB_Hp2.getContentSize().width+_WB_Hp1.getContentSize().width / 2 + 1, _WinSize.height
								- _WB_Hp2.getContentSize().height / 2 - 1));
				  addChild(_WB_Hp2);// 添加hp2
		      }
		      if(Hp_Num==3)
		      {
		    	  _WB_Hp3=CCSprite.sprite(_Hp3_Path);
				   _WB_Hp3.setPosition(CGPoint.make(_WB_Hp3.getContentSize().width +_WB_Hp2.getContentSize().width+_WB_Hp1.getContentSize().width/ 2 + 1, _WinSize.height
									- _WB_Hp3.getContentSize().height / 2 - 1));
				addChild(_WB_Hp3);// 添加hp1
		      }
		      if(Hp_Num>3)
		      {
		    	  //上限24
		    	  Hp_Num=Hp_Num>24?24:Hp_Num;
		    	  
		    	  if (_Hp_Num != null)
		    		  _Hp_Num.removeSelf();
		  		
		    	  _Hp_Num = CCLabel.makeLabel("x" + Hp_Num, _Cookie_Font, 20);
		    	  _Hp_Num.setColor(ccColor3B.ccWHITE);
		    	  _Hp_Num.setString("x" +Hp_Num);
		    	  _Hp_Num.setPosition(CGPoint.ccp(_WB_Hp1.getContentSize().width
		  				+ 5 + _Hp_Num.getTexture().getWidth() / 2, _WinSize.height
		  				- _WB_Hp1.getContentSize().height / 2));
		  		addChild(_Hp_Num);// 将hp数添加到场景
		      }
		 }
		  
	}
	
	private void AddDate()
	{
		if(_Game_Date!=null)
		_Game_Date.removeSelf();
		_Game_Date = CCLabel.makeLabel(gameDate, _Cookie_Font, 40);
		_Game_Date.setColor(ccColor3B.ccWHITE);
		_Game_Date.setString(gameDate);
   	  _Game_Date.setPosition(CGPoint.ccp(_WinSize.width / 2, _WinSize.height
 				- _Game_Date.getContentSize().height / 2));
 		addChild(_Game_Date);// 将hp数添加到场景
		
	}
	/**
	 * 分数改变
	 * 
	 * @param score
	 */
	private void ChageScore(int score) {
		_Get_Score += score;
		
		//判断是否分数到达奖赏分数
			Long temp=Long.parseLong(String.valueOf(_Get_Score));
			if(temp>=Award_Score.get(0))
			{
				Award_Score.remove(0);
				 long mi = 2 <<(Award_Score.size()+1);
				Award_Score.add(mi*10000);
				Wudi_Num++;
				AddWudi();
				AddWudiJia1();
//				_Wudi_Jia1.removeSelf();
			}
		
		AddScore();

	}
	private void AddScore()
	{
		if (_ScoreLabel != null)
			_ScoreLabel.removeSelf();
		
		_ScoreLabel = CCLabel.makeLabel(String.valueOf( _Get_Score),  _Cookie_Font, 40);
		_ScoreLabel.setColor(ccColor3B.ccWHITE);
		_ScoreLabel.setString(String.valueOf( _Get_Score));
		_ScoreLabel.setPosition(CGPoint.ccp(_WinSize.width- _ScoreLabel.getTexture().getWidth() , _WinSize.height
				- _ScoreLabel.getContentSize().height / 2));
		addChild(_ScoreLabel);// 将分数添加到场景
			
	}
	private void AddWudi()
	{
		// TODO Auto-generated method stub
				if (_Wudi_Num != null)
					_Wudi_Num.removeSelf();
				if (_Wudi != null)
					_Wudi.removeSelf();
				if (Wudi_Num >= 0) {
					_Wudi = CCSprite.sprite(_Wudi_Path);
					_Wudi.setPosition(CGPoint.make(
							_Wudi.getContentSize().width / 2 + 1,
							_Wudi.getContentSize().height / 2 + 1));
					addChild(_Wudi);

					_Wudi_Num = CCLabel.makeLabel(String.valueOf(Wudi_Num), _Cookie_Font,
							20);
					_Wudi_Num.setColor(ccColor3B.ccWHITE);
					_Wudi_Num.setPosition(CGPoint.ccp(
							_Wudi.getContentSize().width + 1
									+ _Wudi_Num.getContentSize().width / 2,
									_Wudi.getContentSize().height / 4));
					addChild(_Wudi_Num);// 将分数添加到场景
				}

	}
	
	/*
	 *添加水量
	 */
	 
	private void AddCapa()
	{
		if (_Capacity != null)
			_Capacity.removeSelf();
		
		_Capacity = CCLabel.makeLabel(String.valueOf( Capa),  _Cookie_Font, 40);
		_Capacity.setColor(ccColor3B.ccWHITE);
		_Capacity.setString(String.valueOf( Capa));
		_Capacity.setPosition(CGPoint.ccp(_WinSize.width/2, _Capacity.getContentSize().height / 2));
		addChild(_Capacity);// 将分数添加到场景
	}
//	private void AddWaterBall()
//	{
//		
//	}
	private void AddBomb()
	{
		// TODO Auto-generated method stub
		if (_Bomb_Num != null)
			_Bomb_Num.removeSelf();
		if (_Bomb != null)
			_Bomb.removeSelf();
		if (Bomb_Num >=0) {
			_Bomb = CCSprite.sprite(_Bomb_Path);
			_Bomb.setPosition(CGPoint.make(_WinSize.width-_Bomb.getContentSize().width - 1,
					_Bomb.getContentSize().height / 2 + 1));
			addChild(_Bomb);

			_Bomb_Num = CCLabel.makeLabel(String.valueOf(Bomb_Num), _Cookie_Font,
					20);
			_Bomb_Num.setColor(ccColor3B.ccWHITE);
			_Bomb_Num.setPosition(CGPoint.ccp(_WinSize.width - _Bomb_Num.getContentSize().width,
							_Bomb.getContentSize().height /4));
			addChild(_Bomb_Num);// 将分数添加到场景
		}
	}
   private void AddDrip()
   {
	   setSeason();//跟新下季节
	   Random rand = new Random();
//	   int randomValue=0;
		int randomValue = rand.nextInt(30);//0-29的随机数
		WB_Sprite wb_drip=new WB_Sprite();
		wb_drip.setClicked_Or(false);
		if(randomValue==0&&(Season==2||Season==3))
		{
			wb_drip.setLift(_Small_life);
			wb_drip.setMax_Life(_Small_life);
			wb_drip.setCCSprite(_BigDrip_Path);
			DripDown(wb_drip, 0);
		}
		//冬季降雨
		if(randomValue>0&&randomValue<=4&&Season==4)
		{
			wb_drip.setLift(_Small_life);
			wb_drip.setMax_Life(_Small_life);
			wb_drip.setCCSprite(_SmallDrip_Path);
			DripDown(wb_drip, 1);
		}
		//春季降雨
		if(randomValue>=5&&Season==1)
		{
			wb_drip.setLift(_Small_life);
			wb_drip.setMax_Life(_Small_life);
			wb_drip.setCCSprite(_SmallDrip_Path);
			DripDown(wb_drip, 1);
		}
		//夏季降雨
		if(randomValue>=10&&Season==2)
		{
			wb_drip.setLift(_Small_life);
			wb_drip.setMax_Life(_Small_life);
			wb_drip.setCCSprite(_SmallDrip_Path);
			DripDown(wb_drip, 1);
		}
		//秋季降雨
		if(randomValue>=25&&Season==3)
		{
			wb_drip.setLift(_Small_life);
			wb_drip.setMax_Life(_Small_life);
			wb_drip.setCCSprite(_SmallDrip_Path);
			DripDown(wb_drip, 1);
		}
//		_AllDrip.add(wb_drip);
   }
   private void AddPlay()
   {
	   _WB_Play = CCSprite.sprite(_Play_Path);
		_WB_Play.setPosition(_WinSize.width / 2,
				_WB_Play.getContentSize().height +_Capacity.getContentSize().height/ 2);
		addChild(_WB_Play);
   }
   private void AddTip(String s)
	{
	   if (_Tip != null)
			_Tip.removeSelf();
		
		_Tip = CCLabel.makeLabel(s,  _Cookie_Font, 30);
		_Tip.setColor(ccColor3B.ccWHITE);
		_Tip.setString(s);
		_Tip.setPosition(CGPoint.ccp(_WinSize.width/2, _WinSize.height / 2));
		addChild(_Tip);// 将分数添加到场景
	}
   private void setSeason()
   {
	   int month=Integer.parseInt(gameDate)/100;
	  
	   switch(month)
	   {
	   case 3:
	   case 4:
	   case 5:
		   Season=1;
		   break;
	   case 6:
	   case 7:
	   case 8:
		   Season=2;
		   break;
	   case 9:
	   case 10:
	   case 11:
		   Season=3;
		   break;
	   case 12:
	   case 1:
	   case 2:
		   Season=4;
		   break;
		   
	   }
   }
   /**
	 * 添加水滴落下
	 */
  private void DripDown(WB_Sprite wb_drip,int tag)
  {
	  Random rand = new Random();
		int minX = (int) (wb_drip.getCCSprite().getContentSize().width / 2.0f);//最小横坐标
		int maxX = (int) (_WinSize.width - wb_drip.getCCSprite()
				.getContentSize().width / 2.0f);//最大横坐标
		int rangeX = maxX - minX;
		int actualX = rand.nextInt(rangeX) + minX;
		int minDuration = DripDown_Time - 4;//FoeDown_Time为下降速度 初始为8
		int maxDuration = DripDown_Time;
		if (_Get_Score > 1000000) {
			maxDuration = DripDown_Time - 2;
			minDuration = DripDown_Time - 5;
		}
		int rangeDuration = maxDuration - minDuration;
		int actualDuration = rand.nextInt(rangeDuration) + minDuration;
		if (actualDuration < 0) {
			actualDuration = rand.nextInt(2) + 2;
		}

		wb_drip.setInitX(actualX);
		wb_drip.setInitDuration(actualDuration);
		wb_drip.setInitY(wb_drip.getCCSprite().getContentSize().height
				/ 2.0f + _WinSize.height);

		wb_drip.getCCSprite().setPosition(
				actualX,
				wb_drip.getCCSprite().getContentSize().height / 2.0f
						+ _WinSize.height);
		addChild(wb_drip.getCCSprite());

		    if(tag==0)
		    {
		    	wb_drip.getCCSprite().setTag(0);
				_BigDrip.add(wb_drip);
		    }
			if (tag == 1) {
				wb_drip.getCCSprite().setTag(1);
				_Drip.add(wb_drip);
			} 
			
		setSeason();
        setDownDistance(wb_drip);
        setDownDuration(actualDuration);
		CCFiniteTimeAction fs_timeAction = CCMoveTo.action(_Down_Duration,CGPoint.ccp(actualX, _Down_Distance));// 时间内移动
		System.out.println("Down dis:"+ _Down_Distance);
		CCCallFuncN fs_Over = null; //结束
		if (tag == 1)
			fs_Over = CCCallFuncN.action(this, "Drip_Over");
          if(tag==0)
        	  fs_Over = CCCallFuncN.action(this, "BigDrip_Over"); 
		CCSequence fs_actions = CCSequence.actions(fs_timeAction, fs_Over);//运动开始到结束
		wb_drip.getCCSprite().runAction(fs_actions);
  }
  /**
	 * 给予时间和位置，添加敌机落下
	 */
	private void Down(WB_Sprite wb_drip, int i, float y) {

		wb_drip.getCCSprite().setPosition(wb_drip.getInitX(), y);
		addChild(wb_drip.getCCSprite());

		
			if (i == 1) {
				wb_drip.getCCSprite().setTag(1);
			} 
			_Drip.add(wb_drip);
		

		CCFiniteTimeAction fs_timeAction = CCMoveTo.action(wb_drip
				.getInitDuration(), CGPoint.ccp(wb_drip.getInitX(),
				-(wb_drip.getCCSprite().getContentSize().height / 2)));// 时间内移动
		CCCallFuncN fs_Over = null;
		if (i == 1)
			fs_Over = CCCallFuncN.action(this, "Drip_Over");

		CCSequence fs_actions = CCSequence.actions(fs_timeAction, fs_Over);
		wb_drip.getCCSprite().runAction(fs_actions);
	}
	
	/**
	 * 添加结束清除
	 */
	public void Drip_Over(Object sender) {
		CCSprite drip_over = (CCSprite) sender;
		drip_over.removeSelf();

		for (int i = 0; i < _Drip.size(); i++) {
			WB_Sprite drip = _Drip.get(i);
			if (drip.getCCSprite() == drip_over) {
				_Drip.remove(i);
				break;
			}
		}
	
	}
	
	public void BigDrip_Over(Object sender) {
		CCSprite drip_over = (CCSprite) sender;
		drip_over.removeSelf();
		for(int i=0;i<_BigDrip.size();i++)
		{
			WB_Sprite drip = _BigDrip.get(i);
			if (drip.getCCSprite() == drip_over) {
				_BigDrip.remove(i);
				break;
			}
		}
	}
	private void setDownDistance(WB_Sprite wb_drip)
	{
		
		switch(Season)
		{
		case 1:
			_Down_Distance=-(wb_drip.getCCSprite().getContentSize().height / 2);
			break;
		case 2:
			_Down_Distance=_WinSize.getHeight()*2/3;
		    break;
		case 3:
			_Down_Distance=_WinSize.getHeight()/3;
			break;
		case 4:
			_Down_Distance=_WinSize.getHeight()/5;
			break;
		}
	}
	private void setDownDuration(float i)
	{
		
		switch(Season)
		{
		case 1:
			_Down_Duration=i;
			break;
		case 2:
			_Down_Duration=i/5;
		    break;
		case 3:
			_Down_Duration=i*2/3;
			break;
		case 4:
			_Down_Duration=i*4/5;
			break;
		}
		
	}
   private boolean CirCleinterRect(float arcR ,float arcOx ,float arcOy ,float rectX,float rectY,float rectW,float rectH)
   {
	   if(((rectX-arcOx) * (rectX-arcOx) + (rectY-arcOy) * (rectY-arcOy)) <= arcR * arcR)
		   return true;
	   if(((rectX+rectW-arcOx) * (rectX+rectW-arcOx) + (rectY-arcOy) * (rectY-arcOy)) <= arcR * arcR)
	       return true;
	   if(((rectX-arcOx) * (rectX-arcOx) + (rectY+rectH-arcOy) * (rectY+rectH-arcOy)) <= arcR * arcR)
	       	return true;
	   if(((rectX+rectW-arcOx) * (rectX+rectW-arcOx) + (rectY+rectH-arcOy) * (rectY+rectH-arcOy)) <= arcR * arcR)
	        return true;
	        //分别判断矩形4个顶点与圆心的距离是否<=圆半径；如果<=，说明碰撞成功
	       
	       
	       float minDisX = 0;
	       	if(arcOy >= rectY && arcOy <= rectY + rectH){
	       
	       		if(arcOx < rectX)
	        	minDisX = rectX - arcOx;
	       	    else if(arcOx > rectX + rectW)
	        	minDisX = arcOx - rectX - rectW;
	            else 
	               return true;
	       		
	          if(minDisX <= arcR)
	      	  return true;
	       	}//判断当圆心的Y坐标进入矩形内时X的位置，如果X在(rectX-arcR)到(rectX+rectW+arcR)这个范围内，则碰撞成功
	        
	       	
	        float minDisY = 0;
	       if(arcOx >= rectX && arcOx <= rectX + rectW){
	         if(arcOy < rectY)
	           minDisY = rectY - arcOy;
	         else if(arcOy > rectY + rectH)
	       	   minDisY = arcOy - rectY - rectH;
	         else
	         	return true;
	       	if(minDisY <= arcR)
	        	return true;
	        }//判断当圆心的X坐标进入矩形内时Y的位置，如果X在(rectY-arcR)到(rectY+rectH+arcR)这个范围内，则碰撞成功
	        	
	       return false;
   }
   public void Detection(float t)
   {
	   CGRect Rect_Ball = _WB_Play.getBoundingBox();
	   
		CGRect Rect_Ball2 = CGRect.make(Rect_Ball.origin.x,
				Rect_Ball.origin.y, Rect_Ball.size.width, Rect_Ball.size.height);
		
		//没有水滴下落也会增加non_drip的值
		
			non_drip++;
//		System.out.println("ballx:"+Rect_Ball.origin.x);
//		System.out.println("ballw:"+Rect_Ball.size.width);
//		System.out.println("ballh:"+Rect_Ball.size.height);
//		
//		System.out.println("bally:"+Rect_Ball.origin.y);
		for (int j = 0; j < _Drip.size(); j++) {
			WB_Sprite drip = _Drip.get(j);
			CGRect Rect2 = drip.getCCSprite().getBoundingBox();
//			System.out.println("x:"+drip.getCCSprite().getPosition().x);
//			System.out.println(_WB_Play.getPosition().x);
//			System.out.println("y:"+drip.getCCSprite().getPosition().y);
//			System.out.println(_WB_Play.getPosition().y);
			
			if (CirCleinterRect(Rect_Ball.size.width/2,Rect_Ball.origin.x+Rect_Ball.size.width/2,Rect_Ball.origin.y+Rect_Ball.size.height/2,
					Rect2.origin.x,Rect2.origin.y,Rect2.size.width,Rect2.size.height)) {
//			if (CGRect.intersects(Rect2, Rect_Ball2)) {
//				System.out.println("x:"+drip.getCCSprite().getPosition().x);
//				System.out.println(_WB_Play.getPosition().x);
//				System.out.println("y:"+drip.getCCSprite().getPosition().y);
//				System.out.println(_WB_Play.getPosition().y);
				non_drip=0;
//				AddSpriteAnimal(drip.getCCSprite().getPosition(),
//						_BigFoe_Sequence_Path, 164, 245, 6);
				drip.getCCSprite().removeSelf();
				_Drip.remove(j);
				ChageScore(100);
				ChangeCapa(1);
//				AddPlaySpriteAnimal(_WB_Play.getPosition(),
//						_Play_Sequence_Path, 99, 123, 4);
			
			}
			
		}
		
		//检测大水滴
		for (int j = 0; j < _BigDrip.size(); j++) {
			WB_Sprite drip = _BigDrip.get(j);
			CGRect Rect2 = drip.getCCSprite().getBoundingBox();
               if (CirCleinterRect(Rect_Ball.size.width/2,Rect_Ball.origin.x+Rect_Ball.size.width/2,Rect_Ball.origin.y+Rect_Ball.size.height/2,
					Rect2.origin.x,Rect2.origin.y,Rect2.size.width,Rect2.size.height)) {
                 non_drip=0;
                 drip.getCCSprite().removeSelf();
				_BigDrip.remove(j);
				ChageScore(1000);
				ChangeCapa(10);
                  }
			
		}
		
		
//		System.out.println("non_drip:"+non_drip);
		//判断惩罚
		if(_Recv_Punish20)
		{
			
			if(punish20_count==0)
			AddTip("3s内禁止移动");
//			if(_Ban20==null)
//            {
//				_Ban20=CCSprite.sprite("images/banall.png");
//				_Ban20.setPosition(_WB_Play.getPosition().x, _WB_Play.getPosition().y);
//				addChild(_Ban20);
//            }
				
			punish20_count++;
			if(punish20_count==50)
			   AddTip("3s");
			if(punish20_count==150)
				AddTip("2s");
			if(punish20_count==250)
				AddTip("1s");
			if(punish20_count>=350)
			{
				_Recv_Punish20=false;
//				punish20_count=0;
//				_Ban20.removeSelf();
//				_Ban20=null;
				_Tip.removeSelf();
				
			}
			
		}
		if(_Recv_Punish4)
		{
			if(punish4_count==0)
			AddTip("3s内禁止上下移动");
//			if(_Ban4==null)
//            {
//				_Ban4=CCSprite.sprite("images/banall.png");
//				_Ban4.setPosition(_WB_Play.getPosition().x, _WB_Play.getPosition().y);
//				addChild(_Ban4);
//            }
//				
			punish4_count++;
			if(punish4_count==50)
			{  AddTip("3s");
			
			
			}
			if(punish4_count==150)
				AddTip("2s");
			if(punish4_count==250)
				AddTip("1s");
			if(punish4_count>=350)
			{
				_Recv_Punish4=false;
//				punish20_count=0;
				
				_Tip.removeSelf();
				
			}
//			_Ban4.removeSelf();
//			_Ban4=null;
		}
		
		if(usewudi)
		{
			if(wudi_count==0)
				AddTip("3s无敌");

			      wudi_count++;
				if(wudi_count==50)
				{  AddTip("3s");
				
				
				}
				if(wudi_count==150)
					AddTip("2s");
				if(wudi_count==250)
					AddTip("1s");
				if(wudi_count>=350)
				{
					usewudi=false;
                   _Tip.removeSelf();
                   wudi_count=0;
				}
		}
	
   }
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		// TODO Auto-generated method stub
		CGPoint Location = CCDirector.sharedDirector().convertToGL(
				CGPoint.ccp(event.getX(), event.getY()));// 获取点击位置
		
		//获取到哪个道具
		CCSprite Prop=null;
	    CGRect Rect_Inc=null;CGRect Rect_Dec=null;CGRect Rect_Wudi=null;CGRect Rect_Bomb=null;
		if(_Inc_Prop.size()>=1)
		{
			System.out.println(_Inc_Prop.size()+",");
			Prop=_Inc_Prop.get(0);
			 Rect_Inc=Prop.getBoundingBox();
		}
		if(_Dec_Prop.size()>=1)
		{
			System.out.println(_Dec_Prop.size()+",");
			Prop=_Dec_Prop.get(0);
			 Rect_Dec=Prop.getBoundingBox();
		}
		if(_Wudi_Prop.size()>=1)
			 {
			System.out.println(_Wudi_Prop.size()+",");
			 Prop=_Wudi_Prop.get(0);
			 Rect_Wudi=Prop.getBoundingBox();
			 }
		if(_Bomb_Prop.size()>=1)
			 {
			System.out.println(_Bomb_Prop.size()+",");
			Prop=_Bomb_Prop.get(0);
			 Rect_Bomb=Prop.getBoundingBox();
			 }
		
		CGRect Rect = _WB_Play.getBoundingBox();
		CGRect Rect2 = _WB_Pause.getBoundingBox();
		
		CGRect Rect3 = null;CGRect Rect4 = null;
		if (_Bomb != null)
			Rect3 = _Bomb.getBoundingBox();
		if(_Wudi!=null)
			Rect4=_Wudi.getBoundingBox();
		if (CGRect.containsPoint(Rect, Location) && _Pause_OR != 1) {// 判断是否点击到控制飞机
			_Can_Move = true;
		} else {
			_Can_Move = false;
			if (CGRect.containsPoint(Rect2, Location)) {
				_Pause_OR = -_Pause_OR;
				if (_Pause_OR == 1) {
					CCDirector.sharedDirector().pause();
				} else {
					CCDirector.sharedDirector().resume();
				}
			}
			
			if (_Pause_OR != 1) {
				//点击了加水量道具
				if(Rect_Inc!=null&&CGRect.containsPoint(Rect_Inc, Location))
						{
					       _Inc_Prop.remove(Prop);
					       _IncDec_Prop.remove(Prop);
					       Prop.removeSelf();
					       Capa+=5;
//					       origin_capa=Capa;//水量变化了 origin_capa必须同时变化
					       AddCapa();
					       AddCapaUpDown("+5");
						}
				if(Rect_Dec!=null&&CGRect.containsPoint(Rect_Dec, Location))
				{
					_Dec_Prop.remove(Prop);
					 _IncDec_Prop.remove(Prop);
				       Prop.removeSelf();
			       Capa-=5;if(Capa<0)Capa=0;
//			       origin_capa=Capa;//水量变化了 origin_capa必须同时变化
			       AddCapa();
			       AddCapaUpDown("-5");
				}
				if(Rect_Wudi!=null&&CGRect.containsPoint(Rect_Wudi, Location))
				{
					_Wudi_Prop.remove(Prop);
					 _WudiBomb_Prop.remove(Prop);
				       Prop.removeSelf();
			       Wudi_Num++;
			       AddWudi();
			       AddWudiJia1();
				}
				if(Rect_Bomb!=null&&CGRect.containsPoint(Rect_Bomb, Location))
				{
					_Bomb_Prop.remove(Prop);
					 _WudiBomb_Prop.remove(Prop);
				       Prop.removeSelf();
			       Bomb_Num++;
			       AddBomb();
			       AddBombJia1();
				}
				//使用了道具
				if (_Bomb != null && CGRect.containsPoint(Rect3, Location)
						&& Bomb_Num > 0) {
					Bomb_Num--;
					AddBomb();
					AddPropTip("全屏秒击");
					ReMoveAll();
				}
				
				if (_Wudi != null && CGRect.containsPoint(Rect4, Location)
						&& Wudi_Num > 0) {
					Wudi_Num--;
					AddWudi();
					
					usewudi=true;
				}
			}
		}
//        if(_Pause_OR!=1)
//        {
//        	if(Rect4!=null)
//        	{
//        	if (CGRect.containsPoint(Rect4, Location)) {
//        		System.out.println(Bomb_Num);
//        		Bomb_Num++;
//        		AddBomb();
//        	}
//        	}
//        }
		return super.ccTouchesBegan(event);
	}

	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.ccTouchesEnded(event);
	}

	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		// TODO Auto-generated method stub
		if (_Can_Move) {
			_Touch_Location = CCDirector.sharedDirector().convertToGL(
					CGPoint.ccp(event.getX(), event.getY()));
			if(_Recv_Punish20)
			{
				_WB_Play.setPosition(_WB_Play.getPosition().x,_WB_Play.getPosition().y);
			}
			if(_Recv_Punish4)
			{
				_WB_Play.setPosition(_Touch_Location.x,_WB_Play.getPosition().y);
			}
			else if(!_Recv_Punish20&&!_Recv_Punish4)
			_WB_Play.setPosition(_Touch_Location.x, _Touch_Location.y);
			
		}
		return super.ccTouchesMoved(event);
	}
	private void AddWudiJia1(){
		if (_Wudi_Jia1 != null)
			_Wudi_Jia1.removeSelf();
		
		_Wudi_Jia1 = CCLabel.makeLabel("+1",  _Cookie_Font, 30);
		_Wudi_Jia1.setColor(ccColor3B.ccWHITE);
		_Wudi_Jia1.setString("+1");
		_Wudi_Jia1.setPosition(CGPoint.ccp(_Wudi.getContentSize().width/2+3, _Wudi.getContentSize().height ));
		addChild(_Wudi_Jia1);// 将分数添加到场景
	}
	private void AddBombJia1(){
		if (_Bomb_Jia1 != null)
			_Bomb_Jia1.removeSelf();
		
		_Bomb_Jia1 = CCLabel.makeLabel("+1",  _Cookie_Font, 30);
		_Bomb_Jia1.setColor(ccColor3B.ccWHITE);
		_Bomb_Jia1.setString("+1");
		_Bomb_Jia1.setPosition(CGPoint.ccp(_WinSize.width-_Bomb.getContentSize().width-3, _Bomb.getContentSize().height ));
		addChild(_Bomb_Jia1);// 将分数添加到场景
	}
	private void AddCapaUpDown(String s)
	{
		if (_Capa_UpDown != null)
			_Capa_UpDown.removeSelf();
		
		_Capa_UpDown = CCLabel.makeLabel(s,  _Cookie_Font, 30);
		_Capa_UpDown.setColor(ccColor3B.ccWHITE);
		_Capa_UpDown.setString(s);
		_Capa_UpDown.setPosition(CGPoint.ccp(_WB_Play.getPosition().x,_WB_Play.getPosition().y ));
		addChild(_Capa_UpDown);// 将分数添加到场景
	}
	private void ReMoveAll(){
		List<WB_Sprite> _DripAll=_Drip;
		List<WB_Sprite> _BigDripAll=_BigDrip;
		for(int i=0;i<_DripAll.size();i++)
		{
			WB_Sprite drip=_DripAll.get(i);
			ChageScore(100);
			Capa+=1;
//			origin_capa=Capa;
			AddScore();
			AddCapa();
			drip.getCCSprite().removeSelf();
		}
		_Drip.removeAll(_DripAll);
		
		for(int i=0;i<_BigDripAll.size();i++)
		{
			WB_Sprite drip=_BigDripAll.get(i);
			ChageScore(1000);
			Capa+=10;
//			origin_capa=Capa;
			AddScore();
			AddCapa();
			drip.getCCSprite().removeSelf();
		}
		_BigDrip.removeAll(_BigDripAll);
		
		
	}
	private void AddPropTip(String s )
	{

		   if (_PropTip != null)
				_PropTip.removeSelf();
			
			_PropTip = CCLabel.makeLabel(s,  _Cookie_Font, 30);
			_PropTip.setColor(ccColor3B.ccWHITE);
			_PropTip.setString(s);
			_PropTip.setPosition(CGPoint.ccp(_WinSize.width/2, _WinSize.height / 2));
			addChild(_PropTip);// 将分数添加到场景
	}
	private void WinGame()
	{
		ReMoveAll();
		Intent mainScore = new Intent(
				CCDirector.sharedDirector().getActivity(),WB_Next.class);
		mainScore.putExtra("score", String.valueOf(_Get_Score));
		mainScore.putExtra("hp", String.valueOf(Hp_Num));
		mainScore.putExtra("capa", String.valueOf(Capa));
		mainScore.putExtra("wudi", String.valueOf(Wudi_Num));
		mainScore.putExtra("bomb", String.valueOf(Bomb_Num));
		mainScore.putExtra("place", "1");
		
		
		CCDirector.sharedDirector().getActivity().startActivity(mainScore);
		CCDirector.sharedDirector().getActivity().finish();
	}
	
}
