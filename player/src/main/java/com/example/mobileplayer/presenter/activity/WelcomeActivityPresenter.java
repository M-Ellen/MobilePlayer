package com.example.mobileplayer.presenter.activity;

import android.content.Context;

import com.example.mobileplayer.view.callback.WelcomeActivityVeiw;

/**
 * Created by pengzhimao on 2017/3/23.
 */

public class WelcomeActivityPresenter extends BaseActivityPresenter {

    private Context mContext = null;
    private WelcomeActivityVeiw mVeiw = null;

    public WelcomeActivityPresenter(Context context, WelcomeActivityVeiw welcomeActivityVeiw) {
        mContext = context;
        mVeiw = welcomeActivityVeiw;
    }
}
