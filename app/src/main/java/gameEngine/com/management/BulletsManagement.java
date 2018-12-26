package gameEngine.com.management;

import gameEngine.com.Actor.Bullet;

import java.util.ArrayList;

import com.battle.tanks90.GameLib;
import com.battle.tanks90.MainActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BulletsManagement
{
	public static Bitmap bitmap;
	public static Bitmap bum;
	ArrayList<Bullet> bullets;
	// int index;
	boolean mytank = false;

	public BulletsManagement()
	{
		if (bullets != null)
			bullets.clear();
		else
			bullets = new ArrayList<Bullet>();

		// index = 0;
		bitmap = GameLib.loadImageFromAsset("bullet/butllet1.png");
		bum = GameLib.loadImageFromAsset("bum/image17.png");
	}

	public void Reset()
	{
		if (bullets != null)
			bullets.clear();
	}

	public void AddBullet(int x, int y, int dir, int speed, int damage)
	{

		bullets.add(new Bullet(x, y, dir, speed, damage));
		// Log.d("aaaa"," " + index);
	}

	public void Update()
	{
		for (int i = 0; i < bullets.size(); i++)
		{
			Bullet tempBullet = bullets.get(i);

			int tempx = tempBullet.position.x + (tempBullet.drectionset[tempBullet.drection].x * tempBullet.speed) / 2;
			int tempy = tempBullet.position.y + (tempBullet.drectionset[tempBullet.drection].y * tempBullet.speed) / 2;
			int tempx1 = tempBullet.position.x + (tempBullet.drectionset[tempBullet.drection].x * tempBullet.speed);
			int tempy1 = tempBullet.position.y + (tempBullet.drectionset[tempBullet.drection].y * tempBullet.speed);
			if (tempBullet.isbum <= 0 && !tempBullet.isdie)
			{
				if (bullets.get(i).Predict_CalcConflictwithmap(tempx, tempy, tempBullet.size.x))
				{
					tempBullet.UpdateMove(tempBullet.speed / 2);
					GameManageMent.map.DestroyRect(tempx, tempy, tempBullet.size.x);
					tempBullet.isbum = 3;

				}
				else
				{
					if (bullets.get(i).Predict_CalcConflictwithmap(tempx1, tempy1, tempBullet.size.x)) {
						tempBullet.UpdateMove(tempBullet.speed);
						GameManageMent.map.DestroyRect(tempx1, tempy1, tempBullet.size.x);
						tempBullet.isbum = 3;
					}
					tempBullet.UpdateMove(tempBullet.speed);
				}
			}
			if (tempBullet.isdie)
			{
				bullets.remove(i);
				//if (mytank == true)
				//	MainActivity.sound.play(5, false);
				//TODO: - check this again after
				//continue;
			}
		}

	}

	public void Draw(Canvas canvas)
	{
		for (int i = 0; i < bullets.size(); i++)
			bullets.get(i).Draw(canvas);
	}

	public void CalcConflict2bullet(BulletsManagement butlletManagement2)
	{
		for (int i = 0; i < bullets.size(); i++)
			for (int j = 0; j < butlletManagement2.bullets.size(); j++)
			{
				if (Math.abs(bullets.get(i).position.x - butlletManagement2.bullets.get(j).position.x) < 10)
					if (Math.abs(bullets.get(i).position.y - butlletManagement2.bullets.get(j).position.y) < 10)
					{
						bullets.remove(i);
						butlletManagement2.bullets.remove(j);
						return;
					}
			}
	}

	public void CalcConflictEmery()
	{
		int temp;
		for (int i = 0; i < bullets.size(); i++)
		{
			temp = GameManageMent.map.CalcConfleci_Bulet_with_emery(bullets.get(i).position.x, bullets.get(i).position.y, bullets.get(i).size.x);
			if (temp > 0)
			{

				if (GameManageMent.tanksManegeMent.tanksEnemyList.get(temp - 1) != null)
				{
					if (bullets.get(i).damage >= GameManageMent.tanksManegeMent.tanksEnemyList.get(temp - 1).amor)
					{
						GameManageMent.tanksManegeMent.tanksEnemyList.get(temp - 1).isbum = 3;
						//	GameManageMent.map.ResetTanksMap();
						GameManageMent.tanksManegeMent.SetAllTankinMap();
						// khi tank xanh chet xuat hien event
						//if (GameManageMent.tanksManegeMent.tanksEnemyList.get(temp - 1).mTypeTank == 3)
						//	GameManageMent.events.AddEventk(-1);
					}
					else{
					MainActivity.sound.play(5, false);
						GameManageMent.tanksManegeMent.tanksEnemyList.get(temp - 1).amor -= bullets.get(i).damage;
					}bullets.remove(i);
				}
				i--;
				if (i < 0)
					return;
			}
		}

	}

	public void CalcConflictMy_Bullets()
	{

		for (int i = 0; i < bullets.size(); i++)
		{
			if (GameManageMent.map.CalcConfleci_Bulet_with_my_tank(bullets.get(i).position.x, bullets.get(i).position.y, bullets.get(i).size.x))
			{
				bullets.remove(i);
				if (GameManageMent.mytank.mDefend == 0)
					GameManageMent.mytank.mCountExplosion = 3;
			}
		}
	}
}
