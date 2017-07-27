package com.example.mobileplayer.presenter.fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.mobileplayer.model.MediaInfo;
import com.example.mobileplayer.view.callback.NativeVedioFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengzhimao on 2017/3/24.
 */

public class NativeVideoFragmentPresenter extends BaseFragmentPresenter{

    private NativeVedioFragmentView mNativeVedioFragmentView;
    private Context mContext;

    public NativeVideoFragmentPresenter(Context context,NativeVedioFragmentView nativeVedioFragmentView) {
        this.mContext = context;
        this.mNativeVedioFragmentView = nativeVedioFragmentView;
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
}
