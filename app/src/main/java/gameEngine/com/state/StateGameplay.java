package gameEngine.com.state;

import com.battle.tanks90.BitmapFont;
import com.battle.tanks90.GameLib;
import com.battle.tanks90.IConstant;
import com.battle.tanks90.MainActivity;
import com.battle.tanks90.R;
import com.battle.tanks90.Sprite;

import gameEngine.com.management.GameManageMent;
import gameEngine.com.resolution.DEF;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.util.Log;

public class StateGameplay extends MainActivity implements IConstant {
	public static int[] menu = { R.string.STRING_PAUSE,
			R.string.STRING_MAINMENU, R.string.STRING_EXIT };
	public static Bitmap background = null;
	public static Sprite spriteDpad = null;
	public static Sprite spriteGameBackGround = null;
	// public static GameLayerBG gameLayerBG = new GameLayerBG();
	// public static GameLayerOB gameLayerOB = new GameLayerOB();

	// test
	// public static Enemy enemy;
	// public static Alien enemy2;
	// endtest
	//
	public static int COMPLETE_NONE = 0;
	public static int COMPLETE_MAP = 1;
	public static int COMPLETE_GAME = 2;
	public static GameManageMent gamemanagement;;
	public static int msubStateComplete = 0;
	public static int mTransattion = -1;
	public static int TRANSATION_MAX_COUNTER = 25;
	//
	// public static boolean isGamePause = false;

	static float scale = 3.0f;
	static Matrix mat;
	static Paint a = new Paint();//

	public static int menuGamePLayTopX = DEF.MENU_GAME_PLAY_TOP_X;
	public static int menuGamePLayTopY = DEF.MENU_GAME_PLAY_TOP_Y;
	public static int menuElementWidth = StateMainMenu.menuElementWidth;
	public static int menuElementHeight = StateMainMenu.menuElementHeight;
	public static int menuElementSpace = StateMainMenu.menuElementSpace;
	public static int menuTouchOffsetX = StateMainMenu.menuTouchOffsetX;
	public static int menuTouchOffsetY = StateMainMenu.menuTouchOffsetY;
	public static int menuTextOffsetX = StateMainMenu.menuTextOffsetX;
	public static int menuTextOffsetY = StateMainMenu.menuTextOffsetY;

