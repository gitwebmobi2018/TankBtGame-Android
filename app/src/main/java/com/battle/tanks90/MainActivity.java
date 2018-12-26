// author: hoang.nguyenmau
//this file is template for activity same as cGame
//GameLib same GLIb
package com.battle.tanks90;

import gameEngine.com.resolution.DEF;
import gameEngine.com.state.StateAbout;
import gameEngine.com.state.StateGameplay;
import gameEngine.com.state.StateHelp;
import gameEngine.com.state.StateLoading;
import gameEngine.com.state.StateLogo;
import gameEngine.com.state.StateMainMenu;
import gameEngine.com.state.StateSelectLevel;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;


public class MainActivity extends GameLib {

	public static boolean start = true;
	public static boolean running = true;
	public static MainActivity app;
	public static BitmapFont fontsmall = null;
	public static Bitmap logoimage;
	public static Bitmap bitmapBgMenu;
	public static Bitmap bitmapBgGamePlay;
	public static Sprite spriteMenu = null;
	// protected static Sprite spriteMenuButton;
	protected static boolean isGamePause = false;
	public static int mcurrentlevel = 0;
	public static int mLevelUnlock = 0;
	public static Context context;
	public static Sound sound;
	public static String SAVE_REF = "level_ref";
	public static String LEVEL_UNLOCK = "level";
	public static boolean isEnableSound = true;
	public static MainActivity mainActivity;
	Bitmap bufferBitmap = null;
	// Bitmap screen_menu = tanks.loadImageFromAsset("screen/image76.png");
	Canvas buffercanvas = null;
	public String CHART_BOOST_APP_ID = "55a091b60d602550c2b6072c";
	public String CHART_BOOST_APP_SIGNATURES = "67fd08b3792ce07d98a80f11a0d7a38a88d209f9";
	// SCALE
	public static float nScaleX = 1.0f;
	public static float nScaleY = 1.0f;
	// private AdView mAdView;
	// InterstitialAd mInterstitialAd;
	private static final String TAG = "Chartboost";
	private static int timeRequest = 0;

//	private void requestNewInterstitial() {
		// AdRequest adRequest = new AdRequest.Builder().addTestDevice(
		// "YOUR_DEVICE_HASH").build();
		//
		// mInterstitialAd.loadAd(adRequest);
//		Chartboost.cacheInterstitial(CBLocation.LOCATION_GAMEOVER);
//	}

//	public void showMoreAds() {
//		runOnUiThread(new Runnable() {
//
//			public void run() {
//
//				if (Chartboost.hasMoreApps(CBLocation.LOCATION_MAIN_MENU)) {
//
//					Chartboost.showMoreApps(CBLocation.LOCATION_MAIN_MENU);
//				} else {
//					Chartboost.cacheMoreApps(CBLocation.LOCATION_MAIN_MENU);
//				}
//
//			}
//		});
//	}

//	public void showIAds() {
//		runOnUiThread(new Runnable() {
//
//			public void run() {
//				// if (mInterstitialAd.isLoaded()) {
//				// mInterstitialAd.show();
//				// } else {
//				// Log.d("ittus", "The ads is not load");
//				// requestNewInterstitial();
//				// }
//				timeRequest++;
//				if (Chartboost.hasInterstitial(CBLocation.LOCATION_GAMEOVER)) {
//
//					Chartboost.showInterstitial(CBLocation.LOCATION_GAMEOVER);
//				} else {
//
//					requestNewInterstitial();
//
//				}
//
//			}
//		});

//	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = this;
		mainView = new PanelView(this);
		DisplayMetrics metrics = mainView.getResources().getDisplayMetrics();
		GameLib.SCREEN_WIDTH = metrics.widthPixels;
		GameLib.SCREEN_HEIGHT = metrics.heightPixels;
		context = getApplicationContext();

		// Create an ad.
		// adView = new AdView(this, AdSize.BANNER, "a1504f6762a0456");
		/*
		 * 
		 * RelativeLayout layout = new RelativeLayout(this); GameView vw = new
		 * GameView( this, intDpi ); layout.addView(vw);
		 * 
		 * adView = new AdView(this, AdSize.BANNER, "YOUR_AD_UNIT_ID"); //
		 * Places adView at bottom of screen. RelativeLayout.LayoutParams params
		 * = new
		 * RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
		 * RelativeLayout.LayoutParams.WRAP_CONTENT);
		 * params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
		 * RelativeLayout.TRUE); layout.addView(adView); adView.loadAd(new
		 * AdRequest());
		 * 
		 * setContentView(layout);
		 */
		// mAdView = new AdView(this);
		// mAdView.setAdUnitId(getResources().getString(R.string.banner_ads_id));
		// mAdView.setAdSize(AdSize.BANNER);

