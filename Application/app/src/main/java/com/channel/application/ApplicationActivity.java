package com.channel.application;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.Gravity;
import android.widget.PopupMenu;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.channel.application.settings.LocalPreference;
import com.channel.application.youtube.models.YouTubeChannel;
import com.channel.application.youtube.fragment.YouTubeChannelFragment;
import com.channel.engine.app.BaseActivity;

public class ApplicationActivity extends BaseActivity {
    
    public static String TAG = ApplicationActivity.class.getSimpleName();
    
    public static void start(final Context c) {

        Intent mApplication = new Intent(c, ApplicationActivity.class);
        c.startActivity(mApplication);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle(YouTubeChannel.getChannelTitle());
        actionBar.setDisplayOptions(actionBar.getDisplayOptions() | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
//        imageView.setImageResource(R.drawable.user);
        Glide.with(getBaseContext())
            .load(YouTubeChannel.getChannelThumbnailDefault())
            .apply(new RequestOptions().circleCrop().placeholder(R.drawable.user).error(R.drawable.user))
            .into(imageView);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        layoutParams.rightMargin = 40;
        imageView.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView);

        showFragment(new YouTubeChannelFragment());

        prepareTabDrawer();
    }
    
   /* @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content_frame);
        if (fragment == null) {
            finish();
        } else {
            fm.beginTransaction().remove(fragment).commitAllowingStateLoss();
        }
    }*/
}

