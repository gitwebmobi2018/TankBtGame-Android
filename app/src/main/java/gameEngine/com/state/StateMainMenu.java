package gameEngine.com.state;

import gameEngine.com.resolution.DEF;
import android.graphics.Bitmap;

import com.battle.tanks90.BitmapFont;
import com.battle.tanks90.MainActivity;
import com.battle.tanks90.R;

public class StateMainMenu extends MainActivity {

	public static int[] MENU_STATE_MAIN = { R.string.STRING_PLAYGAME,
			R.string.STRING_HELP, R.string.STRING_MORE_APP,
			R.string.STRING_ABOUT, R.string.STRING_EXIT };
	public static int menuTopX = DEF.MENU_MAIN_TOP_X;
	public static int menuTopY = DEF.MENU_MAIN_TOP_Y;
	public static int menuElementWidth = DEF.MENU_ELEMENT_WIDTH;
	public static int menuElementHeight = DEF.MENU_ELEMENT_HEIGHT;
	public static int menuElementSpace = DEF.MENU_ELEMENT_SPACE;
	public static int menuTouchOffsetX = DEF.MENU_TOUCH_OFFET_X;
	public static int menuTouchOffsetY = DEF.MENU_TOUCH_OFFET_Y;
	public static int menuTextOffsetX = DEF.MENU_TEXT_OFFSET_X;
	public static int menuTextOffsetY = DEF.MENU_TEXT_OFFSET_Y;

	//
	public static int[] menu = null;

	// String mess = getResources().getString(R.string.mess_1)
	public static synchronized void SendMessage(int type) {
		switch (type) {
		case MESSAGE_CTOR:
			StateConfirm.setConfirmDeActive();
			menu = MENU_STATE_MAIN;
			// sound.play(9, true);
			break;
		case MESSAGE_UPDATE:
			updateMenuCommon();
			break;
		case MESSAGE_PAINT:
			DrawMenuCOmmon(0);
			// draw map
			// MAP 1
			if (StateConfirm.isConfirm)
				StateConfirm.SendMessageStateConfirm(MESSAGE_PAINT);
			break;
		case MESSAGE_DTOR:
			break;
		}
	}

	public static void DrawMenuCOmmon(int select) {
		mainCanvas.drawColor(0xFF234532);
		if (bitmapBgMenu != null) {
			Bitmap background = Bitmap.createScaledBitmap(bitmapBgMenu,
					SCREEN_WIDTH, SCREEN_HEIGHT, false);
			mainCanvas.drawBitmap(background, 0, 0, null);
		}
		// draw game title
		spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_GAME_TITLE_2,
				DEF.GAME_TITLE_X, DEF.GAME_TITLE_Y);
		spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_TANK,
				DEF.GAME_TANK_X, DEF.GAME_TANK_Y);
		for (int i = 0; i < menu.length; i++) {
			if (!StateConfirm.isConfirm
					&& (isTouchDrapInRect(menuTopX + menuTouchOffsetX, menuTopY
							+ menuTouchOffsetY + i
							* (menuElementHeight + menuElementSpace),
							menuElementWidth, menuElementHeight))) {
				spriteMenu.drawAFrame(mainCanvas, 1, menuTopX, menuTopY + i
						* (menuElementHeight + menuElementSpace));
			} else if (mainCanvas != null)
				spriteMenu.drawAFrame(mainCanvas, 0, menuTopX, menuTopY + i
						* (menuElementHeight + menuElementSpace));
			fontsmall.drawString(mainCanvas, mainGameLib.getResources()
					.getString(menu[i]), menuTopX, menuTopY + i
					* (menuElementHeight + menuElementSpace),
					BitmapFont.ALIGN_CENTER);
		}
		//
		drawSoundOption(mainCanvas);
	}

	public static void updateMenuCommon() {
		if (StateConfirm.isConfirm) {
			StateConfirm.SendMessageStateConfirm(MESSAGE_UPDATE);
			return;
		}
		updateSoundOption();
		for (int i = 0; i < menu.length; i++) {
			if (isTouchReleaseInRect(menuTopX + menuTouchOffsetX, menuTopY
					+ menuTouchOffsetY + i
					* (menuElementHeight + menuElementSpace), menuElementWidth,
					menuElementHeight)) {
				sound.play(10, false);
				switch (menu[i]) {
				case R.string.STRING_MAINMENU:
					if (mCurrentState != STATE_MAINMENU)
						changeState(STATE_MAINMENU);
					break;
				case R.string.STRING_PLAYGAME:

					changeState(STATE_SELECT_LEVEL);
					break;
//				case R.string.STRING_MORE_APP:
//					MainActivity.app.showMoreAds();
//					break;
				case R.string.STRING_ABOUT:
					if (mCurrentState != STATE_ABOUT)
						changeState(STATE_ABOUT);
					break;
				case R.string.STRING_HELP:
					if (mCurrentState != STATE_HELP)
						changeState(STATE_HELP);
					break;
				case R.string.STRING_EXIT:
					StateConfirm.setConfirmActive(R.string.STRING_EXIT_COFIRM);
					break;
				}
			}
		}
	}

}