	public static synchronized void SendMessage(int type) {

		switch (type) {
		case MESSAGE_CTOR:
			gamemanagement = new GameManageMent(mcurrentlevel);

			gamemanagement.NewGame(mcurrentlevel);
			MainActivity.sound.play(7, false);

			break;
		case MESSAGE_UPDATE:
			
			if (StateConfirm.isConfirm) {
				StateConfirm.SendMessageStateConfirm(MESSAGE_UPDATE);
				return;
			}
			if (isGamePause) {
				if (isTouchReleaseScreen())
					isGamePause = false;
				return;
			}
			if (mTransattion > -1)
				mTransattion--;
			if (GameManageMent.mSubState != GameManageMent.SUB_STATE_PLAY) {
				if (GameManageMent.mSubState == GameManageMent.SUB_STATE_LOSE
						&& mTransattion <= 0) {
					// RESTART
				
					if (isTouchReleaseInRect(DEF.MENU_LOSE_RESTART_X
							+ menuTouchOffsetX, DEF.MENU_LOSE_Y
							+ menuTouchOffsetY, menuElementWidth,
							menuElementHeight)) {
//						MainActivity.app.showIAds();
						changeState(STATE_GAMEPLAY);
					}
					// main menu
					if (isTouchReleaseInRect(DEF.MENU_LOSE_MAINMENU_X
							+ menuTouchOffsetX, DEF.MENU_LOSE_Y
							+ menuTouchOffsetY, menuElementWidth,
							menuElementHeight)) {
//						MainActivity.app.showIAds();
						changeState(STATE_MAINMENU);
					}
					// exit
					if (isTouchReleaseInRect(DEF.MENU_LOSE_EXIT_X
							+ menuTouchOffsetX, DEF.MENU_LOSE_Y
							+ menuTouchOffsetY, menuElementWidth,
							menuElementHeight)) {
						StateConfirm
								.setConfirmActive(R.string.STRING_EXIT_COFIRM);
					}

				}
				if (GameManageMent.mSubState == GameManageMent.SUB_STATE_WIN
						&& mTransattion <= 0) {
					// NEXT LEVEL
					Log.d("ittus","here");
					if (mcurrentlevel < 45)
						if (isTouchReleaseInRect(DEF.MENU_WIN_NEXT_LEVEL_X
								+ menuTouchOffsetX, DEF.MENU_LOSE_Y
								+ menuTouchOffsetY, menuElementWidth,
								menuElementHeight)) {
//							MainActivity.app.showIAds();
							changeState(STATE_GAMEPLAY);
					
						}
					// main menu
					if (isTouchReleaseInRect(DEF.MENU_WIN_MAINMENU_X
							+ menuTouchOffsetX, DEF.MENU_LOSE_Y
							+ menuTouchOffsetY, menuElementWidth,
							menuElementHeight)) {
//						MainActivity.app.showIAds();
						changeState(STATE_MAINMENU);
						
					}
					// exit
					if (isTouchReleaseInRect(DEF.MENU_WIN_EXIT_X
							+ menuTouchOffsetX, DEF.MENU_LOSE_Y
							+ menuTouchOffsetY, menuElementWidth,
							menuElementHeight)) {
//						MainActivity.app.showIAds();
						StateConfirm
								.setConfirmActive(R.string.STRING_EXIT_COFIRM);
					}
				}

				return;
			}
			if (PRINTLOG) {// cheat
				if (isTouchReleaseInRect(SCREEN_WIDTH / 2 - 100, 0, 200, 100)) {
					GameManageMent.mSubState = GameManageMent.SUB_STATE_WIN;
					// GameManageMent.gamepause = true;
					mTransattion = TRANSATION_MAX_COUNTER;
					StateGameplay.inCreateLevel();
				}
			}
			updateSoundOption();

			gamemanagement.Update(mainCanvas);

			for (int i = 0; i < menu.length; i++) {
				if (isTouchReleaseInRect(menuGamePLayTopX + menuTouchOffsetX,
						menuGamePLayTopY + menuTouchOffsetY + i
								* (menuElementHeight + menuElementSpace),
						menuElementWidth, menuElementHeight)) {
					sound.play(10, false);
					switch (menu[i]) {

					case R.string.STRING_RESUME:
						isGamePause = false;
						mCurrentState = STATE_GAMEPLAY;
						break;
					case R.string.STRING_PAUSE:
						isGamePause = true;
						mCurrentState = STATE_GAMEPLAY;
						break;

					case R.string.STRING_MAINMENU:
						StateConfirm
								.setConfirmActive(R.string.STRING_MAINMENU_CONFIRM);
						break;
					case R.string.STRING_EXIT:
						StateConfirm
								.setConfirmActive(R.string.STRING_EXIT_COFIRM);
						break;
					}
				}
			}
			break;

		case MESSAGE_PAINT:
			mainCanvas.drawColor(0xFF234532);
			if (bitmapBgGamePlay != null) {
//				Bitmap background = Bitmap.createScaledBitmap(bitmapBgGamePlay,
//						SCREEN_WIDTH, SCREEN_HEIGHT, false);
//				mainCanvas.drawBitmap(bitmapBgGamePlay, 0, 0, null);
				Bitmap background = Bitmap.createScaledBitmap(bitmapBgGamePlay,
						GameLib.SCREEN_WIDTH, GameLib.SCREEN_HEIGHT, false);
				mainCanvas.drawBitmap(background, 0, 0, null);
			}
			drawSoundOption(mainCanvas);

			gamemanagement.drawAll(mainCanvas);
			{
				for (int i = 0; i < menu.length; i++) {
					if (!StateConfirm.isConfirm
							&& isTouchDrapInRect(menuGamePLayTopX
									+ menuTouchOffsetX, menuGamePLayTopY
									+ menuTouchOffsetY + i
									* (menuElementHeight + menuElementSpace),
									menuElementWidth, menuElementHeight)) {
						spriteMenu
								.drawAFrame(
										mainCanvas,
										1,
										menuGamePLayTopX,
										menuGamePLayTopY
												+ i
												* (menuElementHeight + menuElementSpace));
					} else if (mainCanvas != null)
						spriteMenu
								.drawAFrame(
										mainCanvas,
										0,
										menuGamePLayTopX,
										menuGamePLayTopY
												+ i
												* (menuElementHeight + menuElementSpace));
					fontsmall.drawString(mainCanvas, mainGameLib.getResources()
							.getString(menu[i]), menuGamePLayTopX,
							menuGamePLayTopY + i
									* (menuElementHeight + menuElementSpace),
							BitmapFont.ALIGN_CENTER);
				}

				if (GameManageMent.mSubState != GameManageMent.SUB_STATE_PLAY
						&& mTransattion <= 0) {
					mainPaint.setStyle(Style.FILL_AND_STROKE);
					mainCanvas.drawColor(0xAd222222);
					mainPaint.setColor(0xff000000);
					mainCanvas.drawRect(0, SCREEN_HEIGHT / 8, SCREEN_WIDTH,
							SCREEN_HEIGHT * 7 / 8, mainPaint);
					mainPaint.setStyle(Style.STROKE);
					mainPaint.setStrokeWidth(4);
					mainPaint.setColor(0xfff0c36d);
					mainCanvas.drawRect(0, SCREEN_HEIGHT / 8, SCREEN_WIDTH,
							SCREEN_HEIGHT * 7 / 8, mainPaint);
				}

				if (GameManageMent.mSubState == GameManageMent.SUB_STATE_LOSE
						&& mTransattion <= 0) {
					spriteMenu.drawAFrame(mainCanvas, 12, SCREEN_WIDTH / 2,
							DEF.MENU_WIN_LOSE_TITLE);

					fontsmall.drawString(mainCanvas, "OOOHHH. TRY AGAIN??",
							SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2,
							BitmapFont.ALIGN_CENTER);
					// restart
					spriteMenu.drawAFrame(mainCanvas, 0,
							DEF.MENU_LOSE_RESTART_X, DEF.MENU_LOSE_Y);
					fontsmall.drawString(mainCanvas, "RESTART",
							DEF.MENU_LOSE_RESTART_X, DEF.MENU_LOSE_Y,
							BitmapFont.ALIGN_CENTER);
					// main menu
					spriteMenu.drawAFrame(mainCanvas, 0,
							DEF.MENU_LOSE_MAINMENU_X, DEF.MENU_LOSE_Y);
					fontsmall.drawString(mainCanvas, "MAIN MENU",
							DEF.MENU_LOSE_MAINMENU_X, DEF.MENU_LOSE_Y,
							BitmapFont.ALIGN_CENTER);
					// exit
					spriteMenu.drawAFrame(mainCanvas, 0, DEF.MENU_LOSE_EXIT_X,
							DEF.MENU_LOSE_Y);
					fontsmall.drawString(mainCanvas, "EXIT",
							DEF.MENU_LOSE_EXIT_X, DEF.MENU_LOSE_Y,
							BitmapFont.ALIGN_CENTER);
				} else if (GameManageMent.mSubState == GameManageMent.SUB_STATE_WIN
						&& mTransattion <= 0) {

					spriteMenu.drawAFrame(mainCanvas, 11, SCREEN_WIDTH / 2,
							DEF.MENU_WIN_LOSE_TITLE);

					// next level
					if (mcurrentlevel < 45) {
						if (msubStateComplete == COMPLETE_MAP)
							fontsmall
									.drawString(
											mainCanvas,
											"COMPLETE LEVEL "
													+ (mcurrentlevel)
													+ " \n UNLOCK THE NEXT LEVEL AND UNLOCK NEW BACKGROUND",
											SCREEN_WIDTH / 2,
											SCREEN_HEIGHT / 2,
											BitmapFont.ALIGN_CENTER);
						else
							fontsmall.drawString(mainCanvas, "COMPLETE LEVEL "
									+ (mcurrentlevel)
									+ " \n UNLOCK THE NEXT LEVEL",
									SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2,
									BitmapFont.ALIGN_CENTER);
						spriteMenu.drawAFrame(mainCanvas, 0,
								DEF.MENU_WIN_NEXT_LEVEL_X, DEF.MENU_WIN_Y);
						fontsmall.drawString(mainCanvas, "NEXT LEVEL",
								DEF.MENU_WIN_NEXT_LEVEL_X, DEF.MENU_WIN_Y,
								BitmapFont.ALIGN_CENTER);

					} else {
						fontsmall
								.drawString(
										mainCanvas,
										"Congtuaration:\n Game is completed."
												+ " \n Now you can  play any level at main menu."
												+ " \n Thanks you for play this game.",
										SCREEN_WIDTH / 2,
										SCREEN_HEIGHT / 2 - 30,
										BitmapFont.ALIGN_CENTER);

					}
					// MAIN MENU
					spriteMenu.drawAFrame(mainCanvas, 0,
							DEF.MENU_WIN_MAINMENU_X, DEF.MENU_WIN_Y);
					fontsmall.drawString(mainCanvas, "MAIN MENU",
							DEF.MENU_WIN_MAINMENU_X, DEF.MENU_WIN_Y,
							BitmapFont.ALIGN_CENTER);
					// exit
					spriteMenu.drawAFrame(mainCanvas, 0, DEF.MENU_WIN_EXIT_X,
							DEF.MENU_WIN_Y);
					fontsmall.drawString(mainCanvas, "EXIT",
							DEF.MENU_WIN_EXIT_X, DEF.MENU_WIN_Y,
							BitmapFont.ALIGN_CENTER);
				}
			}

			if (StateConfirm.isConfirm)
				StateConfirm.SendMessageStateConfirm(MESSAGE_PAINT);
			if (isGamePause
					&& GameManageMent.mSubState == GameManageMent.SUB_STATE_PLAY) {
				mainPaint.setStyle(Style.FILL_AND_STROKE);
				mainCanvas.drawColor(0xAd222222);
				if (frameCountCurrentState % 10 > 5)
					fontsmall.drawString(mainCanvas,
							"TOUCH SCREEN TO RESUME GAME", SCREEN_WIDTH / 2,
							SCREEN_HEIGHT / 2, BitmapFont.ALIGN_CENTER);
			}
			break;
		case MESSAGE_DTOR:
			break;
		}
	}

	public static void inCreateLevel() {
		msubStateComplete = COMPLETE_NONE;
		mcurrentlevel++;
		if (mcurrentlevel > mLevelUnlock) {
			mLevelUnlock++;
			if (mLevelUnlock >= 45) {
				msubStateComplete = COMPLETE_GAME;
			} else if ((mLevelUnlock == 15) || (mLevelUnlock == 30))
				msubStateComplete = COMPLETE_MAP;
		}
		mainActivity.saveGame();

	}

}
