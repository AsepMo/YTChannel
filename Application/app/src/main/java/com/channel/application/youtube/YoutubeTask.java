package com.channel.application.youtube;

import com.channel.application.ApplicationActivity;
import com.channel.application.ApplicationTasksActivity;
import com.channel.application.youtube.config.YouTubeApi;
import com.channel.application.youtube.config.YouTubeData;
import com.channel.application.youtube.config.YouTubeDataBase;

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

public class YoutubeTask extends AsyncTask<Void, Void, ArrayList<YouTube>>
{
	private YouTube displaylist;
	private YouTubeData storeData;
	private YouTubeDataBase mData;
	
	private ArrayList<YouTube> displaylistArray;
	//private ProgressDialog progressDialog;
	private Context mContext;
	private String playlistId;

	public YoutubeTask(Context context, String playlistId)
	{
		this.mContext = context;
		this.playlistId = playlistId;
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
        ApplicationTasksActivity.serviceProgress.setVisibility(View.VISIBLE);
	}

	@Override
	protected ArrayList<YouTube> doInBackground(Void... params)
	{
		// TODO Auto-generated method stub
		displaylistArray = new ArrayList<YouTube>();
		try
		{
			String response = getUrlString(YouTubeApi.YOUTUBE_CHANNEL_ID);

			JSONObject json = new JSONObject(response.toString());

			JSONArray jsonArray = json.getJSONArray("items");
			//YoutubeTasksActivity.serviceProgress.setMax(4);
            for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String channelTitle = jsonObject.getJSONObject("snippet").getString("title");
				String channelThumbnails = jsonObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getString("url");
				String channelDescription = jsonObject.getJSONObject("snippet").getString("description");
				String channelPublished = jsonObject.getJSONObject("snippet").getString("publishedAt");
                String channelVideoUpload = jsonObject.getJSONObject("contentDetails").getJSONObject("relatedPlaylists").getString("uploads");
                
				displaylist = new YouTube(channelTitle, channelThumbnails , channelVideoUpload, channelDescription, channelPublished, true);
				YouTubeInfo info = new YouTubeInfo();
                info.initialise(displaylist);
                mData = new YouTubeDataBase(mContext);
				//mData.insertData(title, thumbUrl , id, description, published);
				
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
        ApplicationTasksActivity Task = (ApplicationTasksActivity)mContext;
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



    private byte[] getUrlBytes(String urlSpec) throws IOException
	{
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try
		{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
			{
                throw new IOException(connection.getResponseMessage() +
									  ": with " +
									  urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0)
			{
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        }
		finally
		{
            connection.disconnect();
        }
    }

    private String getUrlString(String urlSpec) throws IOException
	{
        return new String(getUrlBytes(urlSpec));
    }

}
