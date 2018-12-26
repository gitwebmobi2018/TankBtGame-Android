package gameEngine.com.state;

import com.battle.tanks90.BitmapFont;
import com.battle.tanks90.GameLib;
import com.battle.tanks90.MainActivity;
import com.battle.tanks90.Sound;
import com.battle.tanks90.Sprite;

import gameEngine.com.resolution.DEF;
import gameEngine.com.resolution.SCREEN_800X480;
import android.graphics.Bitmap;

public class StateLogo extends MainActivity {
	public static final int SUBSTATE_LOADING = 0;
	public static final int SUBSTATE_SHOWALL = 1;
	public static int subState = -1;// = 0 = loading ; = 1; show logo
	public static int loadingStep = -1;// = 0 = loading ; = 1; show logo

	public static Bitmap bitmapLogo = null;
	public static Bitmap bitmapBackGround = null;

	public static void SendMessage(int Message) {
		switch (Message) {
		case MESSAGE_CTOR:
			subState = 0;
			loadingStep = -1;
			if (fontsmall == null)
				fontsmall = new BitmapFont("font/font_01.spr", true);
			break;
		case MESSAGE_UPDATE:

			switch (subState) {
			case SUBSTATE_LOADING:
				// BEGIN LOADING
				switch (loadingStep++) {
				case 0:
					if (SCREEN_WIDTH > 700 && SCREEN_WIDTH < 900)
						SCREEN_800X480.setInterface();
					bitmapLogo = GameLib
							.loadImageFromAsset("image/logo.png");
					if (bitmapBackGround == null)
						bitmapBackGround = GameLib
								.loadImageFromAsset(DEF.FILE_BACKDROUND_LOADING);
					if (spriteMenu == null) {
						spriteMenu = new Sprite("sprite/ui/menu.sprt");
						spriteMenu.setAnim(0, SCREEN_WIDTH / 2,
								SCREEN_HEIGHT - 50, true, true);

					}
					break;
				case 1:
					spriteMenu.setAnim(0, SCREEN_WIDTH / 2, SCREEN_HEIGHT - 50,
							true, true);
					break;
				case 2:
					if (MainActivity.bitmapBgGamePlay == null)
						MainActivity.bitmapBgGamePlay = GameLib
								.loadImageFromAsset(DEF.FILE_NAME_MENU_BACKDROUND_GAMEPLAY);
					break;
				case 3:
					if (StateGameplay.spriteGameBackGround == null)
						StateGameplay.spriteGameBackGround = new Sprite(
								"sprite/background/gamebackground.sprt");
					break;
				case 4:
					if (MainActivity.bitmapBgMenu == null)
						MainActivity.bitmapBgMenu = GameLib
								.loadImageFromAsset(DEF.FILE_NAME_MENU_BACKDROUND_MENU);
					break;
				case 5:
					sound = new Sound();
					break;
				default:
					break;
				}

				if (loadingStep == 70) {
					sound.play(9, true);
					subState = SUBSTATE_SHOWALL;
					spriteMenu.setAnim(DEF.SPRITE_TITLE_ANIM,
							DEF.LOGO_GAME_TILE_X, DEF.LOGO_GAME_TILE_Y, false,
							true);
				}

				break;
			case SUBSTATE_SHOWALL:

				if (isTouchReleaseScreen())
					changeState(STATE_MAINMENU);

				break;

			default:
				break;
			}

			// if (frameCountCurrentState == 3)

			break;
		case MESSAGE_PAINT:
			mainCanvas.drawARGB(255, 0, 0, 0);
			if (bitmapBackGround != null) {
				Bitmap background = Bitmap.createScaledBitmap(bitmapBackGround,
						SCREEN_WIDTH, SCREEN_HEIGHT, false);

				mainCanvas.drawBitmap(background, 0, 0, null);
			}

			switch (subState) {
			case SUBSTATE_LOADING:
				if (spriteMenu != null && loadingStep > 1)
					spriteMenu.drawAnim(mainCanvas);
				if (bitmapLogo != null)
					mainCanvas
							.drawBitmap(
									bitmapLogo,
									(mainCanvas.getWidth() - bitmapLogo
											.getWidth()) >> 1,
									((mainCanvas.getHeight() - bitmapLogo
											.getHeight()) >> 1)
											- DEF.LOGO_ICON_OFFSET_Y, null);
				break;
			case SUBSTATE_SHOWALL:
				spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_TANK,
						(mainCanvas.getWidth() >> 1),
						mainCanvas.getHeight() >> 1);
				spriteMenu.drawAFrame(mainCanvas, DEF.INDEX_FRAME_GAME_TITLE,
						(mainCanvas.getWidth() >> 1),
						(mainCanvas.getHeight() >> 1));

				if (frameCountCurrentState % 10 > 5)
					fontsmall.drawString(mainCanvas,
							"TOUCH SCREEN TO CONTINUE", SCREEN_WIDTH / 2,
							SCREEN_HEIGHT - 40, fontsmall.ALIGN_CENTER);

				break;

			default:
				break;
			}

			break;
		case MESSAGE_DTOR:

			break;
		default:
			break;
		}

	}
}
