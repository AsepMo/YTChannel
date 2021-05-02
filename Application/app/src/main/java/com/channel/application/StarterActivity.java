package com.channel.application;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.channel.application.youtube.YouTube;
import com.channel.application.youtube.YouTubeFolder;

public class StarterActivity extends AppCompatActivity {

    private Handler mHandler = new Handler(); 
    private Runnable mRunner = new Runnable()
    {
        @Override 
        public void run()
        {
            YouTube mYoutube = new YouTube(StarterActivity.this);
            mYoutube.startActivity(StarterActivity.this, ApplicationTasksActivity.class);
            //mYoutube.setVibrator(2000);
            StarterActivity.this.finish(); 

            YouTubeFolder.initFolder(new YouTubeFolder.Builder(getApplicationContext())
                                     .setDefaultFolder(YouTubeFolder.ZFOLDER)
                                     .setFolderApk(true) 
                                     .setFolderImage(true)
                                     .setFolderArchive(true)
                                     .setFolderEbook(true)
                                     .setFolderImage(true)
                                     .setFolderVideo(true)
                                     .setFolderYoutube(true)
                                     .setFolderScriptMe(true)
                                     .build()); 
        }
    }; 
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO: Implement this method 
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mHandler.postDelayed(mRunner, 2500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // TODO: Implement this method
        menu.add("Youtube")
            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
                @Override
                public boolean onMenuItemClick(MenuItem item)
                {
                    //Application.startActivity(AndroWebActivity.this, YoutubePlaylistActivity.class);
                    return true;
                }
            })
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause()
    {
        // TODO: Implement this method
        super.onPause(); 
        mHandler.removeCallbacks(mRunner);
    }

    @Override
    protected void onDestroy()
    {
        // TODO: Implement this method
        super.onDestroy();
        mHandler.removeCallbacks(mRunner);
    }
}
