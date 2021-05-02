package com.channel.application.settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.channel.application.youtube.config.Constants;

public class LocalPreference {
    public static String TAG = LocalPreference.class.getSimpleName();
    
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public LocalPreference(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("Local_Preference", Activity.MODE_PRIVATE);

    }

    public boolean saveDetals(String name, String email, String password, String age, String gender, String imgpath)
    {
        editor = preferences.edit();
        editor.putString("name",name);
        editor.putString("email",email);
        editor.putString("password",password);
        editor.putString("gender",gender);
        editor.putString("age",age);
        editor.putString("imgpath",imgpath);

        return editor.commit();
    }

    public String getName()
    {
        return preferences.getString("name","0");
    }
    public String getEmail()
    {
        return preferences.getString("email","0");
    }
    public String getPassword()
    {
        return preferences.getString("password","0");
    }
    public String getAge()
    {
        return preferences.getString("age","0");
    }
    public String getGender()
    {
        return preferences.getString("gender","0");
    }
    public String getImgPath()
    {
        return preferences.getString("imgpath","0");
    }

    public boolean setLoginStatus(boolean status)
    {
        editor = preferences.edit();
        editor.putBoolean("status",status);
        return editor.commit();
    }

    public boolean getLoginStatus()
    {
        return preferences.getBoolean("status",false);
    }


    public boolean setChannelId(String id){
        editor = preferences.edit();
        editor.putString("channelID",id);
        return editor.commit();
    }

    public String getChannelID()
    {
        return preferences.getString("channelID",Constants.CHANNEL_ID);
    }
}

