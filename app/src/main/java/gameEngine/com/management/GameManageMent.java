package gameEngine.com.management;



import gameEngine.com.Actor.MyTank;
import gameEngine.com.resolution.DEF;
import gameEngine.com.state.StateGameplay;

import java.util.Random;

import com.battle.tanks90.Event;
import com.battle.tanks90.GameInfo;
import com.battle.tanks90.GameLib;
import com.battle.tanks90.MainActivity;
import com.battle.tanks90.Map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameManageMent
{
	public static final int SUB_STATE_PLAY = 0;
	public static final int SUB_STATE_WIN = 1;
	public static final int SUB_STATE_LOSE = 2;
	public static int BOARD_MAP_OFFSET_X = DEF.BOARD_MAP_OFFSET_X;
	public static int BOARD_MAP_OFFSET_Y = DEF.BOARD_MAP_OFFSET_Y;
	public static int GAME_INFO_POS_X = DEF.GAME_INFO_TANK_NPC_POS_X;
	public static int GAME_INFO_POS_Y = DEF.GAME_INFO_TANK_NPC_POS_Y;
	public static Map map;
	public static TanksManageMent tanksManegeMent;
	public static MyTank mytank;
	public static BulletsManagement bullets;
	public static BulletsManagement mybullets;
	public static Random myRandom = new Random();
	Bitmap bitmapMapDraw = Bitmap.createBitmap(416, 416, Config.ARGB_8888);
	// Bitmap screen_menu = tanks.loadImageFromAsset("screen/image76.png");
	Canvas canvasMapDraw = new Canvas(bitmapMapDraw);

	public GameInfo gameinfo;
	public static int numEnemyTank = 20;
	int cout_new_tank = 10;
	public static int mSubState = 0; // 0 nomal; 1: win 2:lose
	public static Event events;
	public static boolean gamepause = false;

	// left button
	public static Point distance = new Point(0, 0);

	public static Bitmap bitmapDpadMove = GameLib.loadImageFromAsset("navigation/dpad_move.png");
	public static Bitmap bitmapDpadMovePoint = GameLib.loadImageFromAsset("navigation/dpad_move_point.png");
	// right Button
	public static Bitmap n3 = GameLib.loadImageFromAsset("navigation/n3.png");
	public static Bitmap n4 = GameLib.loadImageFromAsset("navigation/n4.png");
	static Point posLeftButton = new Point(100, GameLib.SCREEN_HEIGHT - 100);
	public static Point posRightButton = new Point(GameLib.SCREEN_WIDTH - 100, GameLib.SCREEN_HEIGHT - 100);

	public static Point windowsize = new Point(n3.getWidth(), n3.getHeight());

	public void NewGame(int imap)
	{

		gamepause = false;

	//	map.chosemap(imap);
		numEnemyTank = 20;
		mSubState = SUB_STATE_PLAY;
		mytank.mCountExplosion = 0;
		mytank.isdie = false;
		mytank.ResetLife();
		mytank.mLife = 2;
		tanksManegeMent.Reset();
		bullets.Reset();
		mybullets.Reset();
		mybullets.mytank = true;
		events = new Event();
	}

	public GameManageMent(int  mLevel)
	{
		map = new Map(mLevel);
		// tank = new Tank("tank/image83.png",0,0);
		bullets = new BulletsManagement();
		mybullets = new BulletsManagement();
		tanksManegeMent = new TanksManageMent();
		mytank = new MyTank();
		gameinfo = new GameInfo();

		NewGame(mLevel);
	}

	public void reStartGame(int mLocation, int mLevel) {
		gamepause = false;
		map.chosemap(mLevel);
		numEnemyTank = 20;
		mSubState = SUB_STATE_PLAY;
		mytank.mCountExplosion = 0;
		mytank.isdie = false;

		mytank.ResetLife();
		mytank.mLife = 2;
		tanksManegeMent.Reset();
		bullets.Reset();
		mybullets.Reset();
		mybullets.mytank = true;
		events = new Event();

	}

	public static void detroyGame() {

	}

	public static void SetVerticleByKey() {
		if (MainActivity.isKeyHold(KeyEvent.KEYCODE_DPAD_DOWN)) {
			GameManageMent.mytank.isMove = true;
			GameManageMent.mytank.ChangeDirecttion(1);
		} else if (MainActivity.isKeyHold(KeyEvent.KEYCODE_DPAD_UP)) {
			GameManageMent.mytank.isMove = true;
			GameManageMent.mytank.ChangeDirecttion(3);
		} else if (MainActivity.isKeyHold(KeyEvent.KEYCODE_DPAD_LEFT)) {
			GameManageMent.mytank.isMove = true;
			GameManageMent.mytank.ChangeDirecttion(2);
		} else if (MainActivity.isKeyHold(KeyEvent.KEYCODE_DPAD_RIGHT)) {
			GameManageMent.mytank.isMove = true;
			GameManageMent.mytank.ChangeDirecttion(0);
		}
	}

	public static void SetVerticle()
	{
		GameManageMent.mytank.isMove = true;
		if (distance.x > 0 && distance.y > 0)
			if (distance.x > distance.y)
				GameManageMent.mytank.ChangeDirecttion(0);
			else
				GameManageMent.mytank.ChangeDirecttion(1);
		else if (distance.x > 0 && distance.y < 0)
			if (distance.x > -distance.y)
				GameManageMent.mytank.ChangeDirecttion(0);
			else
				GameManageMent.mytank.ChangeDirecttion(3);
		else if (distance.x < 0 && distance.y < 0)
			if (-distance.x > -distance.y)
				GameManageMent.mytank.ChangeDirecttion(2);
			else
				GameManageMent.mytank.ChangeDirecttion(3);
		else if (distance.x < 0 && distance.y > 0)
			if (-distance.x > distance.y)
				GameManageMent.mytank.ChangeDirecttion(2);
			else
				GameManageMent.mytank.ChangeDirecttion(1);

	}

	public void Update(Canvas c)
	{
		events.Update();
		tanksManegeMent.Update();
		mybullets.Update();
		bullets.Update();
		mytank.Update();
		mybullets.CalcConflict2bullet(bullets);
		mybullets.CalcConflictEmery();
		bullets.CalcConflictMy_Bullets();
		if(Map.mBlockCount-- ==0){
			map.setBlockWall(false);
		}
		if (cout_new_tank > 0)
		{
			cout_new_tank--;
			if (cout_new_tank == 0 && numEnemyTank > 0)
			{
				cout_new_tank = myRandom.nextInt(20) + 15;
				if (tanksManegeMent.tanksEnemyList.size() < 4) {
					tanksManegeMent.AddTank(cout_new_tank % 3, cout_new_tank % 4);

				}
			}
		}
		if (tanksManegeMent.tanksEnemyList.size() == 0 && numEnemyTank == 0) {
			GameManageMent.mSubState = GameManageMent.SUB_STATE_WIN;
			MainActivity.sound.play(8, true);
			//GameManageMent.gamepause = true;
			StateGameplay.mTransattion=  StateGameplay.TRANSATION_MAX_COUNTER;
			StateGameplay.inCreateLevel();
		}
		// left button
		int pointMove = -1;
		GameManageMent.distance.x = 0;
		GameManageMent.distance.y = 0;
		GameManageMent.mytank.isMove = false;
		SetVerticleByKey();
		if (!GameManageMent.mytank.isMove)
			for (int i = 0; i < GameLib.m_currentNumPointer; i++) {
				if (GameLib.checkPointInCicle(GameLib.arraytouchPosX[i], GameLib.arraytouchPosY[i], posLeftButton.x, posLeftButton.y, GameLib.DPAD_LENGTH))
				{
					if (pointMove < 0 &&
							(GameLib.arraytouchState[i] == MotionEvent.ACTION_MOVE ||
									GameLib.arraytouchState[i] == MotionEvent.ACTION_POINTER_DOWN ||
							GameLib.arraytouchState[i] == MotionEvent.ACTION_DOWN))
					{
						pointMove = i;
						GameManageMent.distance.x = GameLib.arraytouchPosX[i] - GameManageMent.posLeftButton.x;
						GameManageMent.distance.y = GameLib.arraytouchPosY[i] - GameManageMent.posLeftButton.y;
						GameManageMent.SetVerticle();
					}
				}
			}

		// right button
		if (GameLib.isTouchDrapInRect(posRightButton.x - n3.getWidth() / 2, posRightButton.y - n3.getHeight() / 2, n3.getWidth(), n3.getHeight())) {
			GameManageMent.mytank.Shot();
		}

	}

	public void drawAll(Canvas c)
	{
		Rect rect1 = new Rect();
		rect1.set(0, 0, canvasMapDraw.getWidth(), canvasMapDraw.getHeight());
//		Log.d("ittus",canvasMapDraw.getWidth() + " " + canvasMapDraw.getHeight());
		bitmapMapDraw.eraseColor(Color.TRANSPARENT);
		int indexbackground =  (StateGameplay.mcurrentlevel/15);
		if(indexbackground >= 3)
			indexbackground = 2;
		StateGameplay.spriteGameBackGround.drawAFrame(canvasMapDraw,indexbackground, 0, 0);
		map.Draw(canvasMapDraw);

		tanksManegeMent.Draw(canvasMapDraw);
		mybullets.Draw(canvasMapDraw);
		bullets.Draw(canvasMapDraw);
		events.Draw(canvasMapDraw);
		mytank.Draw(canvasMapDraw);		
		map.DrawTree(canvasMapDraw);		
		c.drawBitmap(bitmapMapDraw, BOARD_MAP_OFFSET_X, BOARD_MAP_OFFSET_Y, null);

		gameinfo.Draw(c);

		// braw left button
		c.drawBitmap(bitmapDpadMove, posLeftButton.x - bitmapDpadMove.getWidth() / 2, posLeftButton.y - bitmapDpadMove.getHeight() / 2, null);
		c.drawBitmap(bitmapDpadMovePoint, posLeftButton.x - bitmapDpadMovePoint.getWidth() / 2 + distance.x, posLeftButton.y - bitmapDpadMovePoint.getHeight() / 2 + distance.y, null);
		// draw right button
		if (GameLib.isTouchDrapInRect(posRightButton.x - n3.getWidth() / 2, posRightButton.y - n3.getHeight() / 2, n3.getWidth(), n3.getHeight()))
			c.drawBitmap(n3, posRightButton.x - n3.getWidth() / 2, posRightButton.y - n3.getHeight() / 2, null);
		else
			c.drawBitmap(n4, posRightButton.x - n4.getWidth() / 2, posRightButton.y - n4.getHeight() / 2, null);

	}
}
