package com.battle.tanks90;

import android.media.MediaPlayer;

public class Sound
{
	MediaPlayer[] media;
	int lastIndexPlay = -1;

	public Sound()
	{
		media = new MediaPlayer[11];
		media[0] = MediaPlayer.create(MainActivity.context, R.raw.sound1);
		media[1] = MediaPlayer.create(MainActivity.context, R.raw.sound2);
		media[2] = MediaPlayer.create(MainActivity.context, R.raw.sound3);
		media[3] = MediaPlayer.create(MainActivity.context, R.raw.sound4);
		media[4] = MediaPlayer.create(MainActivity.context, R.raw.sound5);
		media[5] = MediaPlayer.create(MainActivity.context, R.raw.sound6);
		media[6] = MediaPlayer.create(MainActivity.context, R.raw.sound7);
		media[7] = MediaPlayer.create(MainActivity.context, R.raw.sound8);
		media[8] = MediaPlayer.create(MainActivity.context, R.raw.sound9);
		
		//
		media[9] = MediaPlayer.create(MainActivity.context, R.raw.m_title);
		media[10] = MediaPlayer.create(MainActivity.context, R.raw.mselect);
	}

	public void play(int index, boolean mustPlay)
	{
		if (!MainActivity.isEnableSound)
			return;
		if (mustPlay) {
			if(lastIndexPlay >=0)
			media[lastIndexPlay].stop();

			try {
				if(lastIndexPlay >=0)
				media[lastIndexPlay].prepare();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			media[index].start();
			lastIndexPlay = index;
		} else {
			if (lastIndexPlay < 0 || lastIndexPlay >= media.length || (media[lastIndexPlay] != null && !media[lastIndexPlay].isPlaying())) {
				media[index].start();
				lastIndexPlay = index;
			}
		}
	}

	public void stopAllSound()
	{
		if(media != null)
		if (lastIndexPlay > -1 && lastIndexPlay < media.length && media[lastIndexPlay] != null) {
			media[lastIndexPlay].stop();
			try {
				media[lastIndexPlay].prepare();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
