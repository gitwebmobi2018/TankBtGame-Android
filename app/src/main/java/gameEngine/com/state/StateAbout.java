package gameEngine.com.state;

import com.battle.tanks90.*;

import gameEngine.com.resolution.DEF;

public class StateAbout extends MainActivity {

	

	// String mess = getResources().getString(R.string.mess_1)
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
			break;
		case MESSAGE_PAINT:

			StateMainMenu.DrawMenuCOmmon(2);
			switch (currentPage) {
			case 0:
				//fontsmall.drawString(mainCanvas, mainGameLib.getResources().getString(R.string.STRING_ABOUT1), DEF.HELP_DETAIL_X, DEF.HELP_DETAIL_Y, 400, 2);
				break;
			case 1:
				//fontsmall.drawString(mainCanvas, mainGameLib.getResources().getString(R.string.STRING_ABOUT2), DEF.HELP_DETAIL_X, DEF.HELP_DETAIL_Y, 400, 2);
				break;
			case 2:
				//fontsmall.drawString(mainCanvas, mainGameLib.getResources().getString(R.string.STRING_ABOUT3), DEF.HELP_DETAIL_X, DEF.HELP_DETAIL_Y, 400, 2);
				break;
			default:
				break;
			}
			
			if (StateConfirm.isConfirm)
				StateConfirm.SendMessageStateConfirm(MESSAGE_PAINT);
			break;
		case MESSAGE_DTOR:
			break;
		}
	}

}
