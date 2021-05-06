package com.channel.engine.widget;

import com.channel.engine.widget.model.Image;
import com.channel.engine.widget.common.MvpView;

import java.util.List;

/**
 * Created by hoanglam on 8/22/17.
 */

public interface CameraView extends MvpView {

    void finishPickImages(List<Image> images);
}
