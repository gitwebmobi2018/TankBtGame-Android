package gameEngine.com.Actor;

import gameEngine.com.management.BulletsManagement;
import gameEngine.com.management.GameManageMent;

import com.battle.tanks90.MainActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint.Style;
import android.graphics.Point;

public class Bullet
{

	public Point position;
	
	public static final int BULLET_SPEED_NORMAL = 8;
	public static final int BULLET_SPEED_FAST = 14;
	public int speed = 0;
	public Point size;
	public int drection;
	public Bitmap bitmap;
	public Point[] drectionset;
	public Matrix mat;
	Bitmap bum;
	public int isbum = 0;
	public int rotate = 0;
	public boolean isdie = false;
	public int damage;

	public Bullet(int x, int y, int dir, int _speed ,int damage2)
	{
		damage = damage2;
		speed = _speed;
		//bum = loadImageFromAsset("bum/image17.png");
		//bitmap = loadImageFromAsset(path);
		position = new Point(x, y);
		size = new Point(16, 16);
		drectionset = new Point[4];
		drectionset[0] = new Point(1, 0);
		drectionset[1] = new Point(0, 1);
		drectionset[2] = new Point(-1, 0);
		drectionset[3] = new Point(0, -1);
		drection = 3;
		if (dir != 3)
			ChangeDirecttion(dir);

	}

	public void ChangeDirecttion(int index)
	{
		mat = new Matrix();

		if (index - drection == 2 || index - drection == -2)
			rotate = 180;
		else if (index - drection == 1 || index - drection == -3)
			rotate = 90;
		else
			rotate = 270;

		//bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mat, true);
		drection = index;
	}

	public void Draw(Canvas canvas)
	{
		mat = new Matrix();
		if (isbum > 0)
		{
			isdie = true;
			mat.postScale(size.x * 2.0f / BulletsManagement.bum.getWidth(), size.x * 2.0f / BulletsManagement.bum.getWidth());
			mat.postTranslate(position.x - size.x / 2, position.y - size.y / 2);
			canvas.drawBitmap(BulletsManagement.bum, mat, null);
			isbum--;

		}
		else
		{
			mat.postScale(size.x * 1.0f / BulletsManagement.bitmap.getWidth(), size.x * 1.0f / BulletsManagement.bitmap.getWidth());
			mat.postRotate(rotate, size.x / 2, size.y / 2);
			mat.postTranslate(position.x, position.y);
			canvas.drawBitmap(BulletsManagement.bitmap, mat, null);
			if (MainActivity.PRINTLOG)
			{
				MainActivity.mainPaint.setStyle(Style.STROKE);
				canvas.drawRect(position.x, position.y, position.x + size.x, position.y + size.y, MainActivity.mainPaint);
			}
		}
	}

	public void UpdateMove(int tempspeed)
	{
		if (!isdie)
		{
			position.x += (drectionset[drection].x * tempspeed);
			position.y += (drectionset[drection].y * tempspeed);

		}
	}

	public boolean Predict_CalcConflictwithmap(int x, int y, int size)
	{
		return GameManageMent.map.CalcConflictwithmap(x,y,size);
	}
}
