package com.channel.application;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnFullscreenListener;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubeInitializationResult;

import com.channel.application.youtube.config.YouTubeApi;
import com.channel.application.youtube.adapters.VideoPostAdapter;
import com.channel.application.youtube.models.YoutubeDataModel;
import com.channel.application.youtube.interfaces.OnItemClickListener;

public class MenuActivity extends Activity implements View.OnClickListener, OnFullscreenListener {


    private static String GOOGLE_YOUTUBE_API_KEY = "AIzaSyAYgHbHDXV1x-wSdJPqdPiwq-2GgdWEqWk";//here you should use your api key for testing purpose you can use this api also
    private static String PLAYLIST_ID = "UU7V6hW6xqPAiUfataAZZtWA";//here you should use your playlist id for testing purpose you can use this api also
    private static String CHANNLE_GET_URL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + PLAYLIST_ID + "&maxResults=20&key=" + GOOGLE_YOUTUBE_API_KEY + "";


    private RecyclerView mList_videos = null;
    private VideoFragment videoFragment; 
    private VideoPostAdapter adapter = null;
    private ArrayList<YoutubeDataModel> mListData;

    private boolean isFullscreen;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_player);
        videoFragment = (VideoFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment); 

        mList_videos = (RecyclerView)findViewById(R.id.mList_videos);
        findViewById(R.id.play_video).setOnClickListener(this);
        findViewById(R.id.pause_video).setOnClickListener(this);
        ImageButton download = (ImageButton)findViewById(R.id.play_time);
        download.setOnClickListener(this);
        
        mListData = new ArrayList<YoutubeDataModel>();
        initList(mListData);
        
        new RequestYoutubeAPI().execute();
    }


    @Override
    public void onClick(View v) {
        
        switch (v.getId()) {
            case R.id.play_video:
                
                break;
            case R.id.pause_video:
                
                break;
            case R.id.play_time:
                
                VideoFragment vFragment = (VideoFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
                vFragment.getDownload();
                break;
        }
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        layout();
    }

    @Override
    public void onFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;

        layout();
    }


    /**
     * Sets up the layout programatically for the three different states. Portrait, landscape or
     * fullscreen+landscape. This has to be done programmatically because we handle the orientation
     * changes ourselves in order to get fluent fullscreen transitions, so the xml layout resources
     * do not get reloaded.
     */
    private void layout() {
        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        if (isPortrait) {

        }

        if (isFullscreen) {
            getActionBar().hide();
        } else {
            getActionBar().show();
        }
    }
    
    public static final class VideoFragment extends YouTubePlayerFragment implements YouTubePlayer.OnInitializedListener {

        private YouTubePlayer player;
        private String videoId;
        private static final int RECOVERY_DIALOG_REQUEST = 1;

        public static VideoFragment newInstance() {
            return new VideoFragment();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            initialize(YouTubeApi.DEVELOPER_API_KEY, this);
        }

        @Override
        public void onDestroy() {
            if (player != null) {
                player.release();
            }
            super.onDestroy();
        }

        public void setVideoId(String videoId) {
            if (videoId != null && !videoId.equals(this.videoId)) {
                this.videoId = videoId;
                if (player != null) {
                    player.cueVideo(videoId);
                }
            }
        }

        public void pause() {
            if (player != null) {
                player.pause();
            }
        }

        public void getDownload()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://y2mate.com/youtube/");
            stringBuilder.append(videoId);
            stringBuilder.append("/?source=android-app");
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
            startActivity(intent);
        }
        
        @Override
        public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean restored) {
            this.player = player;
            player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
            player.setOnFullscreenListener((MenuActivity) getActivity());
            if (!restored && videoId != null) {
                player.cueVideo(videoId);
            }
        }

        @Override
        public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
            if (errorReason.isUserRecoverableError()) {
                errorReason.getErrorDialog(getActivity(), RECOVERY_DIALOG_REQUEST).show();
            } else {
                String errorMessage = String.format(getString(R.string.error_player), errorReason.toString());
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == RECOVERY_DIALOG_REQUEST) {
                // Retry initialization if user performed a recovery action
                getYouTubePlayerProvider().initialize(YouTubeApi.DEVELOPER_API_KEY, this);
            }
        }


        public YouTubePlayer.Provider getYouTubePlayerProvider() {
            return (VideoFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
        }

    }

    private void initList(ArrayList<YoutubeDataModel> mListData) {
        mList_videos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VideoPostAdapter(this, mListData, new OnItemClickListener() {
                @Override
                public void onItemClick(YoutubeDataModel item) {
                    YoutubeDataModel youtubeDataModel = item;
                    // Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    //intent.putExtra(YoutubeDataModel.class.toString(), youtubeDataModel);
                    // startActivity(intent);

                    VideoFragment videoFragment = (VideoFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
                    videoFragment.setVideoId(youtubeDataModel.getVideo_id());

                }
            });
        mList_videos.setAdapter(adapter);

    }


    //create an asynctask to get all the data from youtube
    private class RequestYoutubeAPI extends AsyncTask<Void, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(CHANNLE_GET_URL);
            Log.e("URL", CHANNLE_GET_URL);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                String json = EntityUtils.toString(httpEntity);
                return json;
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("response", jsonObject.toString());
                    mListData = parseVideoListFromResponse(jsonObject);
                    initList(mListData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<YoutubeDataModel> parseVideoListFromResponse(JSONObject jsonObject) {
        ArrayList<YoutubeDataModel> mList = new ArrayList<>();

        if (jsonObject.has("items")) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    if (json.has("kind")) {
                        if (json.getString("kind").equals("youtube#playlistItem")) {
                            YoutubeDataModel youtubeObject = new YoutubeDataModel();
                            JSONObject jsonSnippet = json.getJSONObject("snippet");
                            String vedio_id = "";
                            if (jsonSnippet.has("resourceId")) {
                                JSONObject jsonResource = jsonSnippet.getJSONObject("resourceId");
                                vedio_id = jsonResource.getString("videoId");

                            }
                            String title = jsonSnippet.getString("title");
                            String description = jsonSnippet.getString("description");
                            String publishedAt = jsonSnippet.getString("publishedAt");
                            String thumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("high").getString("url");

                            youtubeObject.setTitle(title);
                            youtubeObject.setDescription(description);
                            youtubeObject.setPublishedAt(publishedAt);
                            youtubeObject.setThumbnail(thumbnail);
                            youtubeObject.setVideo_id(vedio_id);
                            mList.add(youtubeObject);
                            VideoFragment videoFragment = (VideoFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
                            videoFragment.setVideoId(mList.get(0).getVideo_id());
                        }
                    }


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return mList;

    }

}
