package com.channel.application.youtube.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.channel.application.R;
import com.channel.application.youtube.models.YouTubeChannel;

public class YouTubeChannelFragment extends Fragment
{
    
    public static String TAG = YouTubeChannelFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_youtube_channel, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.app_bar_layout);
        appBarLayout.setExpanded(true);

        // hiding & showing the txtPostTitle when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShow = false;
                int scrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        collapsingToolbar.setTitle("Web View");
                        isShow = true;
                    } else if (isShow) {
                        collapsingToolbar.setTitle(" ");
                        isShow = false;
                    }
                }
            });

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        toolbar.setTitle(""); 
        
        ImageView cover = (ImageView)view.findViewById(R.id.channelThumbnails);
        cover.setScaleType(ImageView.ScaleType.CENTER);   
        Glide.with(getActivity())
            .load(YouTubeChannel.getChannelThumbnailMedium())
            .apply(new RequestOptions().circleCrop().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher))
            .into(cover);

        TextView title = (TextView)view.findViewById(R.id.channelTitle);
        title.setText(YouTubeChannel.getChannelTitle());

        ImageView thumbnail = (ImageView)view.findViewById(R.id.thumbs);
        thumbnail.setScaleType(ImageView.ScaleType.CENTER);   
        Glide.with(getActivity())
            .load(YouTubeChannel.getChannelThumbnailDefault())
            .apply(new RequestOptions().circleCrop().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher))
            .into(thumbnail);

        TextView channelTitle = (TextView)view.findViewById(R.id.detailTitle);
        channelTitle.setText(YouTubeChannel.getChannelTitle());

        TextView channelPublished = (TextView)view.findViewById(R.id.detailUploaderName);
        channelPublished.setText(YouTubeChannel.getChannelSubscriberCount() + "  Subscriber");

        TextView viewCount = (TextView)view.findViewById(R.id.detailViews);
        viewCount.setText(YouTubeChannel.getChannelViewCount() + "  views");

        TextView likeCount = (TextView)view.findViewById(R.id.detailLikes);
        if (YouTubeChannel.getChannelLikes() == null & YouTubeChannel.getChannelLikes().equals(" "))
        {            
            likeCount.setText(YouTubeChannel.getChannelLikes());
        } else {
            likeCount.setText("0");
        }

        TextView subcriberCount = (TextView)view.findViewById(R.id.detailDislike);
        if (YouTubeChannel.getChannelFavorite() == null & YouTubeChannel.getChannelFavorite().equals(" ")) 
        {
            subcriberCount.setText(YouTubeChannel.getChannelFavorite());
            
        } else 
        {
            subcriberCount.setText("0");
        }
        
        TextView desc = (TextView)view.findViewById(R.id.channelDescription);
        desc.setText(YouTubeChannel.getChannelDescription());

        view.findViewById(R.id.channel).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                   // startActivity(new Intent(getActivity(), PlaylistActivity.class));
                }
            });
            
        view.findViewById(R.id.history).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(getActivity(), TimeLineActivity.class));
                }
            });
            
        view.findViewById(R.id.tonton_nanti).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(getActivity(), PlaylistActivity.class));
                }
            });
            
        view.findViewById(R.id.langganan).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(getActivity(), PlaylistActivity.class));
                }
            });     
    }
    
}

