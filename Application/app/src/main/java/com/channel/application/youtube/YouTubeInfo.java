package com.channel.application.youtube;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import com.channel.application.youtube.config.YouTubeData;

public class YouTubeInfo {
    
    public static String TAG = YouTubeInfo.class.getSimpleName();
    private static String mFileName ="/youtube_initialise.json";
    public YouTubeInfo()
    {
        
    }
    public void initialise(YouTube video)
    {
        try
        {
            JSONObject json = new JSONObject();
            json.put("title", video.getTitle());
            json.put("thumbnails", video.getThumbnailUrl());
            json.put("videoId", video.getVideoID());
            json.put("description", video.getDescription());
            json.put("publishedAt", video.getPublished());
            json.put("update", video.getUpdater());
            
            File file = new File(YouTubeData.FOLDER + mFileName);
            //file.getParentFile().mkdirs();
            FileUtils.writeStringToFile(file, json.toString());
        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }
    }
    
    public static String getChannelTitle()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("title");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
	}
    
    public static String getChannelThumbnails()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("thumbnails");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
	}
    
    public static String getChannelDescription()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("description");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
	}
    
    public static String getChannelPublished()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("publishedAt");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
	}
    
    public static String getChannelVideos()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("videoId");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
	}
}
