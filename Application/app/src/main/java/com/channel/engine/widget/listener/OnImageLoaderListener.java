package com.channel.engine.widget.listener;

import com.channel.engine.widget.model.Folder;
import com.channel.engine.widget.model.Image;

import java.util.List;

/**
 * Created by hoanglam on 8/17/17.
 */

public interface OnImageLoaderListener {
    void onImageLoaded(List<Image> images, List<Folder> folders);

    void onFailed(Throwable throwable);
}
