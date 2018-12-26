package com.battle.tanks90;

public class GameThread implements Runnable {
	public static int FRAME_RATE = 20;
	public static long timeCurrent;
	public static long timePrev = System.currentTimeMillis();

	public void run() {
		while (true) {
			while (MainActivity.running) {

				timeCurrent = System.currentTimeMillis();

				if ((timeCurrent - timePrev) > 1000 / FRAME_RATE) {
					//Log.d("ccc :", " " + (timeCurrent - timePrev));
					timePrev = timeCurrent;

					GameLib.frameCountCurrentState++;
					MainActivity.SendMessage(MainActivity.mCurrentState, MainActivity.MESSAGE_UPDATE);
					// MainActivity.SendMessage(MainActivity.mCurrentState,MainActivity.MESSAGE_PAINT);
					//hame ve se duoc chuyen xuong ben duoi

					MainActivity.mainView.postInvalidate();
					MainActivity.UpdateKey();

					MainActivity.UpdateTouch();
				} else {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}    
