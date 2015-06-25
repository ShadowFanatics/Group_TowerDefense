package audio;

import java.io.IOException;

import group.activity.R;
import android.content.Context;
import android.media.MediaPlayer;

public class AudioManager
{
	/**
	 * no need to new AudioManager()
	 * AudioManager.setContext(your Activity);
	 * call setContext() again when you change Activity
	 * 
	 * onStop() or onDestroy()
	 * AudioManager.releaseAll();
	 */
	
	private static Context currentContext;
	private static MediaPlayer bgmPlayer;
	private static MediaPlayer sePlayer1;
	private static MediaPlayer sePlayer2;
	private static MediaPlayer sePlayer3;
	private static MediaPlayer sePlayer4;
	private static MediaPlayer sePlayer5;
	private static MediaPlayer sePlayer6;
	private static MediaPlayer sePlayer7;
	
	public AudioManager()
	{
	}
	
	public static void setContext(Context context)
	{
		currentContext = context;
	}
	
	public static void releasePlayer_bgm()
	{
		if(bgmPlayer != null)
		{
			bgmPlayer.release();
			bgmPlayer = null;
		}
	}
	
	private static void releasePlayer_SE1()
	{
		if(sePlayer1 != null)
		{
			sePlayer1.release();
			sePlayer1 = null;
		}
	}
	private static void releasePlayer_SE2()
	{
		if(sePlayer2 != null)
		{
			sePlayer2.release();
			sePlayer2 = null;
		}
	}
	private static void releasePlayer_SE3()
	{
		if(sePlayer3 != null)
		{
			sePlayer3.release();
			sePlayer3 = null;
		}
	}
	private static void releasePlayer_SE4()
	{
		if(sePlayer4 != null)
		{
			sePlayer4.release();
			sePlayer4 = null;
		}
	}
	private static void releasePlayer_SE5()
	{
		if(sePlayer5 != null)
		{
			sePlayer5.release();
			sePlayer5 = null;
		}
	}
	private static void releasePlayer_SE6()
	{
		if(sePlayer6 != null)
		{
			sePlayer6.release();
			sePlayer6 = null;
		}
	}
	private static void releasePlayer_SE7()
	{
		if(sePlayer7 != null)
		{
			sePlayer7.release();
			sePlayer7 = null;
		}
	}
	
	public static void releasePlayer_SE()
	{
		releasePlayer_SE1();
		releasePlayer_SE2();
		releasePlayer_SE3();
		releasePlayer_SE4();
		releasePlayer_SE5();
		releasePlayer_SE6();
		releasePlayer_SE7();
	}
	
	public static void releaseAll()
	{
		releasePlayer_bgm();
		releasePlayer_SE();
		currentContext = null;
	}
	
	public static void playBGM_title()
	{
		releasePlayer_bgm();
		bgmPlayer = MediaPlayer.create(currentContext, R.raw.bgm_title);
		bgmPlayer.setLooping(true);
		bgmPlayer.start();
	}
	
	public static void playBGM_battle()
	{
		releasePlayer_bgm();
		bgmPlayer = MediaPlayer.create(currentContext, R.raw.bgm_battle);
		bgmPlayer.setLooping(true);
		bgmPlayer.start();
	}
	
	public static void playBGM_boss()
	{
		releasePlayer_bgm();
		bgmPlayer = MediaPlayer.create(currentContext, R.raw.bgm_boss);
		bgmPlayer.setLooping(true);
		bgmPlayer.start();
	}
	
	public static void playSE_shoot_fire()
	{
		releasePlayer_SE1();
		sePlayer1 = MediaPlayer.create(currentContext, R.raw.shoot_fire);
		sePlayer1.setLooping(false);
		sePlayer1.start();
	}
	
	public static void playSE_shoot_water()
	{
		releasePlayer_SE2();
		sePlayer2 = MediaPlayer.create(currentContext, R.raw.shoot_water);
		sePlayer2.setLooping(false);
		sePlayer2.start();
	}
	
	public static void playSE_shoot_wind()
	{
		releasePlayer_SE3();
		sePlayer3 = MediaPlayer.create(currentContext, R.raw.shoot_wind);
		sePlayer3.setLooping(false);
		sePlayer3.start();
	}
	
	public static void playSE_shoot_electricity()
	{
		releasePlayer_SE4();
		sePlayer4 = MediaPlayer.create(currentContext, R.raw.shoot_electricity);
		sePlayer4.setLooping(false);
		sePlayer4.start();
	}
	
	public static void playSE_shoot_beam()
	{
		releasePlayer_SE5();
		sePlayer5 = MediaPlayer.create(currentContext, R.raw.shoot_beam);
		sePlayer5.setLooping(false);
		sePlayer5.start();
	}
	
	public static void playSE_shoot_raser()
	{
		releasePlayer_SE6();
		sePlayer6 = MediaPlayer.create(currentContext, R.raw.shoot_raser);
		sePlayer6.setLooping(false);
		sePlayer6.start();
	}
	
	public static void playSE_life_hurt()
	{
		releasePlayer_SE7();
		sePlayer7 = MediaPlayer.create(currentContext, R.raw.life_hurt);
		sePlayer7.setLooping(false);
		sePlayer7.start();
	}
	
	public static void playSE_victory()
	{
		//TODO pause BGM until SE_victory is end ??
		/*if(bgmPlayer.isPlaying())
		{
			bgmPlayer.pause();
		}*/
		releasePlayer_SE7();
		sePlayer7 = MediaPlayer.create(currentContext, R.raw.victory);
		sePlayer7.setLooping(false);
		/*sePlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
		{
			@Override
			public void onCompletion(MediaPlayer mp)
			{
				bgmPlayer.start();
			}
		});*/
		sePlayer7.start();
	}
	
	public static void playSE_gameover()
	{
		releasePlayer_SE7();
		sePlayer7 = MediaPlayer.create(currentContext, R.raw.gameover);
		sePlayer7.setLooping(false);
		sePlayer7.start();
	}
}
