package com.example.mobileplayer.presenter.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.mobileplayer.model.MediaInfo;
import com.example.mobileplayer.utils.Constants;
import com.example.mobileplayer.view.Activity.SystemVideoPlayerActivity;
import com.example.mobileplayer.view.callback.NativeVideoFragmentView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengzhimao on 2017/3/24
 */

public class NativeVideoFragmentPresenter extends BaseFragmentPresenter{

    private NativeVideoFragmentView mView;
    private Context mContext;

    public NativeVideoFragmentPresenter(Context context,NativeVideoFragmentView view) {
        this.mContext = context;
        this.mView = view;
    }

    /**
     * 获取视频的数据
     */
    public List<MediaInfo> getMediaData(){
        ArrayList<MediaInfo> mediaInfos = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] MediaInfos = {
                MediaStore.Video.Media.DISPLAY_NAME,//视频文件在sdcard的名称
                MediaStore.Video.Media.DURATION,//视频总时长
                MediaStore.Video.Media.SIZE,//视频的文件大小
                MediaStore.Video.Media.DATA,//视频的绝对地址
                MediaStore.Video.Media.ARTIST,//歌曲的演唱者
        };
        Cursor cursor = mContext.getContentResolver().query(uri, MediaInfos, null, null, null);
        if(cursor != null) {
            while (cursor.moveToNext()){
                MediaInfo mediaInfo = new MediaInfo();
                mediaInfo.setName(cursor.getString(0));
                mediaInfo.setDuration(cursor.getLong(1));
                mediaInfo.setSize(cursor.getLong(2));
                mediaInfo.setData(cursor.getString(3));
                mediaInfo.setArtist(cursor.getString(4));
                mediaInfos.add(mediaInfo);
            }
        }
        if(cursor != null) {
            cursor.close();
        }
        return mediaInfos;
    }

    public void onItemClick(int position){

//                //1.调起系统所有的播放-隐式意图
//                Intent intent = new Intent();
//                intent.setDataAndType(Uri.parse(mPresenter.getMediaData().get(position).getData()),"video/*");
//                startActivity(intent);

//                2.调用自己写的播放器-显示意图--一个播放地址
//        List<MediaInfo> mediaData = getMediaData();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.MEDIAINFOS, (Serializable) getMediaData());
        bundle.putInt(Constants.POSITION, position);
        intent.putExtras(bundle);
//        intent.setDataAndType(Uri.parse(getMediaData().get(position).getData()),"video/*");
       mView.enterToPlay(intent);

    }
}
