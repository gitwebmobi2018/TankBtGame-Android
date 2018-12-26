package com.battle.tanks90;

import gameEngine.com.data.Map1;
import gameEngine.com.data.Map2;
import gameEngine.com.data.Map3;
import gameEngine.com.management.GameManageMent;
import gameEngine.com.state.StateGameplay;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Map implements Map1, Map2, Map3
{
	int[][] currentmap;
	public int[][] currenttank;
	Bitmap[] mape;
	public int ROWS = 26;
	public int COLS = 26;
	public static int CELL = 16;
	public int WIDTH = CELL * ROWS;
	public int HEIGHT = CELL * COLS;
	boolean isdrawegle = false;
	public static final int CONST_BRICK = 1;
	public static final int CONST_STONE = 2;
	public static final int CONST_TREE = 3;
	public static final int CONST_WARTER = 4;

	public static final int BLOCK_WALL_TIMER = 25 * 12;
	public static int mBlockCount = -1;

	public Map(int imap)
	{

		mape = new Bitmap[10];
		currenttank = new int[ROWS][COLS];
		mape[1] = GameLib.loadImageFromAsset("mape/image22.png");
		mape[2] = GameLib.loadImageFromAsset("mape/image25.png");
		mape[3] = GameLib.loadImageFromAsset("mape/image28.png");
		mape[4] = GameLib.loadImageFromAsset("mape/image30.png");
		mape[9] = GameLib.loadImageFromAsset("mape/image35.png");
		chosemap(imap);
	}

	// Postion of tank id in a map
	public void SetTanksMap(int x, int y, int size, int id)
	{
		if (x + 8 < 0 || x + size - 8 >= WIDTH)
			return;
		if (y + 8 < 0 || y + size - 8 >= HEIGHT)
			return;
		currenttank[(y + 8) / CELL][(x + 8) / CELL] = id;
		currenttank[(y + 8) / CELL][(x + size - 8) / CELL] = id;
		currenttank[(y + size - 8) / CELL][(x + 8) / CELL] = id;
		currenttank[(y + size - 8) / CELL][(x + size - 8) / CELL] = id;
	}

	public void ResetTanksMap()
	{
		for (int i = 0; i < COLS; i++)
			for (int j = 0; j < ROWS; j++)
				currenttank[i][j] = 0;
	}

	public void chosemap(int index)
	{
		mape[9] = GameLib.loadImageFromAsset("mape/image35.png");
		currentmap = new int[ROWS][COLS];
		Log.d("level : ", " " + index);
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < ROWS; j++)
			{
				switch (index)
				{
				case 0:
					currentmap[i][j] = gamemap0[i][j];
					break;
				case 1:
					currentmap[i][j] = gamemap1[i][j];
					break;
				case 2:
					currentmap[i][j] = gamemap2[i][j];
					break;
				case 3:
					currentmap[i][j] = gamemap3[i][j];
					break;
				case 4:
					currentmap[i][j] = gamemap4[i][j];
					break;
				case 5:
					currentmap[i][j] = gamemap5[i][j];
					break;
				case 6:
					currentmap[i][j] = gamemap6[i][j];
					break;
				case 7:
					currentmap[i][j] = gamemap7[i][j];
					break;
				case 8:
					currentmap[i][j] = gamemap8[i][j];
					break;
				case 9:
					currentmap[i][j] = gamemap9[i][j];
					break;
				case 10:
					currentmap[i][j] = gamemap10[i][j];
					break;
				case 11:
					currentmap[i][j] = gamemap11[i][j];
					break;
				case 12:
					currentmap[i][j] = gamemap12[i][j];
					break;
				case 13:
					currentmap[i][j] = gamemap13[i][j];
					break;
				case 14:
					currentmap[i][j] = gamemap14[i][j];
					break;
				case 15:
					currentmap[i][j] = gamemap15[i][j];
					break;
				case 16:
					currentmap[i][j] = gamemap16[i][j];
					break;
				case 17:
					currentmap[i][j] = gamemap17[i][j];
					break;
				case 18:
					currentmap[i][j] = gamemap18[i][j];
					break;
				case 19:
					currentmap[i][j] = gamemap19[i][j];
					break;
				case 20:
					currentmap[i][j] = gamemap20[i][j];
					break;
				case 21:
					currentmap[i][j] = gamemap21[i][j];
					break;
				case 22:
					currentmap[i][j] = gamemap22[i][j];
					break;
				case 23:
					currentmap[i][j] = gamemap23[i][j];
					break;
				case 24:
					currentmap[i][j] = gamemap24[i][j];
					break;
				case 25:
					currentmap[i][j] = gamemap25[i][j];
					break;
				case 26:
					currentmap[i][j] = gamemap26[i][j];
					break;
				case 27:
					currentmap[i][j] = gamemap27[i][j];
					break;
				case 28:
					currentmap[i][j] = gamemap28[i][j];
					break;
				case 29:
					currentmap[i][j] = gamemap29[i][j];
					break;
				case 30:
					currentmap[i][j] = gamemap30[i][j];
					break;
				case 31:
					currentmap[i][j] = gamemap31[i][j];
					break;
				case 32:
					currentmap[i][j] = gamemap32[i][j];
					break;
				case 33:
					currentmap[i][j] = gamemap33[i][j];
					break;
				case 34:
					currentmap[i][j] = gamemap34[i][j];
					break;
				case 35:
					currentmap[i][j] = gamemap35[i][j];
					break;
				case 36:
					currentmap[i][j] = gamemap36[i][j];
					break;
				case 37:
					currentmap[i][j] = gamemap37[i][j];
					break;
				case 38:
					currentmap[i][j] = gamemap38[i][j];
					break;
				case 39:
					currentmap[i][j] = gamemap39[i][j];
					break;
				case 40:
					currentmap[i][j] = gamemap40[i][j];
					break;
				case 41:
					currentmap[i][j] = gamemap41[i][j];
					break;
				case 42:
					currentmap[i][j] = gamemap42[i][j];
					break;
				case 43:
					currentmap[i][j] = gamemap43[i][j];
					break;
				case 44:
					currentmap[i][j] = gamemap44[i][j];
					break;

				}
			}
	}

	public void Draw(Canvas canvas)
	{
		isdrawegle = false;
		Rect temp = new Rect(0, 0, 8, 8);
		for (int i = 0; i < COLS; i++)
			for (int j = 0; j < ROWS; j++)
			{
				if (currentmap[i][j] == 0)
					continue;
				if (currentmap[i][j] == 9)
				{
					if (!isdrawegle)
					{

						isdrawegle = true;
						Rect temp2 = new Rect(0, 0, 16, 14);

						canvas.drawBitmap(mape[currentmap[i][j]], temp2, new Rect(16 * j, 16 * i, 16 * j + 32, 16 * i + 32), null);
					}
					continue;
				}
				if (currentmap[i][j] != CONST_TREE)
					canvas.drawBitmap(mape[currentmap[i][j]], temp, new Rect(16 * j, 16 * i, 16 * j + 16, 16 * i + 16), null);
			}

	}

	public void DrawTree(Canvas canvas)
	{
		isdrawegle = false;
		Rect temp = new Rect(0, 0, 8, 8);
		for (int i = 0; i < COLS; i++)
			for (int j = 0; j < ROWS; j++)
			{
				if (currentmap[i][j] == 0)
					continue;
				if (currentmap[i][j] == CONST_TREE)
					canvas.drawBitmap(mape[currentmap[i][j]], temp, new Rect(16 * j, 16 * i, 16 * j + 16, 16 * i + 16), null);
			}

	}

	public boolean CalcConflictwithmap(int x, int y)
	{

		if (x < 0 || x >= WIDTH)
			return true;
		if (y < 0 || y >= HEIGHT)
			return true;
		if (currentmap[y / CELL][x / CELL] == 3 || currentmap[y / CELL][x / CELL] == 4)
			return false;
		if (currentmap[y / CELL][x / CELL] == 9)
		{
			MainActivity.sound.play(8, false);
			GameManageMent.mSubState = GameManageMent.SUB_STATE_LOSE;
			// GameManageMent.gamepause = true;
			if (mape[9] == null)
				mape[9] = GameLib.loadImageFromAsset("mape/image37.png");
			StateGameplay.mTransattion = StateGameplay.TRANSATION_MAX_COUNTER;

		}
		if (currentmap[y / CELL][x / CELL] != 0)
			return true;
		return false;
	}

	public void setBlockWall(boolean isSetLock) {
		if (isSetLock)
		{

			currentmap[22][10] = currentmap[22][11] = currentmap[22][12] = currentmap[22][13] = currentmap[22][14] = currentmap[22][15] = 2;
			currentmap[23][10] = currentmap[23][11] = currentmap[23][12] = currentmap[23][13] = currentmap[23][14] = currentmap[23][15] = 2;
			currentmap[24][10] = currentmap[24][11] = currentmap[24][14] = currentmap[24][15] = 2;
			currentmap[25][10] = currentmap[25][11] = currentmap[25][14] = currentmap[25][15] = 2;

		}
		else
		{

			currentmap[22][10] = currentmap[22][11] = currentmap[22][12] = currentmap[22][13] = currentmap[22][14] = currentmap[22][15] = 1;
			currentmap[23][10] = currentmap[23][11] = currentmap[23][12] = currentmap[23][13] = currentmap[23][14] = currentmap[23][15] = 1;
			currentmap[24][10] = currentmap[24][11] = currentmap[24][14] = currentmap[24][15] = 1;
			currentmap[25][10] = currentmap[25][11] = currentmap[25][14] = currentmap[25][15] = 1;

		}
	}

	public void Destroy(int x, int y)
	{
		if (x < 0 || x >= COLS)
			return;
		if (y < 0 || y >= ROWS)
			return;
		currentmap[y][x] = 0;
	}

	public int CalcConflictwithmap_returninex(int x, int y)
	{
		if (x < 0 || x >= WIDTH)
			return -1;
		if (y < 0 || y >= HEIGHT)
			return -1;
		if (currentmap[y / CELL][x / CELL] == 3)
			return -1;
		if (currentmap[y / CELL][x / CELL] != 0)
			return currentmap[y / CELL][x / CELL];
		return -1;
	}

	public void DestroyRect(int x, int y, int size)
	{
		int typeMap = CalcConflictwithmap_returninex(x + 3, y + 3);
		if (typeMap == 1 || (typeMap == 2 && GameManageMent.mytank.mTankState == 2))
			Destroy((x + 3) / CELL, (y + 3) / CELL);

		typeMap = CalcConflictwithmap_returninex(x + 3, y + size - 3);
		if (typeMap == 1 || (typeMap == 2 && GameManageMent.mytank.mTankState == 2))
			Destroy((x + 3) / CELL, (y + size - 3) / CELL);

		typeMap = CalcConflictwithmap_returninex(x + size - 3, y + 3);
		if (typeMap == 1 || (typeMap == 2 && GameManageMent.mytank.mTankState == 2))
			Destroy((x + size - 3) / CELL, (y + 3) / CELL);
		typeMap = CalcConflictwithmap_returninex(x + size - 3, y + size - 3);
		if (typeMap == 1 || (typeMap == 2 && GameManageMent.mytank.mTankState == 2))
			Destroy((x + size - 3) / CELL, (y + size - 3) / CELL);
	}

	public boolean CalcConflictwithmap(int x, int y, int size)
	{
		if (CalcConflictwithmap(x + 3, y + 3))
			return true;
		if (CalcConflictwithmap(x + 3, y + size - 3))
			return true;
		if (CalcConflictwithmap(x + size - 3, y + 3))
			return true;
		if (CalcConflictwithmap(x + size - 3, y + size - 3))
			return true;
		return false;
	}

	public boolean Calc_Tank_Conflictwithmap(int x, int y, int size)
	{
		if (Calc_tank_Conflictwithmap(x + 3, y + 3))
			return true;
		if (Calc_tank_Conflictwithmap(x + 3, y + size - 3))
			return true;
		if (Calc_tank_Conflictwithmap(x + size - 3, y + 3))
			return true;
		if (Calc_tank_Conflictwithmap(x + size - 3, y + size - 3))
			return true;
		return false;
	}

	public boolean Calc_tank_Conflictwithmap(int x, int y)
	{

		if (x < 0 || x >= WIDTH)
			return true;
		if (y < 0 || y >= HEIGHT)
			return true;
		if (currentmap[y / CELL][x / CELL] == 3)
			return false;
		if (currentmap[y / CELL][x / CELL] != 0)
			return true;
		return false;
	}

	// Postion of tank id in a map
	public boolean isTankConflicOtherTank(int x, int y, int size, int id)
	{
		// Postion of tank id in a map
		// 1 tank have 4 cell in map
		if (x + 8 < 0 || x + size - 8 >= WIDTH)
			return true;
		if (y + 8 < 0 || y + size - 8 >= HEIGHT)
			return true;
		int mrow1 = (y + 8) / CELL;
		int mrow2 = (y + 8) / CELL;
		int mrow3 = (y + size - 8) / CELL;
		int mrow4 = (y + size - 8) / CELL;
		int mCol1 = (x + 8) / CELL;
		int mCol2 = (x + size - 8) / CELL;
		int mCol3 = (x + 8) / CELL;
		int mCol4 = (x + size - 8) / CELL;
		if ((currenttank[mrow1][mCol1] > 0 && id == -1) || (currenttank[mrow1][mCol1] == -1 && id > 0))
			return true;
		if ((currenttank[mrow2][mCol2] > 0 && id == -1) || (currenttank[mrow2][mCol2] == -1 && id > 0))
			return true;
		if ((currenttank[mrow3][mCol3] > 0 && id == -1) || (currenttank[mrow3][mCol3] == -1 && id > 0))
			return true;
		if ((currenttank[mrow4][mCol4] > 0 && id == -1) || (currenttank[mrow4][mCol4] == -1 && id > 0))
			return true;
		// currenttank[][] = id;
		// currenttank[][] = id;
		// currenttank[][] = id;
		// currenttank[][] = id;
		return false;
	}

	public boolean Calc_Tank_Conflictwithmap(int x, int y)
	{

		if (x < 0 || x >= WIDTH)
			return true;
		if (y < 0 || y >= HEIGHT)
			return true;
		if (currentmap[y / CELL][x / CELL] == 3 || currentmap[y / CELL][x / CELL] == 4)
			return false;
		if (currentmap[y / CELL][x / CELL] != 0)
			return true;
		return false;
	}

	public int CalcConfleci_Bulet_with_emery(int x, int y, int size)
	{
		if (x + 3 < 0 || x + size - 3 >= WIDTH)
			return 0;
		if (y + 3 < 0 || y + size - 3 >= HEIGHT)
			return 0;

		if (currenttank[(y + 3) / CELL][(x + 3) / CELL] > 0)
			return currenttank[(y + 3) / CELL][(x + 3) / CELL];
		if (currenttank[(y + 3) / CELL][(x + size - 3) / CELL] > 0)
			return currenttank[(y + 3) / CELL][(x + size - 3) / CELL];
		if (currenttank[(y + size - 3) / CELL][(x + 3) / CELL] > 0)
			return currenttank[(y + size - 3) / CELL][(x + 3) / CELL];
		if (currenttank[(y + size - 3) / CELL][(x + 3) / CELL] > 0)
			return currenttank[(y + size - 3) / CELL][(x + 3) / CELL];
		return 0;
	}

	public boolean CalcConfleci_Bulet_with_my_tank(int x, int y, int size)
	{
		if (x + 3 < 0 || x + size - 3 >= WIDTH)
			return false;
		if (y + 3 < 0 || y + size - 3 >= HEIGHT)
			return false;
		if (currenttank[(y + 3) / CELL][(x + 3) / CELL] == -1)
			return true;
		if (currenttank[(y + 3) / CELL][(x + size - 3) / CELL] == -1)
			return true;
		if (currenttank[(y + size - 3) / CELL][(x + 3) / CELL] == -1)
			return true;
		if (currenttank[(y + size - 3) / CELL][(x + 3) / CELL] == -1)
			return true;
		return false;
	}

}
