package com.example.mobileplayer.view.Activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mobileplayer.R;
import com.example.mobileplayer.presenter.activity.BaseActivityPresenter;
import com.example.mobileplayer.presenter.activity.SystemVideoPlayerActivityPresenter;
import com.example.mobileplayer.view.callback.SystemVideoPlayerActivityView;

public class SystemVideoPlayerActivity extends BaseActivity implements SystemVideoPlayerActivityView {

    private VideoView mVideoView;
    private Uri mUri;
    private SystemVideoPlayerActivityPresenter mPresenter = null;

    @Override
    protected BaseActivityPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void initView() {
        mVideoView = (VideoView)findViewById(R.id.video_view);
    }

    @Override
    public void initData() {
        mPresenter = new SystemVideoPlayerActivityPresenter(this, this);

        mUri = getIntent().getData();
        if(mUri != null) {
            mVideoView.setVideoURI(mUri);
        }
        mVideoView.setMediaController(new MediaController(this));
    }

    @Override
    public void setLisenter() {
        mVideoView.setOnPreparedListener(new MyOnPreparedListener());
        mVideoView.setOnErrorListener(new MyOnErrorListener());
        mVideoView.setOnCompletionListener(new MyOnCompletionListener());
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_system_video_player;
    }

    /**
     *  准备的监听
     */
    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener{

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            mVideoView.start();
        }
    }

    /**
     * 错误的监听
     */
    class MyOnErrorListener implements MediaPlayer.OnErrorListener{

        @Override
        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
            Toast.makeText(mContext,"onError",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 完成的监听
     */
    class MyOnCompletionListener implements MediaPlayer.OnCompletionListener{

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(mContext,"onCompletion",Toast.LENGTH_SHORT).show();
        }
    }
}
