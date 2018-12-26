package com.battle.tanks90;

import gameEngine.com.management.GameManageMent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class Event
{
	public static Bitmap[] bitmaps;
	public Point[] events_position;
	public int[] events;
	public static final int EVENT_FRAME_APPEAR = 210;
	public int[] time_out;
	public int index;

	public Event()
	{
		index = 0;
		events = new int[10];
		time_out = new int[10];
		events_position = new Point[10];
		if (bitmaps == null)
		{
			bitmaps = new Bitmap[6];
			bitmaps[0] = GameLib.loadImageFromAsset("event/image115.png");
			bitmaps[1] = GameLib.loadImageFromAsset("event/image118.png");
			bitmaps[2] = GameLib.loadImageFromAsset("event/image121.png");
			bitmaps[3] = GameLib.loadImageFromAsset("event/image124.png");
			bitmaps[4] = GameLib.loadImageFromAsset("event/image127.png");
			bitmaps[5] = GameLib.loadImageFromAsset("event/image130.png");
		}
	}

	public void AddEventk(int c)
	{
		if (c == -1)
			c = GameManageMent.myRandom.nextInt(6);
		events[index] = c;
		time_out[index] = EVENT_FRAME_APPEAR;
		events_position[index] = new Point(1 + GameManageMent.myRandom.nextInt(24), 1 + GameManageMent.myRandom.nextInt(24));
		// events_position[index] = new Vec2(0,10);
		index++;
	}

	public void RemoveEvent(int i)
	{
		events[i] = events[index - 1];
		events_position[i] = events_position[index - 1];
		time_out[i] = time_out[index - 1];
		index--;
	}

	public void Update()
	{
		for (int i = 0; i < index; i++)
		{
			if (time_out[i] == 0)
			{
				RemoveEvent(i);
				i--;
				continue;
			}
			time_out[i]--;

		}
	}

	public void Draw(Canvas canvas)
	{
		Rect temp = new Rect(0, 0, 16, 16);
		for (int i = 0; i < index; i++)
		{
			if (time_out[i] % 2 == 0)
				canvas.drawBitmap(bitmaps[events[i]], temp, new Rect(16 * events_position[i].x, 16 * events_position[i].y, 16 * events_position[i].x + 32, 16 * events_position[i].y + 32), null);
		}
	}

}
