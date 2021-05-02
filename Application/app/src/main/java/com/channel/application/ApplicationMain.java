package com.channel.application;

import android.app.Application;
import android.content.Context;

public class ApplicationMain extends Application {
    
    public static String TAG = ApplicationMain.class.getSimpleName();
    private static Application mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this; 
       /* CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                      .setDefaultFontPath("fonts/Roboto-ThinItalic.ttf")
                                      .setFontAttrId(R.attr.fontPath)
                                      .addCustomViewWithSetTypeface(CustomViewWithTypefaceSupport.class)
                                      .addCustomStyle(TextField.class, R.attr.textFieldStyle)
                                      .build()); */
        
        
    }


    public static Context getContext()
    {
        return mInstance.getApplicationContext();
	}
}
