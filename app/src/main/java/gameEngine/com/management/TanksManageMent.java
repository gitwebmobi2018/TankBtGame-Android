package gameEngine.com.management;

import gameEngine.com.Actor.Tank;

import java.util.ArrayList;

import com.battle.tanks90.GameLib;
import com.battle.tanks90.MainActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class TanksManageMent
{
	public static Bitmap[] bitmaps;
	public static Bitmap bum;
	public static Bitmap start_bit;
	ArrayList<Tank> tanksEnemyList;

	// int index;
	public void Reset()
	{
		if (tanksEnemyList != null)
			tanksEnemyList.clear();
	}

	public TanksManageMent()
	{
		tanksEnemyList = new ArrayList<Tank>();
		bitmaps = new Bitmap[4];
		bitmaps[0] = GameLib.loadImageFromAsset("tank/normal_tank.png");
		bitmaps[1] = GameLib.loadImageFromAsset("tank/fast_tank.png");
		bitmaps[2] = GameLib.loadImageFromAsset("tank/strong_tank.png");
		bitmaps[3] = GameLib.loadImageFromAsset("tank/special_tank.png");
		bum = GameLib.loadImageFromAsset("bum/image17.png");
		start_bit = GameLib.loadImageFromAsset("effect/image63.png");
	}

	public void AddTank(int vitri, int xe)
	{
		if (GameManageMent.numEnemyTank > 0)
		{
			switch (vitri)
			{
			case 0:
				tanksEnemyList.add(new Tank(null, 0, 0, xe));

				break;
			case 1:
				tanksEnemyList.add(new Tank(null, 192, 0, xe));

				break;
			case 2:
				tanksEnemyList.add(new Tank(null, 380, 0, xe));
				break;				
			}
			GameManageMent.numEnemyTank--;
		}

	}

	public void SetAllTankinMap()
	{
		for (int i = 0; i < tanksEnemyList.size(); i++) {
			Tank tank = tanksEnemyList.get(i);
			GameManageMent.map.SetTanksMap(tank.position.x, tank.position.y, tank.size.x, i + 1);
		}

	}

	public void Update()
	{
		for (int i = 0; i < tanksEnemyList.size(); i++)
		{
			Tank tank = tanksEnemyList.get(i);
			if (tank.Predict_CalcConflictwithmap())
				tank.ChangeDirecttion(-1);
			else
			{
				tank.ID= i+1;//
				tank.Update();
			}
			
			

			if (tank.isdie)
			{
				if(tank.mTypeTank == Tank.TANK_TYPE_SPECICALL)
				GameManageMent.events.AddEventk(-1);//-1 will random
				tanksEnemyList.remove(i);
				MainActivity.sound.play(6, false);
				

			}
		}
		GameManageMent.map.ResetTanksMap();
		for (int i = 0; i < tanksEnemyList.size(); i++)
		GameManageMent.map.SetTanksMap(tanksEnemyList.get(i).position.x, tanksEnemyList.get(i).position.y, tanksEnemyList.get(i).size.x, i + 1);
	}

	public void Draw(Canvas canvas)
	{
		for (int i = 0; i < tanksEnemyList.size(); i++)
			tanksEnemyList.get(i).Draw(canvas);
	}

	public void explosionAll()
	{
		for (int i = 0; i < tanksEnemyList.size(); i++)
			tanksEnemyList.get(i).isbum = 3;
	}

	public void ideAll()
	{
		for (int i = 0; i < tanksEnemyList.size(); i++)
			tanksEnemyList.get(i).thoigianbatdong = 160;
	}
}
