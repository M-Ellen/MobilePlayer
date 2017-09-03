
package com.example.mobileplayer.presenter.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.mobileplayer.R;
import com.example.mobileplayer.model.MediaInfo;
import com.example.mobileplayer.utils.Constants;
import com.example.mobileplayer.view.callback.SystemVideoPlayerActivityView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.R.attr.filter;
import static android.content.ContentValues.TAG;

/**
 * Created by pzm on 2017/8/30
 */

public class SystemVideoPlayerActivityPresenter extends BaseActivityPresenter{

    private Intent mIntent = null;
    private Context mContext = null;
    private SystemVideoPlayerActivityView mVeiw = null;

    private BroadcastReceiver mReceiver = null;

    private List<MediaInfo> mMediaList = null;
    private int mPosition = 0;

    public SystemVideoPlayerActivityPresenter(Context context, SystemVideoPlayerActivityView view) {
        mIntent  = ((Activity)context).getIntent();
        mContext = context;
        mVeiw = view;
        initData();
    }

    private void initData() {

        if (mMediaList == null) {
            mMediaList = new ArrayList<>();
        }
        mMediaList = (List<MediaInfo>) mIntent.getSerializableExtra(Constants.MEDIAINFOS);
        mPosition = mIntent.getIntExtra(Constants.POSITION, 0);

        registerReceiver();
        setNextOrPreBtnState();
    }

    private void registerReceiver() {
        mReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        mContext.registerReceiver(mReceiver, filter);
    }

    public Uri getVideoUri(){

        Uri uri = null;

        if (mMediaList != null && mMediaList.size() > 0) {
            uri = Uri.parse(mMediaList.get(mPosition).getData());
        }else if(mIntent.getData() != null) {
            uri = mIntent.getData();
        }else {
            mVeiw.showToast(mContext.getString(R.string.invalid_uri),0);
        }
        return uri;
    }

    public String getVideoName(){
        if(mMediaList != null && mMediaList.size() > 0) {
            return mMediaList.get(mPosition).getName();
        }
        return mContext.getString(R.string.unknown);
    }



    /**
     * 更新电量，显示图片
     * @param level 电量值
     */
    private void updateBattery(int level) {
        if(level <= 0 ) {
            mVeiw.updateBattery(R.mipmap.ic_battery_0);
        }else if(level <= 10) {
            mVeiw.updateBattery(R.mipmap.ic_battery_10);
        }else if(level <= 20) {
            mVeiw.updateBattery(R.mipmap.ic_battery_20);
        }else if(level <= 40) {
            mVeiw.updateBattery(R.mipmap.ic_battery_40);
        }else if(level <= 60) {
            mVeiw.updateBattery(R.mipmap.ic_battery_60);
        }else if(level <= 80) {
            mVeiw.updateBattery(R.mipmap.ic_battery_80);
        }else if(level <= 100) {
            mVeiw.updateBattery(R.mipmap.ic_battery_100);
        }

    }

    @Override
    public void onDestroy() {
        //注意：释放资源在super前面释放比较安全
        if(mReceiver != null) {
            mContext.unregisterReceiver(mReceiver);
            mReceiver = null;
        }

        super.onDestroy();
    }

    /**
     * 播放下一个视频
     */
    public void playNextVideo() {
        if(hasNext()) {
            mPosition++;
            mVeiw.setVideoName(getVideoName());
            mVeiw.setVideoURI(getVideoUri());
            setNextOrPreBtnState();
        }
    }

    /**
     * 播放上一个视频
     */
    public void playPreVideo() {
        if(hasPrevious()) {
            mPosition--;
            mVeiw.setVideoName(getVideoName());
            mVeiw.setVideoURI(getVideoUri());
            setNextOrPreBtnState();
        }
    }

    /**
     * 设置按钮的状态
     */
    public void setNextOrPreBtnState(){
        if(hasNext()) {
            mVeiw.setNextEnabled(true);
        }else {
            mVeiw.setNextEnabled(false);
        }

        if(hasPrevious()) {
            mVeiw.setPreEnabled(true);
        }else {
            mVeiw.setPreEnabled(false);
        }

    }

    private boolean hasNext(){
        if(mMediaList != null && mMediaList.size() > 0) {
            return (mPosition + 1) < mMediaList.size();
        }
        return false;
    }

    private boolean hasPrevious(){
        if(mMediaList != null && mMediaList.size() > 0) {
            return (mPosition - 1) >= 0;
        }
        return false;
    }
    /**
     * 电量的广播
     */
    class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("debug", "onReceive: "+action);

            if(Intent.ACTION_BATTERY_CHANGED == action) {
                int level = intent.getIntExtra("level", 0);
                updateBattery(level);
            }


        }
    }

    public void onPrepared() {

    }
}
