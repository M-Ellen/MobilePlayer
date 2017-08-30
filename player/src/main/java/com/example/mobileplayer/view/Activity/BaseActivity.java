package com.example.mobileplayer.view.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.mobileplayer.presenter.activity.BaseActivityPresenter;

/**
 * Created by pengzhimao on 2017/3/23
 */
public abstract class BaseActivity extends Activity{

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutViewId());
        mContext = getApplicationContext();
        initView();
        initData();
        setLisenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getPresenter().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onDestroy();
    }


    protected abstract BaseActivityPresenter getPresenter();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void setLisenter();
    protected abstract int getLayoutViewId();
}
