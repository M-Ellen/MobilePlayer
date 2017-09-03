package com.example.mobileplayer.view.callback;


import android.net.Uri;

/**
 * Created by pengzhimao on 2017/3/23.
 */

public interface SystemVideoPlayerActivityView {

    void setVideoName(String name);

    void setVideoURI(Uri uri);

    void updateBattery(int imgId);

    void showToast(String msg, int time);

    void setNextEnabled(boolean isEnabled);

    void setPreEnabled(boolean isEnabled);
}
