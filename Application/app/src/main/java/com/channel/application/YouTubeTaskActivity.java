package com.channel.application;

import com.channel.application.R; 
import com.channel.application.ApplicationActivity;
import com.channel.application.youtube.config.YouTubeApi;
import com.channel.application.youtube.config.YouTubeData;
import com.channel.application.youtube.YouTube; 
import com.channel.application.youtube.task.YouTubeChannelTask;
import com.channel.application.youtube.YouTubeFolder;
import com.channel.engine.widget.progress.MaterialProgressBar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.FrameLayout;

import java.io.File;

public class YouTubeTaskActivity extends AppCompatActivity 
{

	public static String ACTION_ADD_PLAYLIST = "addPlaylist";
	public static String ADD_PLAYLIST = "playlistId";
    
	private String channelID = YouTubeApi.YOUTUBE_CHANNEL_ID;
	private String playlistID = YouTubeApi.YOUTUBE_BERANDA;
    
	public static FrameLayout serviceProgress;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_tasks);
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
		YouTubeFolder.initYouTubeFolder();
        
		serviceProgress = (FrameLayout)findViewById(R.id.main_progress_bar_container);
		String action = getIntent().getAction();
        
        if (action != null && action.equals(ACTION_ADD_PLAYLIST))
		{
			String addPlaylistId = getIntent().getStringExtra(ADD_PLAYLIST);
			YouTubeChannelTask task = new YouTubeChannelTask(this, addPlaylistId);
            task.execute();               
		}
		else
		{
			File file = new File(YouTubeData.FOLDER + "/" + YouTubeData.FILENAME);
			if (file.exists())
			{
				serviceProgress.setVisibility(View.INVISIBLE);
                ApplicationActivity.start(YouTubeTaskActivity.this);
                YouTubeTaskActivity.this.finish();
                YouTube mYoutube = new YouTube(YouTubeTaskActivity.this);
                mYoutube.sendShortMessage("YT Channel Is Ready");
				mYoutube.setVibrator(2000);
			}
			else
		    {			
			    YouTubeChannelTask task = new YouTubeChannelTask(this, channelID);
                task.execute(); 
			}
		}
    }


	@Override
	protected void onStop()
	{
		// TODO: Implement this method
		super.onStop();
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
		
	}

	/*@Override
	 protected void attachBaseContext(Context newBase)
	 {
	 // TODO: Implement this method
	 super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	 }*/

}
