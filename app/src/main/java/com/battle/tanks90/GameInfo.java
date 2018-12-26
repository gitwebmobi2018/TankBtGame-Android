package com.battle.tanks90;

import gameEngine.com.management.GameManageMent;
import gameEngine.com.resolution.DEF;
import gameEngine.com.state.StateGameplay;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;

public class GameInfo
{
	Bitmap enermytankinfo;
	Bitmap mytankinfo;
	Bitmap levelinfo;
	Point position;
	float resize = 3;
	Matrix mat;

	public GameInfo()
	{
		mat = new Matrix();
		enermytankinfo = GameLib.loadImageFromAsset("gameinfo/image40.png");
		mytankinfo = GameLib.loadImageFromAsset("gameinfo/image54.png");
		levelinfo = GameLib.loadImageFromAsset("gameinfo/image53.png");
		position = new Point(DEF.GAME_INFO_TANK_NPC_POS_X, DEF.GAME_INFO_TANK_NPC_POS_Y);
	}

	public void Draw(Canvas canvas)
	{
		if (mat == null)
			mat = new Matrix();

		mat.reset();
		mat.postScale(resize, resize);

		for (int i = 0; i < 20; i++)
		{
			if (i == GameManageMent.numEnemyTank)
				break;
			int posy = i / 4;
			int posX = i - posy * 4;
			mat.reset();
			mat.postScale(resize, resize);
			mat.postTranslate(position.x + posX * 25, position.y + posy * 25);
			canvas.drawBitmap(enermytankinfo, mat, null);

		}
	
		mat.reset();
		mat.postScale(resize, resize);
		mat.postTranslate(DEF.GAME_INFO_MY_TANK_LIFE_POS_X, DEF.GAME_INFO_MY_TANK_LIFE_POS_Y);
		canvas.drawBitmap(mytankinfo, mat, null);
		MainActivity.fontsmall.drawString(MainActivity.mainCanvas, "" + GameManageMent.mytank.mLife,DEF.GAME_INFO_MY_TANK_LIFE_POS_X + 70, DEF.GAME_INFO_MY_TANK_LIFE_POS_Y + 10, BitmapFont.ALIGN_CENTER);
		mat.reset();
		mat.postScale(resize, resize);
		mat.postTranslate(DEF.GAME_INFO_CURRENT_LEVEL_POS_X, DEF.GAME_INFO_CURRENT_LEVEL_POS_Y);
		canvas.drawBitmap(levelinfo, mat, null);
		
		MainActivity.fontsmall.drawString(MainActivity.mainCanvas, "" + StateGameplay.mcurrentlevel, DEF.GAME_INFO_CURRENT_LEVEL_POS_X + 70, DEF.GAME_INFO_CURRENT_LEVEL_POS_Y + 10, BitmapFont.ALIGN_CENTER);

	}

}