		FrameLayout layout = new FrameLayout(this);
		// FrameLayout layout = (FrameLayout)findViewById(R.layout.main);
		layout.setPadding(0, 0, 0, 0);
		FrameLayout.LayoutParams gameParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);

		// FrameLayout.LayoutParams adsParams =new
		// FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
		// FrameLayout.LayoutParams.WRAP_CONTENT,
		// android.view.Gravity.TOP|android.view.Gravity.RIGHT);
		// FrameLayout.LayoutParams adsParams = new FrameLayout.LayoutParams(
		// FrameLayout.LayoutParams.WRAP_CONTENT,
		// FrameLayout.LayoutParams.WRAP_CONTENT,
		// android.view.Gravity.BOTTOM
		// | android.view.Gravity.CENTER_HORIZONTAL);

		layout.addView(mainView, gameParams);
		// layout.addView(mAdView, adsParams);

		// mAdView.loadAd(new AdRequest.Builder().build());

		setContentView(layout);

		// mInterstitialAd = new InterstitialAd(this);
		// mInterstitialAd.setAdUnitId("ca-app-pub-2345081211695305/2068058873");
		// mInterstitialAd.setAdListener(new AdListener() {
		//
		// @Override
		// public void onAdClosed() {
		// super.onAdClosed();
		// // requestNewInterstitial();
		// }
		//
		// });
		// requestNewInterstitial();

