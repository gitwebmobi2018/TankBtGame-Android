package gameEngine.com.Actor;

import java.util.Random;

import com.battle.tanks90.GameLayer;
import com.battle.tanks90.GameLib;
import com.battle.tanks90.MainActivity;
import com.battle.tanks90.Sprite;

import android.graphics.Color;

public class Actor {
	//type of Actor
	public final static int TYPE_MOUSE = 0;
	public final static int TYPE_GIF = 1;
	public final static int TYPE_WALL = 2;
	public static Random random = new Random(); 
	public String TYPE = " ";
	public String NAME = " ";
	public int m_x = 100;
	public int m_y = 100;
	public int m_Width = 12;
	public int m_Height = 12;
	public int m_speedx = 10;//defaut
	public int m_speedy = 10;//defaule
	public int m_speedxy = 7;//defaule
	public Sprite sprite;
	public int animID = 0;
	public int frameID = 0;
//	int currentFrame;
//	int currentAnimation;
	public int angle = 0;
	public int state = -1;	
	public int _currentFrame = 0;
	public int _waitDelay = 0;
	public int _currentAnimation =0;
	
	
	public boolean _canLoop = false;
	public boolean _ShowLastFrame = true;
	
	public Actor() {

	}

	public Actor(int x, int y, int w, int h, String Name, String Type) {
		//	sprite = new Sprite(pathSprite, true);

		m_x = x;
		m_y = y;
		m_Width = w;
		m_Height = h;
		NAME = Name;
		Type = Type;
	}
	public void setPostion(int x, int y) {
		m_x = x;
		m_y = y;
	}
	public void drawRect(int x, int y, int w, int h) {
		GameLib.mainPaint.setColor(Color.RED);
		GameLib.mainCanvas.drawRect(x, y, x + w, y + h, GameLib.mainPaint);
	}

	public void render() {
		GameLib.mainCanvas.drawRect((m_x + GameLayer.screenOffsetX), (m_y + GameLayer.screenOffsetY), (m_x + GameLayer.screenOffsetX) + m_Width, (m_y + GameLayer.screenOffsetY) + m_Height, GameLib.mainPaint);
		MainActivity.fontsmall.drawString(GameLib.mainCanvas, "Object", (m_x + GameLayer.screenOffsetX), (m_y + GameLayer.screenOffsetY), 0);
		//sprite.drawAnim(GameLib.mainCanvas, m_x, m_y);
	}

	//ACTOR TYPE
	public static final int ACTOR_TYPE_MAINMC = 0;
	public static final int ACTOR_TYPE_DOOR = 1;

	public static Actor createActor(int x, int y, int w, int h, String Name, String type) {
		int typeInt = getActorTypeFromString(type);
		switch (typeInt) {
		case ACTOR_TYPE_MAINMC:
		
			return null;
		//case ACTOR_TYPE_DOOR:
		//	return new Door(x, y, w, h, Name, type);
		default:
			break;
		}
		return null;
	}

	public static int getActorTypeFromString(String type) {
		if (type.equals("mainmc")) {
			return ACTOR_TYPE_MAINMC;
		} else if (type.equals("door")) {
			return ACTOR_TYPE_DOOR;
		}
		return -1;
	}

	public static boolean checkCollision(Actor actor1, Actor actor2) { //true when it have collition
		return !(actor1.m_x > actor2.m_x + actor2.m_Width
				|| actor1.m_x + actor1.m_Width < actor2.m_x
				|| actor1.m_y > actor2.m_y + actor2.m_Height || actor1.m_y + actor1.m_Height < actor2.m_y);
	}

	public void update() {
		//it will overide in child class
		// TODO Auto-generated method stub

	}

}
