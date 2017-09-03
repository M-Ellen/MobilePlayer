package com.example.mobileplayer.view.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mobileplayer.R;
import com.example.mobileplayer.presenter.activity.BaseActivityPresenter;
import com.example.mobileplayer.presenter.activity.SystemVideoPlayerActivityPresenter;
import com.example.mobileplayer.utils.Utils;
import com.example.mobileplayer.view.callback.SystemVideoPlayerActivityView;

import static android.content.ContentValues.TAG;

public class SystemVideoPlayerActivity extends BaseActivity implements SystemVideoPlayerActivityView, View.OnClickListener {


    public static final String TAG = SystemVideoPlayerActivity.class.getSimpleName();
    public static final int UPDATE_VIDEO_POGESS = 0;

    private LinearLayout mLoyoutTop;
    private TextView mTvName;
    private ImageView mImgBattery;
    private TextView mTvSystemTime;
    private Button mBtnVoice;
    private SeekBar mSeekbarVoice;
    private Button mBtnSwichPlayer;
    private LinearLayout mLayoutBottom;
    private TextView mTvCurrentTime;
    private SeekBar mSeekbarVideo;
    private TextView mTvDuration;
    private Button mBtnExit;
    private Button mBtnVideoPre;
    private Button mBtnVideoStartPause;
    private Button mBtnVideoNext;
    private Button mBtnVideoSiwchScreen;
    private VideoView mVideoView;