//		Chartboost.startWithAppId(this, CHART_BOOST_APP_ID, CHART_BOOST_APP_SIGNATURES);
//		Chartboost.setLoggingLevel(Level.ALL);
//		Chartboost.setDelegate(delegate);
//		Chartboost.onCreate(this);
//		requestNewInterstitial();
//		Chartboost.cacheMoreApps(CBLocation.LOCATION_MAIN_MENU);
		// setContentView(mainView, new ViewGroup.LayoutParams(SCREEN_WIDTH,
		// SCREEN_HEIGHT));

		// Log.d("SCREEN_WIDTH : ", " " + SCREEN_WIDTH);
		// Log.d("SCREEN_HEIGHT : ", " " + SCREEN_HEIGHT);

		bufferBitmap = Bitmap.createBitmap(GameLib.SCREEN_WIDTH,
				GameLib.SCREEN_HEIGHT, Config.ARGB_8888);
		buffercanvas = new Canvas(bufferBitmap);
		mainGameLib = this;
		mainActivity = this;
		loadGame();
		changeState(STATE_LOGO);
		(new Thread(new GameThread())).start();
	}

	public void saveGame() {
		SharedPreferences settings = getSharedPreferences(SAVE_REF, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt(LEVEL_UNLOCK, mLevelUnlock);
		editor.commit();
	}

	public void loadGame() {
		SharedPreferences settings = getSharedPreferences(SAVE_REF, 0);
		mLevelUnlock = settings.getInt(LEVEL_UNLOCK, 0);
	}

	public static void changeState(int nextstate) {
		// SendMessage
		resetTouch();
		SendMessage(mCurrentState, MESSAGE_DTOR);
		mPreState = mCurrentState;
		mCurrentState = nextstate;
		SendMessage(nextstate, MESSAGE_CTOR);
		// reset variable
		timeBeginCurrentState = System.currentTimeMillis();// L not 1
		frameCountCurrentState = 0;// L not 1

	}

	public static synchronized void SendMessage(int state, int type) {
		switch (state) {
		case STATE_LOGO:
			StateLogo.SendMessage(type);
			break;
		case STATE_ASKSOUND:
			break;
		case STATE_MAINMENU:
			StateMainMenu.SendMessage(type);
			break;
		case STATE_ABOUT:
			StateAbout.SendMessage(type);
			break;
		case STATE_HELP:
			StateHelp.SendMessage(type);
			break;
		case STATE_GAMEPLAY:
			StateGameplay.SendMessage(type);
			break;
		case STATE_LOADING:
			StateLoading.SendMessage(type);
			break;
		case STATE_SELECT_LEVEL:
			StateSelectLevel.SendMessage(type);
			break;

		}

	}

	Matrix matrix = new Matrix();

	@Override
	public synchronized void DrawAll(Canvas canvas, Paint paint) {
		mainCanvas = buffercanvas;
		mainPaint = paint;

		SendMessage(mCurrentState, MESSAGE_PAINT);
		canvas.drawBitmap(bufferBitmap, matrix, mainPaint);
//		canvas.restore();

	}

	public static void drawSoundOption(Canvas canvas) {
		int frame = DEF.FRAME_SOUND_BEGIN_INDEX;
		if (!isEnableSound) {
			frame += 2;
		}
		if (isTouchDrapInRect(DEF.SOUND_OPTION_X, DEF.SOUND_OPTION_Y,
				DEF.ARROW_WIDTH, DEF.ARROW_HEIGH))
			frame += 1;
		spriteMenu.drawAFrame(canvas, frame, DEF.SOUND_OPTION_X,
				DEF.SOUND_OPTION_Y);
	}

	public static void updateSoundOption() {
		if (isTouchReleaseInRect(DEF.SOUND_OPTION_X, DEF.SOUND_OPTION_Y,
				DEF.ARROW_WIDTH, DEF.ARROW_HEIGH)) {
			isEnableSound = !isEnableSound;
			if (mCurrentState == STATE_MAINMENU || mCurrentState == STATE_ABOUT
					|| mCurrentState == STATE_HELP
					|| mCurrentState == STATE_SELECT_LEVEL)
				sound.play(9, false);
			else
				sound.play(10, false);
		}
		if (!isEnableSound)
			sound.stopAllSound();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		sound.stopAllSound();
//		Chartboost.onDestroy(this);
	}

	@Override
	public void onStart() {
		super.onStart();
//		Chartboost.onStart(this);
	}

	@Override
	public void onResume() {
		super.onResume();
//		Chartboost.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
//		Chartboost.onPause(this);
	}

	@Override
	public void onStop() {
		super.onStop();
//		Chartboost.onStop(this);
	}

	@Override
	public void onBackPressed() {
		// If an interstitial is on screen, close it.
//		if (Chartboost.onBackPressed())
//			return;
//		else
			super.onBackPressed();
	}

	private ChartboostDelegate delegate = new ChartboostDelegate() {

		@Override
		public boolean shouldRequestInterstitial(String location) {
			Log.i(TAG, "SHOULD REQUEST INTERSTITIAL '"
					+ (location != null ? location : "null"));
			return true;
		}

		@Override
		public boolean shouldDisplayInterstitial(String location) {
			Log.i(TAG, "SHOULD DISPLAY INTERSTITIAL '"
					+ (location != null ? location : "null"));
			return true;
		}

		@Override
		public void didCacheInterstitial(String location) {
			Log.i(TAG, "DID CACHE INTERSTITIAL '"
					+ (location != null ? location : "null"));
		}

		@Override
		public void didFailToLoadInterstitial(String location,
				CBImpressionError error) {
			Log.i(TAG, "DID FAIL TO LOAD INTERSTITIAL '"
					+ (location != null ? location : "null") + " Error: "
					+ error.name());
			Toast.makeText(
					getApplicationContext(),
					"INTERSTITIAL '" + location + "' REQUEST FAILED - "
							+ error.name(), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void didDismissInterstitial(String location) {
			Log.i(TAG, "DID DISMISS INTERSTITIAL: "
					+ (location != null ? location : "null"));
			// requestNewInterstitial();
		}

		@Override
		public void didCloseInterstitial(String location) {
			// requestNewInterstitial();
		}

		@Override
		public void didClickInterstitial(String location) {
			Log.i(TAG, "DID CLICK INTERSTITIAL: "
					+ (location != null ? location : "null"));
		}

		@Override
		public void didDisplayInterstitial(String location) {
//			requestNewInterstitial();
		}

		@Override
		public boolean shouldRequestMoreApps(String location) {
			Log.i(TAG, "SHOULD REQUEST MORE APPS: "
					+ (location != null ? location : "null"));
			return true;
		}

		@Override
		public boolean shouldDisplayMoreApps(String location) {
			Log.i(TAG, "SHOULD DISPLAY MORE APPS: "
					+ (location != null ? location : "null"));
			return true;
		}

		@Override
		public void didFailToLoadMoreApps(String location,
				CBImpressionError error) {
			Log.i(TAG, "DID FAIL TO LOAD MOREAPPS "
					+ (location != null ? location : "null") + " Error: "
					+ error.name());
			Toast.makeText(getApplicationContext(),
					"MORE APPS REQUEST FAILED - " + error.name(),
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void didCacheMoreApps(String location) {
			Log.i(TAG, "DID CACHE MORE APPS: "
					+ (location != null ? location : "null"));

		}

		@Override
		public void didDismissMoreApps(String location) {
			Log.i(TAG, "DID DISMISS MORE APPS "
					+ (location != null ? location : "null"));
//			if (!Chartboost.hasMoreApps(CBLocation.LOCATION_MAIN_MENU)) {
//				Chartboost.cacheMoreApps(CBLocation.LOCATION_MAIN_MENU);
//			}
		}

		@Override
		public void didCloseMoreApps(String location) {
			Log.i(TAG, "DID CLOSE MORE APPS: "
					+ (location != null ? location : "null"));
//			if (!Chartboost.hasMoreApps(CBLocation.LOCATION_MAIN_MENU)) {
//				Chartboost.cacheMoreApps(CBLocation.LOCATION_MAIN_MENU);
//			}
		}

		@Override
		public void didClickMoreApps(String location) {
			Log.i(TAG, "DID CLICK MORE APPS: "
					+ (location != null ? location : "null"));
//			if (!Chartboost.hasMoreApps(CBLocation.LOCATION_MAIN_MENU)) {
//				Chartboost.cacheMoreApps(CBLocation.LOCATION_MAIN_MENU);
//			}
		}

		@Override
		public void didDisplayMoreApps(String location) {
			Log.i(TAG, "DID DISPLAY MORE APPS: "
					+ (location != null ? location : "null"));
//			if (!Chartboost.hasMoreApps(CBLocation.LOCATION_MAIN_MENU)) {
//				Chartboost.cacheMoreApps(CBLocation.LOCATION_MAIN_MENU);
//			}
		}

		@Override
		public void didFailToRecordClick(String uri, CBClickError error) {
			Log.i(TAG, "DID FAILED TO RECORD CLICK "
					+ (uri != null ? uri : "null") + ", error: " + error.name());
			Toast.makeText(
					getApplicationContext(),
					"FAILED TO RECORD CLICK " + (uri != null ? uri : "null")
							+ ", error: " + error.name(), Toast.LENGTH_SHORT)
					.show();
		}

		@Override
		public boolean shouldDisplayRewardedVideo(String location) {
			Log.i(TAG, String.format("SHOULD DISPLAY REWARDED VIDEO: '%s'",
					(location != null ? location : "null")));
			return true;
		}

		@Override
		public void didCacheRewardedVideo(String location) {
			Log.i(TAG, String.format("DID CACHE REWARDED VIDEO: '%s'",
					(location != null ? location : "null")));
		}

		@Override
		public void didFailToLoadRewardedVideo(String location,
				CBImpressionError error) {
			Log.i(TAG, String.format(
					"DID FAIL TO LOAD REWARDED VIDEO: '%s', Error:  %s",
					(location != null ? location : "null"), error.name()));
			Toast.makeText(
					getApplicationContext(),
					String.format(
							"DID FAIL TO LOAD REWARDED VIDEO '%s' because %s",
							location, error.name()), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void didDismissRewardedVideo(String location) {
			Log.i(TAG, String.format("DID DISMISS REWARDED VIDEO '%s'",
					(location != null ? location : "null")));
		}

		@Override
		public void didCloseRewardedVideo(String location) {
			Log.i(TAG, String.format("DID CLOSE REWARDED VIDEO '%s'",
					(location != null ? location : "null")));
		}

		@Override
		public void didClickRewardedVideo(String location) {
			Log.i(TAG, String.format("DID CLICK REWARDED VIDEO '%s'",
					(location != null ? location : "null")));
		}

		@Override
		public void didCompleteRewardedVideo(String location, int reward) {
			Log.i(TAG, String.format(
					"DID COMPLETE REWARDED VIDEO '%s' FOR REWARD %d",
					(location != null ? location : "null"), reward));
		}

		@Override
		public void didDisplayRewardedVideo(String location) {
			Log.i(TAG, String.format(
					"DID DISPLAY REWARDED VIDEO '%s' FOR REWARD", location));
		}

		@Override
		public void willDisplayVideo(String location) {
			Log.i(TAG, String.format("WILL DISPLAY VIDEO '%s", location));
		}

	};

}