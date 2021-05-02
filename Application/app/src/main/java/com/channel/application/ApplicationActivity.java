package com.channel.application;

import android.support.v7.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.Gravity;
import android.widget.PopupMenu;
import android.widget.ImageView;

import com.channel.application.utils.NetworkUtil;
import com.channel.application.settings.LocalPreference;
import com.channel.application.youtube.YouTubeInfo;
import com.channel.application.youtube.fragment.YouTubePopulerFragment;
import com.channel.engine.app.BaseActivity;
import com.channel.engine.widget.Status;
import com.channel.engine.widget.StatusView;
import com.channel.engine.widget.SearchBox;
import com.channel.engine.widget.SearchBox.MenuListener;
import com.channel.engine.widget.SearchBox.SearchListener;
import com.channel.engine.widget.SearchResult;

import java.util.ArrayList;
public class ApplicationActivity extends BaseActivity {
    
    public static String TAG = ApplicationActivity.class.getSimpleName();
    
    public static void start(final Context c) {

        Intent mApplication = new Intent(c, ApplicationActivity.class);
        c.startActivity(mApplication);
    }
    
    private StatusView statusView;
    private Boolean isSearch;
	private SearchBox search;
    private final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();
    IntentFilter filter = new IntentFilter(ACTION);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        search = (SearchBox) findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);
        for(int x = 0; x < 10; x++){
            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.ic_history));
            search.addSearchable(option);
        }
        search.setMenuListener(new MenuListener(){

                @Override
                public void onMenuClick() {
                    //Hamburger has been clicked
                    //Toast.makeText(MainActivity.this, "Menu click", Toast.LENGTH_LONG).show();              
                }

            });
        search.setThumbnails(YouTubeInfo.getChannelThumbnails());
        search.setSearchListener(new SearchListener(){

                @Override
                public void onSearchOpened() {
                    //Use this to tint the screen
                }

                @Override
                public void onSearchClosed() {
                    //Use this to un-tint the screen
                }

                @Override
                public void onSearchTermChanged(String term) {
                    //React to the search term changing
                    //Called after it has updated results
                }

                @Override
                public void onSearch(String searchTerm) {
                    //Toast.makeText(MainActivity.this, searchTerm +" Searched", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onResultClick(SearchResult result) {
                    //React to a result being clicked
                }

                @Override
                public void onSearchCleared() {
                    //Called when the clear button is clicked
                }

            });
        search.setOverflowMenu(R.menu.menu_application);
        search.setOverflowMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_about:
                            //Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                            return true;
                    }
                    return false;
                }
            });

        showFragment(new YouTubePopulerFragment());

        prepareTabDrawer();
        statusView = (StatusView) findViewById(R.id.status);
        registerReceiver(networkChangeReceiver, filter);
    }
    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
	}
    
    @Override
    protected void onResume() {
        super.onResume();
        this.registerReceiver(networkChangeReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkChangeReceiver);
    }
   
    private class NetworkChangeReceiver extends BroadcastReceiver {
        private boolean flag = false;

        @Override
        public void onReceive(final Context context, final Intent intent) {

            int status = NetworkUtil.getConnectivityStatusString(context);
            if(status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
                statusView.setStatus(Status.ERROR); 
                flag = true;
                //context.startActivity(retry);
            }
            if(status == NetworkUtil.NETWORK_STATUS_MOBILE) {
                statusView.setStatus(Status.COMPLETE); 
                flag = true;
                //context.startActivity(retry);
            }
            if(status == NetworkUtil.NETWORK_STATUS_WIFI) {
                statusView.setStatus(Status.COMPLETE); 
                flag = true;
                //context.startActivity(retry);
            }
        }
    }
}

