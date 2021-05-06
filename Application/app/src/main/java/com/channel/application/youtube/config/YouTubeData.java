package com.channel.application.youtube.config;

import com.channel.application.youtube.YouTube;
import com.channel.application.youtube.YouTubeFolder;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.URL;

public class YouTubeData
{

    public static String TAG = YouTubeData.class.getSimpleName();

    private static Context mContext;
    private static String mFileName;

    public String title;
    public String thumbnail_url;
    public String videoID;
    public String published;
    public boolean isUpdated;

    public static String FOLDER = YouTubeFolder.ZFOLDER_YOUTUBE_ANALYTICS;
    public static String FILENAME = "youtube_channel.json";
    public static String URL = "https://drive.google.com/uc?export=download&id=1QW12zqOIraKRCVOeriBCbB47QD0G-6By";


    public YouTubeData(Context activity)
    {
        this.mContext = activity;
    }

    public YouTubeData(Context context, String filename)
    {
        mContext = context;
        mFileName = filename;
    }

    public YouTubeData(String title, String videoId, boolean update)
    {
        this.title = title;
        this.videoID = videoId;
        this.isUpdated = update;
    }

    public static JSONArray toJSONArray(ArrayList<YouTube> items) throws JSONException
    {
        JSONArray jsonArray = new JSONArray();
        for (YouTube item : items)
        {
            JSONObject jsonObject = item.toJSON();
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    public void saveToFile(ArrayList<YouTube> items) throws JSONException, IOException
    {

        File file = new File(FOLDER, mFileName);
        file.getParentFile().mkdirs();
        try
        {
            //FileOutputStream fos = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(toJSONArray(items).toString());
            osw.write('\n');
            osw.flush();
            fos.flush();
            fos.getFD().sync();
            fos.close();

            Log.d(TAG, toJSONArray(items).toString());
        }
        catch (IOException e)
        {
            Log.e(TAG, "Exception writing to file", e);
        }
    }

    public void saveToFile(String file, ArrayList<YouTube> items) throws JSONException, IOException
    {

        try
        {
            FileOutputStream fos = mContext.openFileOutput(file, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(toJSONArray(items).toString());
            osw.write('\n');
            osw.flush();
            fos.flush();
            fos.getFD().sync();
            fos.close();

            Log.d(TAG, toJSONArray(items).toString());
        }
        catch (IOException e)
        {
            Log.e(TAG, "Exception writing to file", e);
        }
    }

    public ArrayList<YouTube> loadFromFile() throws IOException, JSONException
    {
        ArrayList<YouTube> items = new ArrayList<>();
        BufferedReader bufferedReader = null;
        FileInputStream fileInputStream = null;
        File file = new File(Environment.getExternalStorageDirectory() + "/ZFOLDER", mFileName);
        try
        {
            fileInputStream = new FileInputStream(file);
            StringBuilder builder = new StringBuilder();
            String line;
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((line = bufferedReader.readLine()) != null)
            {
                builder.append(line);
            }

            JSONArray jsonArray = (JSONArray) new JSONTokener(builder.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++)
            {
                YouTube item = new YouTube(jsonArray.getJSONObject(i));
                items.add(item);
            }


        }
        catch (FileNotFoundException fnfe)
        {
            //do nothing about it
            //file won't exist first time app is run
        }
        finally
        {
            if (bufferedReader != null)
            {
                bufferedReader.close();
            }
            if (fileInputStream != null)
            {
                fileInputStream.close();
            }

        }
        return items;
    }

    public static ArrayList<YouTube> loadFromFile(String file) throws IOException, JSONException
    {
        ArrayList<YouTube> items = new ArrayList<>();
        BufferedReader bufferedReader = null;
        FileInputStream fileInputStream = null;
        File files = new File(file);
        try
        {
            fileInputStream = new FileInputStream(files);
            StringBuilder builder = new StringBuilder();
            String line;
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((line = bufferedReader.readLine()) != null)
            {
                builder.append(line);
            }

            JSONArray jsonArray = (JSONArray) new JSONTokener(builder.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++)
            {
                YouTube item = new YouTube(jsonArray.getJSONObject(i));
                items.add(item);
            }


        }
        catch (FileNotFoundException fnfe)
        {
            //do nothing about it
            //file won't exist first time app is run
        }
        finally
        {
            if (bufferedReader != null)
            {
                bufferedReader.close();
            }
            if (fileInputStream != null)
            {
                fileInputStream.close();
            }

        }
        return items;
    }

    public static void saveData(Context c, String title, String thumbUrl, String videoId, String description, String published, boolean update) 
    {
        YouTubeData storeData = new YouTubeData(c, "youtube_recent.json");
        //mToDoItemsArrayList = getLocallyStoredData(storeRetrieveData);

        ArrayList<YouTube> items = new ArrayList<YouTube>();
        YouTube displaylist = new YouTube(title, thumbUrl , videoId, description, published, update);
        items.add(displaylist);
        try
        {
            storeData.saveToFile(items);
        }
        catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<YouTube> getLocallyStoredData(Context c)
    {
        ArrayList<YouTube> items = null;
        YouTubeData storeData = new YouTubeData(c, FILENAME);

        try
        {
            items = storeData.loadFromFile();

        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }

        if (items == null)
        {
            items = new ArrayList<>();
        }
        return items;
    }

    public static byte[] getUrlBytes(String urlSpec) throws IOException
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

    public static String getUrlString(String urlSpec) throws IOException
    {
        return new String(getUrlBytes(urlSpec));
    }
}

