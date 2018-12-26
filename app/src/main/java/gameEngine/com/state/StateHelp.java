package gameEngine.com.state;

import com.battle.tanks90.*;

import gameEngine.com.resolution.DEF;

public class StateHelp extends MainActivity {

	public static int currentPage = 0;
	public static int totalPage = 3;

	public static synchronized void SendMessage(int type) {
		switch (type) {
		case MESSAGE_CTOR:
			StateConfirm.setConfirmDeActive();
			StateMainMenu.menu = StateMainMenu.MENU_STATE_MAIN;
			break;
		case MESSAGE_UPDATE:
			if (StateConfirm.isConfirm) {
				StateConfirm.SendMessageStateConfirm(MESSAGE_UPDATE);
				return;
			}
			StateMainMenu.updateMenuCommon();
			if (isTouchReleaseInRect(DEF.HELP_POS_ARROW_LEFT_X, DEF.HELP_POS_ARROW_Y, DEF.ARROW_WIDTH, DEF.ARROW_HEIGH)) {
				currentPage--;
				sound.play(10, false);
				if (currentPage < 0)
					currentPage = totalPage - 1;

			} else if (isTouchReleaseInRect(DEF.HELP_POS_ARROW_RIGHT_X, DEF.HELP_POS_ARROW_Y, DEF.ARROW_WIDTH, DEF.ARROW_HEIGH)) {
				sound.play(10, false);
				currentPage++;
				if (currentPage >= totalPage)
					currentPage = 0;
			}

			break;
		case MESSAGE_PAINT:

			StateMainMenu.DrawMenuCOmmon(1);
			switch (currentPage) {
			case 0:
				fontsmall.drawString(mainCanvas, mainGameLib.getResources().getString(R.string.STRING_HELP1), DEF.HELP_DETAIL_X, DEF.HELP_DETAIL_Y, 400, 2);
				break;
			case 1:
				fontsmall.drawString(mainCanvas, mainGameLib.getResources().getString(R.string.STRING_HELP2), DEF.HELP_DETAIL_X, DEF.HELP_DETAIL_Y, 400, 2);
				break;
			case 2:
				fontsmall.drawString(mainCanvas, mainGameLib.getResources().getString(R.string.STRING_HELP3), DEF.HELP_DETAIL_X, DEF.HELP_DETAIL_Y, 400, 2);
				break;
			default:
				break;
			}
			fontsmall.drawString(mainCanvas, "" + (currentPage + 1) + " / " + totalPage + "", (DEF.HELP_POS_ARROW_LEFT_X + DEF.ARROW_WIDTH + DEF.HELP_POS_ARROW_RIGHT_X) / 2, DEF.HELP_POS_ARROW_Y + DEF.ARROW_HEIGH / 2, 2);

			if (isTouchDrapInRect(DEF.HELP_POS_ARROW_LEFT_X, DEF.HELP_POS_ARROW_Y, DEF.ARROW_WIDTH, DEF.ARROW_HEIGH))
				spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_ARROW_LEFT + 1, DEF.HELP_POS_ARROW_LEFT_X, DEF.HELP_POS_ARROW_Y);
			else
				spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_ARROW_LEFT, DEF.HELP_POS_ARROW_LEFT_X, DEF.HELP_POS_ARROW_Y);

			if (isTouchDrapInRect(DEF.HELP_POS_ARROW_RIGHT_X, DEF.HELP_POS_ARROW_Y, DEF.ARROW_WIDTH, DEF.ARROW_HEIGH))
				spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_ARROW_RIGHT + 1, DEF.HELP_POS_ARROW_RIGHT_X, DEF.HELP_POS_ARROW_Y);
			else
				spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_ARROW_RIGHT, DEF.HELP_POS_ARROW_RIGHT_X, DEF.HELP_POS_ARROW_Y);
			if (StateConfirm.isConfirm)
				StateConfirm.SendMessageStateConfirm(MESSAGE_PAINT);
			break;
		case MESSAGE_DTOR:
			break;
		}
	}

}
