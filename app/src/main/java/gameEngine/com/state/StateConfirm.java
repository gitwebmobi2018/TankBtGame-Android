package gameEngine.com.state;

import com.battle.tanks90.BitmapFont;
import com.battle.tanks90.MainActivity;
import com.battle.tanks90.R;

import android.graphics.Paint.Style;

public class StateConfirm extends MainActivity {
	public static int IDText = -1;
	public static boolean isConfirm = false;
	//align
	public static int CONFIRM_POS_ARROW_LEFT_X = SCREEN_WIDTH / 8 * 3 - 50;
	public static int CONFIRM_POS_ARROW_RIGHT_X = SCREEN_WIDTH / 8 * 5;
	public static int CONFIRM_POS_ARROW_Y = (SCREEN_HEIGHT >> 1) + 30;

	public static void SendMessageStateConfirm(int type) {
		switch (type) {
		case MESSAGE_CTOR:

			break;
		case MESSAGE_UPDATE:
			if (isTouchReleaseInRect(CONFIRM_POS_ARROW_LEFT_X - 60, CONFIRM_POS_ARROW_Y - 30, 120, 60)) {

				switch (IDText) {
				case R.string.STRING_EXIT_COFIRM:
					sound.stopAllSound();
					mainGameLib.finish();
					break;
				case R.string.STRING_MAINMENU_CONFIRM:
					changeState(STATE_MAINMENU);
					break;
				default:
					break;
				}
			} else if (isTouchReleaseInRect(CONFIRM_POS_ARROW_RIGHT_X - 60, CONFIRM_POS_ARROW_Y - 30, 120, 60))
			{
				sound.play(10, false);
				setConfirmDeActive();
			}
			break;
		case MESSAGE_PAINT:
			mainPaint.setStyle(Style.FILL_AND_STROKE);
			mainCanvas.drawColor(0xAd222222);
			mainPaint.setColor(0xff000000);
			mainCanvas.drawRect(0, SCREEN_HEIGHT / 4, SCREEN_WIDTH, SCREEN_HEIGHT * 3 / 4, mainPaint);
			mainPaint.setStyle(Style.STROKE);
			mainPaint.setStrokeWidth(4);
			mainPaint.setColor(0xfff0c36d);
			mainCanvas.drawRect(0, SCREEN_HEIGHT / 4, SCREEN_WIDTH, SCREEN_HEIGHT * 3 / 4, mainPaint);
			fontsmall.drawString(mainCanvas, mainGameLib.getResources()
					.getString(IDText), SCREEN_WIDTH >> 1, SCREEN_HEIGHT / 4 + 40,
					BitmapFont.ALIGN_CENTER);
			if (isTouchDrapInRect(CONFIRM_POS_ARROW_LEFT_X - 60, CONFIRM_POS_ARROW_Y - 30, 120, 60))
				spriteMenu.drawAFrame(mainCanvas, 1, CONFIRM_POS_ARROW_LEFT_X, CONFIRM_POS_ARROW_Y);
			else
				spriteMenu.drawAFrame(mainCanvas, 0, CONFIRM_POS_ARROW_LEFT_X, CONFIRM_POS_ARROW_Y);
			if (isTouchDrapInRect(CONFIRM_POS_ARROW_RIGHT_X - 60, CONFIRM_POS_ARROW_Y - 30, 120, 60))
				spriteMenu.drawAFrame(mainCanvas, 1, CONFIRM_POS_ARROW_RIGHT_X, CONFIRM_POS_ARROW_Y);
			else
				spriteMenu.drawAFrame(mainCanvas, 0, CONFIRM_POS_ARROW_RIGHT_X, CONFIRM_POS_ARROW_Y);
			fontsmall.drawString(mainCanvas, mainGameLib.getResources()
					.getString(R.string.STRING_YES), CONFIRM_POS_ARROW_LEFT_X, CONFIRM_POS_ARROW_Y,
					BitmapFont.ALIGN_CENTER);
			fontsmall.drawString(mainCanvas, mainGameLib.getResources()
					.getString(R.string.STRING_NO), CONFIRM_POS_ARROW_RIGHT_X,CONFIRM_POS_ARROW_Y,
					BitmapFont.ALIGN_CENTER);
			break;
		case MESSAGE_DTOR:
			break;
		}
	}

	public static void setConfirmActive(int idtext) {
		isConfirm = true;
		IDText = idtext;
	}

	public static void setConfirmDeActive() {
		isConfirm = false;
		IDText = -1;
	}

}
