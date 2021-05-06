package com.channel.application.youtube.fragment;

import com.channel.application.R;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

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
import java.util.List;
import java.util.ArrayList;

import com.channel.application.youtube.config.YouTubeApi;
import com.channel.application.youtube.config.JsonKeys;
import com.channel.application.youtube.models.VideoData;
import com.channel.application.youtube.adapters.VideoPostAdapter;
import com.channel.application.youtube.interfaces.OnItemClickListener;
import com.channel.application.utils.RecyclerItemClickListener;
import com.channel.application.utils.EndlessRecyclerOnScrollListner;

public class YouTubePopulerFragment extends Fragment {
    
    public static String TAG = YouTubePopulerFragment.class.getSimpleName();
   
    
    public YouTubePopulerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_youtube_populer, container, false);
        
        return view;  
    }

    
}
