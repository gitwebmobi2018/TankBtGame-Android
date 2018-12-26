package gameEngine.com.Actor;

import gameEngine.com.management.BulletsManagement;
import gameEngine.com.management.GameManageMent;
import gameEngine.com.management.TanksManageMent;
import gameEngine.com.state.StateGameplay;

import java.util.Random;

import com.battle.tanks90.GameLib;
import com.battle.tanks90.GameThread;
import com.battle.tanks90.MainActivity;
import com.battle.tanks90.Map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;

public class MyTank
{
	Point position;
	int speed;
	Point size;
	int mDirection;
	Bitmap[] bitmaps;
	Random myRandom;
	Point[] drectionset;
	Matrix matrix;
	int rotate = 0;
	public int mTankState = 0; // add start to increase state
	public boolean isMove = false;
	int mFrameCountInitEffect = 10;
	public Bitmap bitmapExplosion;
	public Bitmap start_bit;
	public Bitmap baove_bit;
	public int mCountExplosion = 0;
	public boolean isdie = false;
	public int mDefend = 20;
	public int mLife = 2;
	// public int amor=1;
	public int damage = 1;
	public long timeLastShot = 0;
	public long FPS_SHOOT = 450;

	// public int baove=0;
	public MyTank()
	{
		bitmaps = new Bitmap[3];
		bitmaps[0] = GameLib.loadImageFromAsset("tank/image7.png");
		bitmaps[1] = GameLib.loadImageFromAsset("tank/image101.png");
		bitmaps[2] = GameLib.loadImageFromAsset("tank/image107.png");
		bitmapExplosion = GameLib.loadImageFromAsset("bum/image17.png");
		start_bit = GameLib.loadImageFromAsset("effect/image63.png");
		baove_bit = GameLib.loadImageFromAsset("effect/image134.png");
		position = new Point(128, 384);
		speed = 8;
		size = new Point(32, 32);
		mDirection = 3;

		drectionset = new Point[4];
		drectionset[0] = new Point(1, 0);
		drectionset[1] = new Point(0, 1);
		drectionset[2] = new Point(-1, 0);
		drectionset[3] = new Point(0, -1);
		mLife = 2;
	}

	public void ResetLife()
	{
		mTankState = 0;
		mCountExplosion = 0;
		mFrameCountInitEffect = 10;
		mDefend = 20;
		isdie = false;
		position = new Point(128, 384);
	}

	public void ChangeDirecttion(int index)
	{
		if (index == mDirection)
			return;
		if (index == -1)
		{
			index = mDirection;
			while (index == mDirection)
				index = GameManageMent.myRandom.nextInt(4);
		}
		if (index - mDirection == 2 || index - mDirection == -2)
			rotate = rotate + 180;
		else if (index - mDirection == 1 || index - mDirection == -3)
			rotate = rotate + 90;
		else
			rotate = rotate + 270;

		rotate = rotate % 360;//
		correctPosition();
		mDirection = index;

	}
	public void correctPosition()
	{
		if(position.x % Map.CELL !=0){
			//need move
			position.x += (drectionset[mDirection].x * speed);
			
		}else if(position.y % Map.CELL !=0){
			//need move
			position.y += (drectionset[mDirection].y * speed);
			
		}
	}
	public void Update()
	{

		// if(baove > 0)
		// baove--;
		if (isMove)
		{
			int temposX = position.x + (drectionset[mDirection].x * speed);
			int temposY = position.y + (drectionset[mDirection].y * speed);
			if (Predict_CalcConflictwithmap() || (GameManageMent.map.isTankConflicOtherTank(temposX, temposY, size.x, -1)))
			{

			} else {
				position.x += (drectionset[mDirection].x * speed);
				position.y += (drectionset[mDirection].y * speed);

			}
		}
		else
		{
			correctPosition();
		}
			
		GameManageMent.map.SetTanksMap(position.x, position.y, size.x, -1); // MC is -1
		GainEvent();
	}

