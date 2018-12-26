package gameEngine.com.Actor;


import gameEngine.com.management.BulletsManagement;
import gameEngine.com.management.GameManageMent;
import gameEngine.com.management.TanksManageMent;

import java.util.Random;

import com.battle.tanks90.GameLib;
import com.battle.tanks90.MainActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Paint.Style;

public class Tank
{
	public Point position;
	int speed;
	public static final int TANK_SPEED_NORMAL = 3;
	public static final int TANK_SPEED_FAST = 5;
	public Point size;
	int drection;
	Bitmap bitmap;
	Random myRandom;
	Point[] drectionset;
	Matrix mat;
	boolean isbitmap = false;
	int rotate = 0;
	public int mTypeTank;
	public boolean isdie = false;
	public int isbum = 0;
	int oninit = 10;
	public int amor;
	public int damage = 1;
	public int thoigianbatdong = 0;
	public int ID;// tank id
	// not importtance
	public static final int TANK_TYPE_NORMAL = 0;// tank id
	public static final int TANK_TYPE_FAST = 1;// tank id
	public static final int TANK_TYPE_STRONG = 2;// tank id
	public static final int TANK_TYPE_SPECICALL = 3;// tank id
	public int shot_count = 10 + GameManageMent.myRandom.nextInt(10);
	public int rotate_count = 20 + GameManageMent.myRandom.nextInt(20);

	public Tank(String path, int x, int y, int type)
	{
		speed = TANK_SPEED_NORMAL;
		mTypeTank = type;
		amor = 1;

		switch (mTypeTank) {
		case TANK_TYPE_NORMAL:
			speed = TANK_SPEED_FAST;
			break;
		case TANK_TYPE_FAST:
			amor = 2;
			break;
		case TANK_TYPE_STRONG:
			amor = 4;
			break;
		case TANK_TYPE_SPECICALL:
			amor = 1;
			speed = TANK_SPEED_FAST;
			break;
		default:
			break;
		}
		if (path != null)
		{
			isbitmap = true;
			bitmap = GameLib.loadImageFromAsset(path);
		}
		else
			isbitmap = false;

		position = new Point(x, y);

		size = new Point(32, 32);

		myRandom = new Random();
		drectionset = new Point[4];
		drectionset[0] = new Point(1, 0);
		drectionset[1] = new Point(0, 1);
		drectionset[2] = new Point(-1, 0);
		drectionset[3] = new Point(0, -1);
		drection = 3;
		ChangeDirecttion(1);
	}

	public void Shot()
	{
		GameManageMent.bullets.AddBullet(position.x + size.x / 4 + drectionset[drection].x * size.x / 2, position.y + size.y / 4 + drectionset[drection].y * size.x / 2, drection, Bullet.BULLET_SPEED_NORMAL, damage);
	}

	public void Draw(Canvas canvas)
	{
		mat = new Matrix();
		if (oninit > 0)
		{
			mat.postScale(size.x * (oninit % 2) * 1.0f / BulletsManagement.bum.getWidth(), size.x * (oninit % 2) * 2.0f / BulletsManagement.bum.getWidth());
			mat.postTranslate(position.x + size.x / 4, position.y);
			canvas.drawBitmap(TanksManageMent.start_bit, mat, null);
			oninit--;
		}
		else if (isbum > 0)
		{
			isdie = true;
			mat.postScale(size.x * 2.0f / BulletsManagement.bum.getWidth(), size.x * 2.0f / BulletsManagement.bum.getWidth());
			mat.postTranslate(position.x - size.x / 2, position.y - size.y / 2);
			canvas.drawBitmap(TanksManageMent.bum, mat, null);
			isbum--;
		}
		else
		{
			mat.postScale(size.x * 1.0f / TanksManageMent.bitmaps[mTypeTank].getWidth(), size.x * 1.0f / TanksManageMent.bitmaps[mTypeTank].getWidth());
			mat.postRotate(rotate, size.x / 2, size.y / 2);
			mat.postTranslate(position.x, position.y);
			canvas.drawBitmap(TanksManageMent.bitmaps[mTypeTank], mat, null);
		}
		if (MainActivity.PRINTLOG)
		{
			MainActivity.mainPaint.setStyle(Style.STROKE);
			canvas.drawRect(position.x, position.y, position.x + size.x, position.y + size.y, MainActivity.mainPaint);
		}
		// canvas.drawBitmap(TanksManageMent.bitmap,new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()),new Rect(position.x,position.y,position.x+size.x,position.y+size.y),null);
		// canvas.drawBitmap(BulletsManagement.bum, mat, null);
		// canvas.drawBitmap(bMapRotate,new Rect(0,0,bMapRotate.getWidth(),bMapRotate.getHeight()),new Rect(position.x,position.y,position.x+size.x,position.y+size.y),null);

	}

	public void Update()
	{
		if (oninit == 0)
		{
			if (thoigianbatdong > 0)
				thoigianbatdong--;
			else
			{
				shot_count--;
				rotate_count--;
				int temposX = position.x + (drectionset[drection].x * speed);
				int temposY = position.y + (drectionset[drection].y * speed);
				if (!GameManageMent.map.isTankConflicOtherTank(temposX, temposY, size.x, 1))
				{
					position.x += (drectionset[drection].x * speed);// ,(int)(drectionset[drection].y*speed));
					position.y += (drectionset[drection].y * speed);
				}
				if (shot_count == 0)
				{
					Shot();
					shot_count = 30 + GameManageMent.myRandom.nextInt(10);
				}
				if (rotate_count == 0)
				{
					ChangeDirecttion(-1);
					rotate_count = 20 + GameManageMent.myRandom.nextInt(20);
				}
			}
		}
	}

	public void ChangeDirecttion(int index)
	{
		Shot();
		shot_count = 30 + GameManageMent.myRandom.nextInt(10);

		if (index == drection)
			return;
		if (index == -1)
		{
			index = drection;
			while (index == drection)
				index = GameManageMent.myRandom.nextInt(4);
		}
		if (index - drection == 2 || index - drection == -2)
			rotate = rotate + 180;
		else if (index - drection == 1 || index - drection == -3)
			rotate = rotate + 90;
		else
			rotate = rotate + 270;

		rotate = rotate % 360;
		drection = index;

	}

	public boolean Predict_CalcConflictwithmap()
	{
		// if(drection <0 ||drection>3 )
		// Log.d("ddddddddddddddddddddd", " " + drection);
		return GameManageMent.map.Calc_Tank_Conflictwithmap(position.x + drectionset[drection].x * speed, position.y + drectionset[drection].y * speed, size.x);

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
}
