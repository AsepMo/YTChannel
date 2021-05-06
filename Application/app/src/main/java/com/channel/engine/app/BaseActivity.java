package com.channel.engine.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.channel.application.R;
import com.channel.application.AuthActivity;
import com.channel.engine.widget.TabDrawer;
import com.channel.engine.widget.model.Tab;
import com.channel.engine.widget.model.TabDrawerData;
import com.channel.engine.widget.model.TabListItem;
import com.channel.application.youtube.YouTube;
import com.channel.application.youtube.fragment.YouTubePopulerFragment;
import com.channel.application.youtube.fragment.YouTubeFavoriteFragment;
import com.channel.application.youtube.fragment.YouTubeSearchFragment;
import com.channel.application.youtube.fragment.YouTubePlaylistFragment;
import com.channel.application.youtube.fragment.YouTubeHistoryFragment;
import com.channel.application.youtube.fragment.YouTubeChannelFragment;
import com.channel.application.settings.LocalPreference;
import android.support.v4.app.FragmentManager;

public class BaseActivity extends AppCompatActivity {
    
    public static String TAG = BaseActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private Context context;
    private  Activity activity;
    private TabDrawer tabDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      
        context = this;
        activity = this;

    }

    private TabDrawerData prepareTabArray() {
        return new TabDrawerData()
            // Simple
            .setTabIconColors(
            Color.parseColor("#3199ff"),
            Color.parseColor("#ffffff"),
            Color.parseColor("#CCCCCC")
        )
            .setTabTitleSize(12)
            .setTabTitleColors(
            ContextCompat.getColor(context, R.color.tabTitle),
            ContextCompat.getColor(context, R.color.tabTitle_selected),
            Color.parseColor("#CCCCCC")
        )
            .setTabBackgroundColors(
            ContextCompat.getColor(context, R.color.tabBackground),
            ContextCompat.getColor(context, R.color.tabBackground_selected),
            Color.parseColor("#2C7DDC")
        )
            .setTabListItemTitleColors(Color.parseColor("#ffffff"))
            .setTabListItemTitleSize(16)

            .addTab(new Tab()
                    .setTitle("Menu")
                    .setIconImage(R.drawable.n_activity)
                    .addTabListItem(new TabListItem("Trending", R.drawable.ic_trending_up))
                    .addTabListItem(new TabListItem("Favorite", R.drawable.ic_favorites))
                    .addTabListItem(new TabListItem("Search", R.drawable.ic_movie_search))
                    .addTabListItem(new TabListItem("Playlist", R.drawable.ic_playlist_play))
                    .addTabListItem(new TabListItem("History", R.drawable.ic_history))
                    )

            .addTab(new Tab()
                    .setTitle("Queue")
                    .setIconImage(R.drawable.n_queue)
                    .addTabListItem(new TabListItem("Add to Queue", R.drawable.ic_playlist_plus))
                    .addTabListItem(new TabListItem("Download", R.drawable.ic_download))
                    .addTabListItem(new TabListItem("Uploads", R.drawable.ic_upload)))


            .addTab(new Tab()
                    .setTitle("Chat")
                    .setIconImage(R.drawable.n_chat)
                    // .setDrawerListColumnNumber(2)
                    .addTabListItem(new TabListItem("Friends", R.drawable.ic_face_white_24dp))
                    .addTabListItem(new TabListItem("Add Friend", R.drawable.ic_person_add_white_24dp))
                    .addTabListItem(new TabListItem("Start Group Chat", R.drawable.ic_people_white_24dp))
                    .addTabListItem(new TabListItem("Funny Moments", R.drawable.ic_sentiment_very_satisfied_white_24dp)))


            .addTab(new Tab()
                    .setTitle("Reports")
                    .setIconImage(R.drawable.n_report)
                    .addTabListItem(new TabListItem("Completed Jobs", R.drawable.ic_event_available_white_24dp))
                    .addTabListItem(new TabListItem("Cancelled Jobs", R.drawable.ic_event_busy_white_24dp))
                    .addTabListItem(new TabListItem("Customer Feedbacks", R.drawable.ic_feedback_white_24dp))
                    .addTabListItem(new TabListItem("Documents", R.drawable.ic_description_white_24dp))
                    )

            .addTab(new Tab()
                    .setTitle("Settings")
                    .setIconImage(R.drawable.n_settings)
                    .addTabListItem(new TabListItem("General", R.drawable.ic_settings_white_24dp))
                    .addTabListItem(new TabListItem("My Account", R.drawable.ic_account_box))
                    .addTabListItem(new TabListItem("Accesibility", R.drawable.ic_accessibility_white_24dp))
                    .addTabListItem(new TabListItem("Notifications", R.drawable.ic_notifications_white_24dp))
                    .addTabListItem(new TabListItem("Bookmarks", R.drawable.ic_collections_bookmark_white_24dp))
                    .addTabListItem(new TabListItem("Shared Folders", R.drawable.ic_folder_shared_white_24dp))
                    .addTabListItem(new TabListItem("Cast to TV", R.drawable.ic_cast_white_24dp))
                    .addTabListItem(new TabListItem("Other Applications", R.drawable.ic_apps_white_24dp))
                    );

        /* Custom layouts
         .setCustomTabLayoutResourceId(R.layout.item_tab)
         .setTabBackgroundColors(
         Color.parseColor("#111111"),
         Color.parseColor("#333333"),
         Color.parseColor("#000000")
         )
         .setTabTitleSize(12)
         .setTabTitleColors( Color.parseColor("#ffffff") )

         .setTabListItemTitleSize(16)
         .setTabListItemTitleColors( Color.parseColor("#ffffff") )

         .addTab( new Tab()
         .setTitle("Demo")
         .setIconImage(R.drawable.n_activity)
         .setTitleSize(14)
         .setTitleColors(
         Color.parseColor("#ffffff"),
         Color.parseColor("#ff0000"),
         Color.parseColor("#990000")
         )
         .setIconColors(
         Color.parseColor("#ffffff"),
         Color.parseColor("#ff0000"),
         Color.parseColor("#990000")
         )
         .addTabListItem( new TabListItem("Bottom/Left TabDrawer", R.drawable.ic_home_white_24dp) )
         .addTabListItem( new TabListItem("Bottom TabDrawer", R.drawable.ic_action_collapse) )
         .addTabListItem( new TabListItem("Top TabDrawer", R.drawable.ic_action_expand) )
         .addTabListItem( new TabListItem("Left TabDrawer", R.drawable.ic_action_next_item) )
         .addTabListItem( new TabListItem("Right TabDrawer", R.drawable.ic_action_previous_item) )
         )

         .addTab( new Tab()
         .setTitle("Queue")
         .setIconImage(R.drawable.n_queue)
         .setCustomTabLayoutResourceId(R.layout.item_tab2)
         .setTitleColors( Color.parseColor("#ffffff"), Color.parseColor("#00ff00") )
         .setIconColors(Color.parseColor("#ffffff"), Color.parseColor("#00ff00"))
         .setCustomDrawerLayoutResourceId(R.layout.drawer_layout1)
         .setCustomDrawerListItemLayoutResourceId(R.layout.list_item)
         .addTabListItem( new TabListItem("Add to Queue", R.drawable.ic_add_box_white_24dp ) )
         .addTabListItem( new TabListItem("Archive", R.drawable.ic_archive_white_24dp) )
         .addTabListItem( new TabListItem("Delete", R.drawable.ic_delete_forever_white_24dp) )
         )

         .addTab( new Tab()
         .setTitle("Chat")
         .setIconImage(R.drawable.n_chat)
         .setCustomTabLayoutResourceId(R.layout.item_tab3)
         .setIconColors(Color.parseColor("#8888ee"), Color.parseColor("#ffffff"))
         .setTitleColors(Color.parseColor("#8888ee"), Color.parseColor("#ffffff"))
         .setBackgroundColors( Color.parseColor("#FF4E4B76"), Color.parseColor("#FF726EA9"), Color.parseColor("#FF2F2A79") )
         .setCustomDrawerLayoutResourceId(R.layout.drawerlayout)
         .setDrawerListColumnNumber(2)
         .addTabListItem( new TabListItem("Friends", R.drawable.ic_face_white_24dp) )
         .addTabListItem( new TabListItem("Add Friend", R.drawable.ic_person_add_white_24dp) )
         .addTabListItem( new TabListItem("Start Group Chat", R.drawable.ic_people_white_24dp) )
         .addTabListItem( new TabListItem("Funny Moments", R.drawable.ic_sentiment_very_satisfied_white_24dp) )
         .addTabListItem( new TabListItem("Add Friend", R.drawable.ic_person_add_white_24dp) )
         .addTabListItem( new TabListItem("Friends", R.drawable.ic_face_white_24dp) )
         .addTabListItem( new TabListItem("Funny Moments", R.drawable.ic_sentiment_very_satisfied_white_24dp) )
         .addTabListItem( new TabListItem("Start Group Chat", R.drawable.ic_people_white_24dp) )
         )

         .addTab( new Tab()
         .setTitle("Reports")
         .setIconImage(R.drawable.n_report)
         .setTitleSize(10)
         .setCustomTabLayoutResourceId(R.layout.item_tab4)
         .setBackgroundColors(Color.parseColor("#003366"), Color.parseColor("#336699"), Color.parseColor("#6699ff"))
         .addTabListItem( new TabListItem("Completed Jobs", R.drawable.ic_event_available_white_24dp) )
         .addTabListItem( new TabListItem("Cancelled Jobs", R.drawable.ic_event_busy_white_24dp) )
         .addTabListItem( new TabListItem("Customer Feedbacks", R.drawable.ic_feedback_white_24dp) )
         .addTabListItem( new TabListItem("Documents", R.drawable.ic_description_white_24dp) )
         )

         .addTab( new Tab()
         .setTitle(".more.")
         .forceDefaultLayout()
         .setBackgroundColors(Color.parseColor("#990066"), Color.parseColor("#660099"), Color.parseColor("#6633ff"))
         .setCustomDrawerLayoutResourceId(R.layout.drawerlayout_with_gridview)
         .setCustomDrawerGridViewId(R.id.drawer_gridview)
         .setCustomDrawerListItemLayoutResourceId(R.layout.list_item2)
         .setDrawerListColumnNumber(4)
         .addTabListItem( new TabListItem(R.drawable.ic_settings_white_24dp) )
         .addTabListItem( new TabListItem(R.drawable.ic_lock_white_24dp) )
         .addTabListItem( new TabListItem(R.drawable.ic_accessibility_white_24dp) )
         .addTabListItem( new TabListItem(R.drawable.ic_notifications_white_24dp) )
         .addTabListItem( new TabListItem(R.drawable.ic_collections_bookmark_white_24dp) )
         .addTabListItem( new TabListItem(R.drawable.ic_folder_shared_white_24dp) )
         .addTabListItem( new TabListItem(R.drawable.ic_cast_white_24dp) )
         .addTabListItem( new TabListItem(R.drawable.ic_apps_white_24dp) )
         );
         */
    }

    public void showFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commitAllowingStateLoss();
    }

    public void prepareToolbar()
    {
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    public void prepareTabDrawer() { prepareTabDrawer(false); }

    public void prepareTabDrawer(boolean additional) {
        TabDrawerData tabDrawerDataTemp = prepareTabArray();

        // Clone 3 tabs to the end to fill space when it is Left or Right TabDrawer
        if (additional) {
            TabDrawerData tabDrawerDataTemp2 = prepareTabArray();
            tabDrawerDataTemp.addTab(tabDrawerDataTemp2.getTab(3).setTitle("Add 1"));
            tabDrawerDataTemp.addTab(tabDrawerDataTemp2.getTab(2).setTitle("Add 2"));
            tabDrawerDataTemp.addTab(tabDrawerDataTemp2.getTab(1).setTitle("Add 3"));
        }

        final TabDrawerData tabDrawerData = tabDrawerDataTemp;

        tabDrawer = new TabDrawer(context, activity, R.id.tabDrawer, tabDrawerData) {
            @Override
            public void onTabDrawerClicked(int tabPosition, int itemPosition) {
                super.onTabDrawerClicked(tabPosition, itemPosition);

                String text = tabDrawerData.getTab(tabPosition).getTitle();
                if (text == null) text = "... MORE ...";

                if (tabDrawerData.getTab(tabPosition).hasItems()) {
                    if (tabDrawerData.getTab(tabPosition).getTabItemList().get(itemPosition).getTitle() != null)
                        text += " -> " + tabDrawerData.getTab(tabPosition).getTabItemList().get(itemPosition).getTitle();

                    text += " - ( " + tabPosition + ", " + itemPosition + " )";
                } else
                    text += " - ( " + tabPosition + " )";


                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
                Fragment fragment = null;
                if (tabPosition == 0) {

                    if (itemPosition == 0) {
                        fragment = new YouTubePopulerFragment();       
                    }if (itemPosition == 1) {
                        fragment = new YouTubeFavoriteFragment();
                    }if (itemPosition == 2) {
                        fragment = new YouTubeSearchFragment();
                    }if (itemPosition == 3) {
                        fragment = new YouTubePlaylistFragment();
                    } if (itemPosition == 4) {
                        fragment = new YouTubeHistoryFragment();
                    }
                    showFragment(fragment);
                }

                if (tabPosition == 1) {

                    if (itemPosition == 0) {
                        fragment = new YouTubePopulerFragment();       
                    }if (itemPosition == 1) {
                        fragment = new YouTubeFavoriteFragment();
                    }if (itemPosition == 2) {
                        fragment = new YouTubeSearchFragment();
                    }
                    showFragment(fragment);
                }

                if (tabPosition == 2) {

                    if (itemPosition == 0) {
                        fragment = new YouTubePopulerFragment();       
                    }if (itemPosition == 1) {
                        fragment = new YouTubeFavoriteFragment();
                    }if (itemPosition == 2) {
                        fragment = new YouTubeSearchFragment();
                    }if (itemPosition == 3) {
                        fragment = new YouTubePlaylistFragment();
                    } if (itemPosition == 4) {
                        fragment = new YouTubeHistoryFragment();
                    }
                    showFragment(fragment);
                }

                if (tabPosition == 3) {

                    if (itemPosition == 0) {
                        fragment = new YouTubePopulerFragment();       
                    }if (itemPosition == 1) {
                        fragment = new YouTubeFavoriteFragment();
                    }if (itemPosition == 2) {
                        fragment = new YouTubeSearchFragment();
                    }if (itemPosition == 3) {
                        fragment = new YouTubePlaylistFragment();
                    } if (itemPosition == 4) {
                        fragment = new YouTubeHistoryFragment();
                    }
                    showFragment(fragment);
                } 

                if (tabPosition == 4) {

                    if (itemPosition == 0) {
                        fragment = new YouTubePopulerFragment();       
                    }if (itemPosition == 1) {
                        fragment = new YouTubeChannelFragment();
                    }if (itemPosition == 2) {                 
                        AuthActivity.start(activity);
                    }if (itemPosition == 3) {
                        fragment = new YouTubePlaylistFragment();
                    }if (itemPosition == 4) {
                        fragment = new YouTubeHistoryFragment();
                    }if (itemPosition == 5) {
                        fragment = new YouTubeHistoryFragment();
                    }
                    if (itemPosition == 6) {
                        fragment = new YouTubeHistoryFragment();
                    }
                    if (itemPosition == 7) {
                        fragment = new YouTubeHistoryFragment();
                    }
                    showFragment(fragment);
                } 
                 
            }


            /* for even more customization
             @Override
             public void setUnselectedTabView(RelativeLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition) {
             super.setUnselectedTabView(tabLayout, iconView, titleView, tabPosition);

             if (tabPosition == 1)
             tabLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_bg2));
             }

             @Override
             public void setSelectedTabView(RelativeLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition) {
             super.setSelectedTabView(tabLayout, iconView, titleView, tabPosition);

             if (tabPosition == 1) {
             tabLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_bg1));
             }
             }

             @Override
             public void setInactiveSelectedTabView(RelativeLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition) {
             super.setInactiveSelectedTabView(tabLayout, iconView, titleView, tabPosition);

             if (tabPosition == 1)
             tabLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_bg2));
             }

             @Override
             public void setUnselectedListItemView(int tabPosition, int itemPosition, View view, ImageView iconView, TextView titleView) {
             super.setUnselectedListItemView(tabPosition, itemPosition, view, iconView, titleView);

             }

             @Override
             public void setSelectedListItemView(int tabPosition, int itemPosition, View view, ImageView iconView, TextView titleView) {
             super.setSelectedListItemView(tabPosition, itemPosition, view, iconView, titleView);

             if (tabPosition == 1) {
             view.setBackgroundColor(Color.parseColor("#44000000"));

             TextView leftArrow = (TextView) view.findViewById(R.id.listitem_selected_left);
             TextView rightArrow = (TextView) view.findViewById(R.id.listitem_selected_right);

             leftArrow.setVisibility(View.VISIBLE);
             rightArrow.setVisibility(View.VISIBLE);

             }
             else if (tabPosition == 4) {
             view.setBackgroundColor(Color.parseColor("#ffffff"));
             iconView.setColorFilter(Color.parseColor("#ff0000"));
             }
             }
             */
        };

        tabDrawer.initialize();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content_frame);
        if (fragment == null && tabDrawer.isDrawerOpen()){
            tabDrawer.closeDrawer();
            fm.beginTransaction().remove(fragment).commitAllowingStateLoss();
        
        }else{
            super.onBackPressed();
        }
    }
    
}
