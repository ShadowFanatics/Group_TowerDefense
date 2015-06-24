package audio;

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
	private static MediaPlayer sePlayer;
	
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
	
	public static void releasePlayer_SE()
	{
		if(sePlayer != null)
		{
			sePlayer.release();
			sePlayer = null;
		}
	}
	
	public static void releaseAll()
	{
		releasePlayer_bgm();
		releasePlayer_SE();
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
		releasePlayer_SE();
		sePlayer = MediaPlayer.create(currentContext, R.raw.shoot_fire);
		sePlayer.setLooping(false);
		sePlayer.start();
	}
	
	public static void playSE_shoot_water()
	{
		releasePlayer_SE();
		sePlayer = MediaPlayer.create(currentContext, R.raw.shoot_water);
		sePlayer.setLooping(false);
		sePlayer.start();
	}
	
	public static void playSE_shoot_wind()
	{
		releasePlayer_SE();
		sePlayer = MediaPlayer.create(currentContext, R.raw.shoot_wind);
		sePlayer.setLooping(false);
		sePlayer.start();
	}
	
	public static void playSE_shoot_stone()
	{
		releasePlayer_SE();
		sePlayer = MediaPlayer.create(currentContext, R.raw.shoot_stone);
		sePlayer.setLooping(false);
		sePlayer.start();
	}
	
	public static void playSE_shoot_beam()
	{
		releasePlayer_SE();
		sePlayer = MediaPlayer.create(currentContext, R.raw.shoot_beam);
		sePlayer.setLooping(false);
		sePlayer.start();
	}
	
	public static void playSE_shoot_raser()
	{
		releasePlayer_SE();
		sePlayer = MediaPlayer.create(currentContext, R.raw.shoot_raser);
		sePlayer.setLooping(false);
		sePlayer.start();
	}
	
	public static void playSE_life_hurt()
	{
		releasePlayer_SE();
		sePlayer = MediaPlayer.create(currentContext, R.raw.life_hurt);
		sePlayer.setLooping(false);
		sePlayer.start();
	}
	
	public static void playSE_victory()
	{
		//TODO pause BGM until SE_victory is end ??
		/*if(bgmPlayer.isPlaying())
		{
			bgmPlayer.pause();
		}*/
		releasePlayer_SE();
		sePlayer = MediaPlayer.create(currentContext, R.raw.victory);
		sePlayer.setLooping(false);
		/*sePlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
		{
			@Override
			public void onCompletion(MediaPlayer mp)
			{
				bgmPlayer.start();
			}
		});*/
		sePlayer.start();
	}
	
	public static void playSE_gameover()
	{
		releasePlayer_SE();
		sePlayer = MediaPlayer.create(currentContext, R.raw.gameover);
		sePlayer.setLooping(false);
		sePlayer.start();
	}
}
