package com.channel.application.youtube.models;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import com.channel.application.youtube.config.YouTubeData;

public class YouTubeChannel {

    public static String TAG = YouTubeChannel.class.getSimpleName();
    private static String mFileName ="/youtube_initialise.json";
    private String mTitle;
    private String mThumbnailDefault;
    private String mThumbnailMedium;
    private String mThumbnailHigh;
    private String mDescription;
    private String mPublishedAt;
    
    private String mLikes;
    private String mFavorite;
    private String mUpload;
    
    private String mViewCount;
    private String mSubscriberCount;
    private String mHisdenSubscriberCount;
    private String mVideoCount;
    
    public YouTubeChannel()
    {

    }
    
    public void setChannelTitle(String mTitle)
    {
        this.mTitle = mTitle;
    }
    
    public String getTitle()
    {
        return mTitle;
    }

    public void setThumbnailDefault(String mThumbnail)
    {
        this.mThumbnailDefault = mThumbnail;
    }
    
    public String getThumbnailDefault()
    {
        return mThumbnailDefault;
    }
    
    public void setThumbnailMedium(String mThumbnail)
    {
        this.mThumbnailMedium = mThumbnail;
    }

    public String getThumbnailMedium()
    {
        return mThumbnailMedium;
    }
    
    public void setThumbnailHigh(String mThumbnail)
    {
        this.mThumbnailHigh = mThumbnail;
    }

    public String getThumbnailHigh()
    {
        return mThumbnailHigh;
    }
    
    public void setChannelDescription(String mDescription)
    {
        this.mDescription = mDescription;
    }

    public String getDescription()
    {
        return mDescription;
    }

    public void setPublishedAt(String mPublished)
    {
        this.mPublishedAt = mPublished;
    }
    
    public String getPublishedAt()
    {
        return mPublishedAt;
    }
    
    public void setLikes(String mLikes)
    {
        this.mLikes = mLikes;
    }

    public String getLikes()
    {
        return mLikes;
    }
    
    public void setDisLikes(String mLikes)
    {
        this.mLikes = mLikes;
    }

    public String getDisLikes()
    {
        return mLikes;
    }
    
    public void setFavorite(String mFavorite)
    {
        this.mFavorite = mLikes;
    }

    public String getFavorite()
    {
        return mFavorite;
    }
   
    public void setUploads(String mVideoId)
    {
        this.mUpload = mVideoId;
    }

    public String getUploads()
    {
        return mUpload;
    }
    
    public void setViewCount(String mViewCount)
    {
        this.mViewCount = mViewCount;
    }

    public String getViewCount()
    {
        return mViewCount;
    }
    
    public void setSubcriberCount(String mSubscriberCount)
    {
        this.mSubscriberCount = mSubscriberCount;
    }

    public String getSubcriberCount()
    {
        return mSubscriberCount;
    }
    
    public void setHiddenSubcriberCount(String mHisdenSubscriberCount)
    {
        this.mHisdenSubscriberCount = mHisdenSubscriberCount;
    }

    public String getHiddenSubcriberCount()
    {
        return mHisdenSubscriberCount;
    }
    
    
    public void setVideoCount(String mVideoCount)
    {
        this.mVideoCount = mVideoCount;
    }

    public String getVideoCount()
    {
        return mVideoCount;
    }
    
    public void initialise(YouTubeChannel video)
    {
        try
        {
            JSONObject json = new JSONObject();
            json.put("title", video.getTitle());
            json.put("default", video.getThumbnailDefault());
            json.put("medium", video.getThumbnailMedium());
            json.put("high", video.getThumbnailHigh());
            json.put("description", video.getDescription());
            json.put("publishedAt", video.getPublishedAt());
            json.put("likes", video.getLikes());
            json.put("favorite", video.getFavorite());
            json.put("default", video.getThumbnailDefault());
            json.put("uploads", video.getUploads());
            json.put("viewCount", video.getViewCount());
            json.put("subscriberCount", video.getSubcriberCount());
            json.put("videoCount", video.getVideoCount());
            
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

    public static String getChannelThumbnailDefault()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("default");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
    }
    
    public static String getChannelThumbnailMedium()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("medium");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
    }
    
    public static String getChannelThumbnailHigh()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("high");
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

    
    public static String getChannelLikes()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("likes");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
    }
    
    public static String getChannelFavorite()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("favorite");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
    }
    
    public static String getChannelUploads()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("uploads");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
    }
    
    public static String getChannelViewCount()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("viewCount");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
    }
    
    public static String getChannelSubscriberCount()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("subscriberCount");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
    }
    
    public static String getChannelVideoCount()
    {
        try
        {
            File infoFile = new File(YouTubeData.FOLDER + mFileName);
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("videoCount");
        }
        catch (IOException | JSONException e)
        {
            return null;
        }    
    }
    
}


