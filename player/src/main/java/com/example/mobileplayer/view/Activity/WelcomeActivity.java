package com.example.mobileplayer.view.Activity;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

import com.example.mobileplayer.R;
import com.example.mobileplayer.presenter.activity.BaseActivityPresenter;
import com.example.mobileplayer.presenter.activity.WelcomeActivityPresenter;
import com.example.mobileplayer.view.callback.WelcomeActivityVeiw;

public class WelcomeActivity extends BaseActivity implements WelcomeActivityVeiw {

    private static final String TAG = WelcomeActivity.class.getSimpleName();

    private WelcomeActivityPresenter mPresenter = null;

    private Handler mHandler = new Handler();

    @Override
    public void initView() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                starMainActivity();
                Log.e(TAG, "当前线程名称==" + Thread.currentThread().getName());
            }
        },2000);
    }

    @Override
    public void initData() {
        mPresenter = new WelcomeActivityPresenter(this, this);
    }

    @Override
    public void setLisenter() {

    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_welcome;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        starMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //把所有的消息和回调多移除
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected BaseActivityPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void starMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
