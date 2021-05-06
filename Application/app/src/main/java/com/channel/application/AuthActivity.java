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

public class AuthActivity extends AppCompatActivity
{
    
    public static String TAG = AuthActivity.class.getSimpleName();
    public static void start(final Context c) {

        Intent mApplication = new Intent(c, AuthActivity.class);
        c.startActivity(mApplication);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO: Implement this method 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentics);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

}