    private SystemVideoPlayerActivityPresenter mPresenter = null;

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what) {
                case  UPDATE_VIDEO_POGESS:

                    if(mVideoView.isPlaying()) {
                        Log.d(TAG, "handleMessage: "+ msg.what);
                        setCurrentTime(mVideoView.getCurrentPosition());
                        mSeekbarVideo.setProgress(mVideoView.getCurrentPosition());
                        //每个1秒发一次
                        sendEmptyMessageDelayed(UPDATE_VIDEO_POGESS, 1000);
                    }
                    mTvSystemTime.setText(Utils.getSystemTime());

                    break;

                default:

                    break;
            }

        }
    };

    @Override
    public void initView() {

        mLoyoutTop = (LinearLayout)findViewById( R.id.ll_top );
        mTvName = (TextView)findViewById( R.id.tv_name );
        mImgBattery = (ImageView)findViewById( R.id.iv_battery );
        mTvSystemTime = (TextView)findViewById( R.id.tv_system_time );
        mBtnVoice = (Button)findViewById( R.id.btn_voice );
        mSeekbarVoice = (SeekBar)findViewById( R.id.seekbar_voice );
        mBtnSwichPlayer = (Button)findViewById( R.id.btn_swich_player );
        mLayoutBottom = (LinearLayout)findViewById( R.id.ll_bottom );
        mTvCurrentTime = (TextView)findViewById( R.id.tv_current_time );
        mSeekbarVideo = (SeekBar)findViewById( R.id.seekbar_video );
        mTvDuration = (TextView)findViewById( R.id.tv_duration );
        mBtnExit = (Button)findViewById( R.id.btn_exit );
        mBtnVideoPre = (Button)findViewById( R.id.btn_video_pre );
        mBtnVideoStartPause = (Button)findViewById( R.id.btn_video_start_pause );
        mBtnVideoNext = (Button)findViewById( R.id.btn_video_next );
        mBtnVideoSiwchScreen = (Button)findViewById( R.id.btn_video_siwch_screen );
        mVideoView = (VideoView)findViewById(R.id.video_view);

        mBtnVoice.setOnClickListener(this);
        mBtnSwichPlayer.setOnClickListener(this);
        mBtnExit.setOnClickListener(this);
        mBtnVideoPre.setOnClickListener(this);
        mBtnVideoStartPause.setOnClickListener(this);
        mBtnVideoNext.setOnClickListener(this);
        mBtnVideoSiwchScreen.setOnClickListener(this);
    }


    @Override
    protected BaseActivityPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void initData() {
        mPresenter = new SystemVideoPlayerActivityPresenter(this, this);

        if(mPresenter.getVideoUri() != null) {
            mVideoView.setVideoURI(mPresenter.getVideoUri());
        }
//        mVideoView.setMediaController(new MediaController(this));
    }

    @Override
    public void setLisenter() {
        mVideoView.setOnPreparedListener(new MyOnPreparedListener());
        mVideoView.setOnErrorListener(new MyOnErrorListener());
        mVideoView.setOnCompletionListener(new MyOnCompletionListener());
        mSeekbarVideo.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener());
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_system_video_player;
    }

    @Override
    public void setVideoName(String name){
        mTvName.setText(name);
    }

    public void setDuration(int duration){
        mTvDuration.setText(Utils.stringForTime(duration));
    }

    public void setCurrentTime(int curTime){
        mTvCurrentTime.setText(Utils.stringForTime(curTime));
    }

    public boolean isPlay(){
        return mVideoView.isPlaying();
    }

    public void setPause(){
        mVideoView.pause();
    }

    public void setStart(){
        mVideoView.start();
    }

    @Override
    public void setVideoURI(Uri uri){
        mVideoView.setVideoURI(uri);
    }

    public void setPlayOrPause(boolean isPlay)
    {
        if(isPlay) {
            mVideoView.pause();
            mBtnVideoStartPause.setBackgroundResource(R.mipmap.btn_play_normal);
        }else {
            mVideoView.start();
            mBtnVideoStartPause.setBackgroundResource(R.mipmap.btn_pause_normal);
        }
    }

    public void setVideoBarMaxProgress(){
        mSeekbarVideo.setMax(mVideoView.getDuration());
    }

    public void setVideoBarProgress(){
        mSeekbarVideo.setProgress(mVideoView.getCurrentPosition());
    }

    public void updateBattery(int id){
        mImgBattery.setImageResource(id);
    }

    @Override
    public void setNextEnabled(boolean isEnabled){
        if(isEnabled) {
            mBtnVideoNext.setEnabled(true);
            mBtnVideoNext.setBackgroundResource(R.drawable.btn_video_next_selector);

        }else {
            mBtnVideoNext.setEnabled(false);
            mBtnVideoNext.setBackgroundResource(R.mipmap.btn_next_gray);

        }
    }

    @Override
    public void setPreEnabled(boolean isEnabled){
        if(isEnabled) {
            mBtnVideoPre.setEnabled(true);
            mBtnVideoPre.setBackgroundResource(R.drawable.btn_video_pre_selector);

        }else {
            mBtnVideoPre.setEnabled(false);
            mBtnVideoPre.setBackgroundResource(R.mipmap.btn_pre_gray);

        }
    }

    public void showToast(String msg, int time){
        Toast.makeText(SystemVideoPlayerActivity.this, msg, time).show();
    }


    /**
     * 按钮的监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        if ( v == mBtnVoice ) {
            // Handle clicks for btnVoice
        } else if ( v == mBtnSwichPlayer ) {
            // Handle clicks for btnSwichPlayer
        } else if ( v == mBtnExit ) {
            //退出播放
            exitActivity();
        } else if ( v == mBtnVideoPre ) {
            mPresenter.playPreVideo();
        } else if ( v == mBtnVideoStartPause ) {
            //开始、暂停播放
            setPlayOrPause(mVideoView.isPlaying());
        } else if ( v == mBtnVideoNext ) {
            mPresenter.playNextVideo();
        } else if ( v == mBtnVideoSiwchScreen ) {
            // Handle clicks for btnVideoSiwchScreen
        }
    }

//    private void playNextVideo() {
////        mVideoView.setVideoURI();
////        mVideoView.start();
//    }
//
//    private void playPreVideo() {
//
//    }

    private void exitActivity() {
        finish();
        setAnimation();
    }


    /**
     * 进度条滚动监听
     */
    class MyOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser) {
                Log.d(TAG, "onProgressChanged: "+progress);
                mVideoView.seekTo(progress);
//                mSeekbarVideo.setProgress(progress);
                setCurrentTime(mVideoView.getCurrentPosition());

            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.d(TAG, "onStartTrackingTouch: ");

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d(TAG, "onStopTrackingTouch: ");
        }
    }
    /**
     *  准备的监听
     */
    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener{

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            Log.d(TAG, "onPrepared: ");
            mVideoView.start();
//            mPresenter.onPrepared();
            setDuration(mVideoView.getDuration());
            setCurrentTime(mVideoView.getCurrentPosition());
            setVideoName(mPresenter.getVideoName());
            mSeekbarVideo.setMax(mVideoView.getDuration());
            mSeekbarVideo.setProgress(mVideoView.getCurrentPosition());
            mHandler.sendEmptyMessage(UPDATE_VIDEO_POGESS);

        }
    }

    /**
     * 错误的监听
     */
    class MyOnErrorListener implements MediaPlayer.OnErrorListener{

        @Override
        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
            Log.d(TAG, "onError: ");
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
            Log.d(TAG, "onCompletion: ");
            Toast.makeText(mContext,"onCompletion",Toast.LENGTH_SHORT).show();
        }
    }
}