	public void Draw(Canvas canvas)
	{
		if (isdie && GameManageMent.mSubState != GameManageMent.SUB_STATE_LOSE)
		{
			if (mLife > 0)
			{
				isdie = false;
				mLife--;
				ResetLife();
			}
			else
			{
				GameManageMent.mSubState = GameManageMent.SUB_STATE_LOSE;
				// GameManageMent.gamepause = true;
				MainActivity.sound.play(8, true);
				StateGameplay.mTransattion = StateGameplay.TRANSATION_MAX_COUNTER;
				return;
			}
		}
		matrix = new Matrix();
		if (mFrameCountInitEffect > 0)
		{
			matrix.postScale(size.x * (mFrameCountInitEffect % 2) * 1.0f / BulletsManagement.bum.getWidth(), size.x * (mFrameCountInitEffect % 2) * 2.0f / BulletsManagement.bum.getWidth());
			matrix.postTranslate(position.x + size.x / 4, position.y);
			canvas.drawBitmap(start_bit, matrix, null);
			mFrameCountInitEffect--;
		}
		else if (mCountExplosion > 0)
		{
			MainActivity.sound.play(6, false);
			MainActivity.sound.play(8, false);
			isdie = true;
			mDirection = 3;
			rotate = 0;
			matrix.postScale(size.x * 2.0f / BulletsManagement.bum.getWidth(), size.x * 2.0f / BulletsManagement.bum.getWidth());
			matrix.postTranslate(position.x - size.x / 2, position.y - size.y / 2);
			canvas.drawBitmap(bitmapExplosion, matrix, null);
			mCountExplosion--;
		}
		else
		{

			matrix.postScale(size.x * 1.0f / TanksManageMent.bitmaps[mTankState].getWidth(), size.x * 1.0f / TanksManageMent.bitmaps[mTankState].getWidth());
			matrix.postRotate(rotate, size.x / 2, size.y / 2);
			matrix.postTranslate(position.x, position.y);
			canvas.drawBitmap(bitmaps[mTankState], matrix, null);
			if (mDefend > 0)
			{
				mDefend--;
				matrix.postRotate((mDefend % 2) * 90, size.x / 2, size.y / 2);
				canvas.drawBitmap(baove_bit, matrix, null);
			}
		}
	}

	public boolean CalcConflictwithmap()
	{

		if (GameManageMent.map.CalcConflictwithmap(position.x + 1, position.y + 1))
			return true;
		if (GameManageMent.map.CalcConflictwithmap(position.x + 1, position.y + size.y - 1))
			return true;
		if (GameManageMent.map.CalcConflictwithmap(position.x + size.x - 1, position.y + 1))
			return true;
		if (GameManageMent.map.CalcConflictwithmap(position.x + size.x - 1, position.y + size.y - 1))
			return true;
		return false;
	}

	public boolean Predict_CalcConflictwithmap()
	{
		// if(drection <0 ||drection>3 )
		// Log.d("ddddddddddddddddddddd", " " + drection);
		return GameManageMent.map.Calc_Tank_Conflictwithmap(position.x + drectionset[mDirection].x * speed, position.y + drectionset[mDirection].y * speed, size.x);

	}

	public void Shot()
	{

		if (GameThread.timeCurrent - timeLastShot > FPS_SHOOT)
		{
			timeLastShot = GameThread.timeCurrent;

			{
				GameManageMent.mybullets.AddBullet(position.x + size.x / 4, position.y + size.y / 4 , mDirection, Bullet.BULLET_SPEED_FAST, mTankState + 1);
				MainActivity.sound.play(4, false);
			}
		}

	}

	public void GainEvent()
	{
		for (int i = 0; i < GameManageMent.events.index; i++)
		{
			// Log.d("toan",""+position.x + " " +GameManageMent.events.events_position[i].x*16 );
			if (Math.abs(position.x - GameManageMent.events.events_position[i].x * 16) < 32)
				if (Math.abs(position.y - GameManageMent.events.events_position[i].y * 16) < 32)
				{
					switch (GameManageMent.events.events[i])
					{
					case 0:// mu~
						MainActivity.sound.play(3, false);
						mDefend = 200;
						break;
					case 1:// mang
						MainActivity.sound.play(1, false);
						mLife++;
						break;
					case 2:// sao
						MainActivity.sound.play(3, false);
						if (mTankState < 2)
							mTankState++;
						break;
					case 3:// min
							// App.sound.Play(3);
						GameManageMent.tanksManegeMent.explosionAll();
						break;

					case 4:// hen gio
							// App.sound.Play(3);
						GameManageMent.tanksManegeMent.ideAll();
						break;
					case 5:// xen
						Map.mBlockCount = Map.BLOCK_WALL_TIMER;
						GameManageMent.map.setBlockWall(true);
						MainActivity.sound.play(3, false);
						break;
					}
					GameManageMent.events.RemoveEvent(i);
					// i--;
					return;
				}
		}
	}
}
