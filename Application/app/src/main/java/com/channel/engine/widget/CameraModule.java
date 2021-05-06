package com.channel.engine.widget;

import android.content.Context;
import android.content.Intent;

import com.channel.engine.widget.model.Config;

/**
 * Created by hoanglam on 8/18/17.
 */

public interface CameraModule {
    Intent getCameraIntent(Context context, Config config);

    void getImage(Context context, Intent intent, OnImageReadyListener imageReadyListener);
}
