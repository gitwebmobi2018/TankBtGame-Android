package gameEngine.com.state;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import com.battle.tanks90.*;

public class StateLoading extends MainActivity {

	public static int loadingtype =-1;
	public static String levelStr ="";
	public static final int LOADING_TYPE_TO_MAINMENU =0;	
	public static final int LOADING_TYPE_GAME_TO_GAME =1;
	public static synchronized void SendMessage(int type) {

		switch (type) {
		case MESSAGE_CTOR:
		
			break;
		case MESSAGE_UPDATE:
			switch (loadingtype) {
			case LOADING_TYPE_TO_MAINMENU:
				
				break;
			case LOADING_TYPE_GAME_TO_GAME:
				GameLayer.arrayListBitmap = null;
				GameLayer.arrayListDataMap = null;
				GameLayer.arrayListAObject = null;
				GameLayer.tileMap = null;
				try {
					if(levelStr.length()<2)
					GameLayer.loadLayerBG("level/level1.tmx");
					else
						GameLayer.loadLayerBG(levelStr);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				changeState(STATE_GAMEPLAY);
				break;				

			default:
				break;
			}
				break;
				
		case MESSAGE_PAINT:
				fontsmall.drawString(mainCanvas, "Loading", 300, 300, 2);
			break;
		case MESSAGE_DTOR:

			break;
		}
	}
	public static synchronized void update(int type) {
		
		
	}
}
