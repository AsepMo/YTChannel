package com.channel.application.youtube.task;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.channel.application.ApplicationActivity;
import com.channel.application.YouTubeTaskActivity;
import com.channel.application.youtube.YouTube;
import com.channel.application.youtube.config.YouTubeApi;
import com.channel.application.youtube.config.YouTubeData;
import com.channel.application.youtube.models.YouTubeChannel;

public class YouTubeChannelTask extends AsyncTask<Void, Void, ArrayList<YouTube>>
{
    private YouTube displaylist;
    private YouTubeData storeData;
    
    private ArrayList<YouTube> displaylistArray;
    //private ProgressDialog progressDialog;
    private Context mContext;
    private String channelId;

    public YouTubeChannelTask(Context context, String channelId)
    {
        this.mContext = context;
        this.channelId = channelId;
        this.storeData = new YouTubeData(mContext, YouTubeData.FILENAME);
    }

    @Override
    protected void onPreExecute()
    {
        // TODO Auto-generated method stub
        super.onPreExecute();
        //progressDialog = new ProgressDialog(mContext);
        //progressDialog.setMessage("Tunggu Sebentar");
        //progressDialog.show();
        YouTubeTaskActivity.serviceProgress.setVisibility(View.VISIBLE);
    }

    @Override
    protected ArrayList<YouTube> doInBackground(Void... params)
    {
        // TODO Auto-generated method stub
        displaylistArray = new ArrayList<YouTube>();
        try
        {
            String response = storeData.getUrlString(channelId);

            JSONObject json = new JSONObject(response.toString());

            JSONArray jsonArray = json.getJSONArray("items");
            //YoutubeTasksActivity.serviceProgress.setMax(4);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String channelTitle = jsonObject.getJSONObject("snippet").getString("title");
                String channelThumbnailDefault = jsonObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("default").getString("url");
                String channelThumbnailMedium = jsonObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url");
                String channelThumbnailHigh = jsonObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getString("url");
                String channelDescription = jsonObject.getJSONObject("snippet").getString("description");
                String channelPublished = jsonObject.getJSONObject("snippet").getString("publishedAt");
                
                String channelLikes = jsonObject.getJSONObject("contentDetails").getJSONObject("relatedPlaylists").getString("likes");
                String channelFavorites = jsonObject.getJSONObject("contentDetails").getJSONObject("relatedPlaylists").getString("favorites");
                String channelUploads = jsonObject.getJSONObject("contentDetails").getJSONObject("relatedPlaylists").getString("uploads");
               
                String viewCount = jsonObject.getJSONObject("statistics").getString("viewCount");
                String subscriberCount = jsonObject.getJSONObject("statistics").getString("subscriberCount");
                String hiddenSubscriberCount = jsonObject.getJSONObject("statistics").getString("hiddenSubscriberCount");
                String videoCount = jsonObject.getJSONObject("statistics").getString("videoCount");
                
                displaylist = new YouTube(channelTitle, channelThumbnailMedium , channelUploads, channelDescription, channelPublished, true);
                YouTubeChannel channel = new YouTubeChannel();
                channel.setChannelTitle(channelTitle);
                channel.setThumbnailDefault(channelThumbnailDefault);
                channel.setThumbnailMedium(channelThumbnailMedium);
                channel.setThumbnailHigh(channelThumbnailHigh);
                channel.setChannelDescription(channelDescription);
                channel.setPublishedAt(channelPublished);
                channel.setLikes(channelLikes);
                channel.setFavorite(channelFavorites);
                channel.setUploads(channelUploads);
                
                channel.setViewCount(viewCount);
                channel.setSubcriberCount(subscriberCount);
                channel.setHiddenSubcriberCount(hiddenSubscriberCount); 
                channel.setVideoCount(videoCount);
                
                channel.initialise(channel);
                
                displaylistArray.add(displaylist);
                //YoutubeTasksActivity.serviceProgress.setProgress(i);
                try
                {
                    //Thread.sleep(2000);
                    storeData.saveToFile(displaylistArray);
                }
                catch (JSONException | IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(ArrayList<YouTube> result)
    {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        YouTubeTaskActivity Task = (YouTubeTaskActivity)mContext;
        Task.serviceProgress.setVisibility(View.INVISIBLE);
        Task.finish();

       ApplicationActivity.start(mContext);

        YouTube mYoutube = new YouTube(mContext);
        mYoutube.sendShortMessage("YTChannel Is Ready");
        mYoutube.setVibrator(2000);
        /*if (progressDialog.isShowing())
         {
         progressDialog.dismiss();
         }*/
    }

}


