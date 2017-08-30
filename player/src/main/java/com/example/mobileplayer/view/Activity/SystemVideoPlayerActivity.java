package com.example.mobileplayer.view.Activity;

import android.media.MediaPlayer;
import android.net.Uri;
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
import com.example.mobileplayer.view.callback.SystemVideoPlayerActivityView;

public class SystemVideoPlayerActivity extends BaseActivity implements SystemVideoPlayerActivityView, View.OnClickListener {


    private LinearLayout llTop;
    private TextView tvName;
    private ImageView ivBattery;
    private TextView tvSystemTime;
    private Button btnVoice;
    private SeekBar seekbarVoice;
    private Button btnSwichPlayer;
    private LinearLayout llBottom;
    private TextView tvCurrentTime;
    private SeekBar seekbarVideo;
    private TextView tvDuration;
    private Button btnExit;
    private Button btnVideoPre;
    private Button btnVideoStartPause;
    private Button btnVideoNext;
    private Button btnVideoSiwchScreen;

    @Override
    public void initView() {

        llTop = (LinearLayout)findViewById( R.id.ll_top );
        tvName = (TextView)findViewById( R.id.tv_name );
        ivBattery = (ImageView)findViewById( R.id.iv_battery );
        tvSystemTime = (TextView)findViewById( R.id.tv_system_time );
        btnVoice = (Button)findViewById( R.id.btn_voice );
        seekbarVoice = (SeekBar)findViewById( R.id.seekbar_voice );
        btnSwichPlayer = (Button)findViewById( R.id.btn_swich_player );
        llBottom = (LinearLayout)findViewById( R.id.ll_bottom );
        tvCurrentTime = (TextView)findViewById( R.id.tv_current_time );
        seekbarVideo = (SeekBar)findViewById( R.id.seekbar_video );
        tvDuration = (TextView)findViewById( R.id.tv_duration );
        btnExit = (Button)findViewById( R.id.btn_exit );
        btnVideoPre = (Button)findViewById( R.id.btn_video_pre );
        btnVideoStartPause = (Button)findViewById( R.id.btn_video_start_pause );
        btnVideoNext = (Button)findViewById( R.id.btn_video_next );
        btnVideoSiwchScreen = (Button)findViewById( R.id.btn_video_siwch_screen );
        mVideoView = (VideoView)findViewById(R.id.video_view);

        btnVoice.setOnClickListener(this);
        btnSwichPlayer.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnVideoPre.setOnClickListener(this);
        btnVideoStartPause.setOnClickListener(this);
        btnVideoNext.setOnClickListener(this);
        btnVideoSiwchScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ( v == btnVoice ) {
            // Handle clicks for btnVoice
        } else if ( v == btnSwichPlayer ) {
            // Handle clicks for btnSwichPlayer
        } else if ( v == btnExit ) {
            // Handle clicks for btnExit
        } else if ( v == btnVideoPre ) {
            // Handle clicks for btnVideoPre
        } else if ( v == btnVideoStartPause ) {
            // Handle clicks for btnVideoStartPause
        } else if ( v == btnVideoNext ) {
            // Handle clicks for btnVideoNext
        } else if ( v == btnVideoSiwchScreen ) {
            // Handle clicks for btnVideoSiwchScreen
        }
    }


    private VideoView mVideoView;
    private Uri mUri;
    private SystemVideoPlayerActivityPresenter mPresenter = null;

    @Override
    protected BaseActivityPresenter getPresenter() {
        return mPresenter;
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
