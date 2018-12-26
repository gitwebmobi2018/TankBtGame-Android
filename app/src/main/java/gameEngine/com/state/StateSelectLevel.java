package gameEngine.com.state;

import com.battle.tanks90.*;

import gameEngine.com.resolution.DEF;

public class StateSelectLevel extends MainActivity {
	public static int[] MENU_STATE_SELECT_LEVEL = { R.string.STRING_MAINMENU,
			R.string.STRING_HELP, R.string.STRING_ABOUT, R.string.STRING_EXIT };
	public static int mMap_Select = 0;
	public static int[][] arraylevel =
	{ { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 9, 10, 11 }, { 12, 13, 14 } };
	
	public static int menuLevelBeginX = DEF.MENU_LEVEL_BEGIN_X;
	public static int menuLevelBeginY = DEF.MENU_LEVEL_BEGIN_Y;
	public static int menuLevelWIDTH = DEF.MENU_LEVEL_WIDTH;
	public static int menuLevelHEIGHT = DEF.MENU_LEVEL_HEIGHT;
	public static int menuLevelSPACEX = DEF.MENU_LEVEL_SPACE_X;
	public static int menuLevelSPACEY = DEF.MENU_LEVEL_SPACE_Y;

	// String mess = getResources().getString(R.string.mess_1)
	public static void SendMessage(int type) {
		switch (type) {
		case MESSAGE_CTOR:
			StateMainMenu.menu = MENU_STATE_SELECT_LEVEL;
			StateConfirm.setConfirmDeActive();

			break;
		case MESSAGE_UPDATE:
			if (StateConfirm.isConfirm) {
				StateConfirm.SendMessageStateConfirm(MESSAGE_UPDATE);
				return;
			}
			StateMainMenu.updateMenuCommon();
			// update level select

			for (int i = 0; i < arraylevel.length; i++) {
				for (int j = 0; j < arraylevel[0].length; j++) {
					if ((mLevelUnlock >= (arraylevel[i][j] + mMap_Select*15)) &&
							!StateConfirm.isConfirm &&
							isTouchReleaseInRect(menuLevelBeginX + menuLevelSPACEX * j - menuLevelWIDTH / 2,
									menuLevelBeginY + menuLevelSPACEY * i - menuLevelHEIGHT / 2,
									menuLevelWIDTH, menuLevelHEIGHT))
					{
						mcurrentlevel =arraylevel[i][j] + StateSelectLevel.mMap_Select*15;
						sound.stopAllSound();
						changeState(STATE_GAMEPLAY);
					}
				}
			}
			if (isTouchReleaseInRect(DEF.SELECT_LEVEL_ARROW_LEFT_X, DEF.SELECT_LEVEL_ARROW_Y, DEF.ARROW_WIDTH, DEF.ARROW_HEIGH)) {
				sound.play(10, false);
				mMap_Select--;
			}
			if (isTouchReleaseInRect(DEF.SELECT_LEVEL_ARROW_RIGHT_X, DEF.SELECT_LEVEL_ARROW_Y, DEF.ARROW_WIDTH, DEF.ARROW_HEIGH)) {
				sound.play(10, false);
				mMap_Select++;
			}
			if (mMap_Select < 0)
				mMap_Select = 2;
			if (mMap_Select > 2)
				mMap_Select = 0;
			break;
		case MESSAGE_PAINT:
			StateMainMenu.DrawMenuCOmmon(-1);

			for (int i = 0; i < arraylevel.length; i++) {
				for (int j = 0; j < arraylevel[0].length; j++) {
					if ((mLevelUnlock >= (arraylevel[i][j] +  mMap_Select*15)) &&
							!StateConfirm.isConfirm &&
							isTouchDrapInRect(menuLevelBeginX + menuLevelSPACEX * j - menuLevelWIDTH / 2, menuLevelBeginY + menuLevelSPACEY * i - menuLevelHEIGHT / 2, menuLevelWIDTH, menuLevelHEIGHT)) {
						spriteMenu.drawAFrame(mainCanvas, 1, menuLevelBeginX + menuLevelSPACEX * j, menuLevelBeginY + menuLevelSPACEY * i);
					} else if (mainCanvas != null)
						spriteMenu.drawAFrame(mainCanvas, 0, menuLevelBeginX + menuLevelSPACEX * j, menuLevelBeginY + menuLevelSPACEY * i);

					fontsmall.drawString(mainCanvas, "LEVEL\n " + (arraylevel[i][j] + 1 + mMap_Select*15), menuLevelBeginX + menuLevelSPACEX * j - 60,
							menuLevelBeginY + menuLevelSPACEY * i - 20, BitmapFont.ALIGN_LEFT);
					if (mLevelUnlock < (arraylevel[i][j] +  mMap_Select*15))
						spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_LOCK, menuLevelBeginX + menuLevelSPACEX * j + 50, menuLevelBeginY + menuLevelSPACEY * i);
				}
			}
			
			if (isTouchDrapInRect(DEF.SELECT_LEVEL_ARROW_RIGHT_X,DEF.SELECT_LEVEL_ARROW_Y, DEF.ARROW_WIDTH, DEF.ARROW_HEIGH))
				spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_ARROW_RIGHT + 1, DEF.SELECT_LEVEL_ARROW_RIGHT_X, DEF.SELECT_LEVEL_ARROW_Y);
			else
				spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_ARROW_RIGHT, DEF.SELECT_LEVEL_ARROW_RIGHT_X, DEF.SELECT_LEVEL_ARROW_Y);

			if (isTouchDrapInRect(DEF.SELECT_LEVEL_ARROW_LEFT_X, DEF.SELECT_LEVEL_ARROW_Y, DEF.ARROW_WIDTH, DEF.ARROW_HEIGH))
				spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_ARROW_LEFT + 1, DEF.SELECT_LEVEL_ARROW_LEFT_X, DEF.SELECT_LEVEL_ARROW_Y);
			else
				spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_ARROW_LEFT, DEF.SELECT_LEVEL_ARROW_LEFT_X, DEF.SELECT_LEVEL_ARROW_Y);
			if (StateConfirm.isConfirm)
				StateConfirm.SendMessageStateConfirm(MESSAGE_PAINT);
			break;
		case MESSAGE_DTOR:
			break;
		}
	}

}
