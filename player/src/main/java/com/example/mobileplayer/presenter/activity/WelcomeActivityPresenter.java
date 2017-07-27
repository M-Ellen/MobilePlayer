package com.example.mobileplayer.presenter.activity;

import com.example.mobileplayer.presenter.activity.BaseActivityPresenter;
import com.example.mobileplayer.view.callback.WelcomeActivityVeiw;

/**
 * Created by pengzhimao on 2017/3/23.
 */

public class WelcomeActivityPresenter extends BaseActivityPresenter {

    private WelcomeActivityVeiw mWelcomeActivityVeiw;

    public WelcomeActivityPresenter(WelcomeActivityVeiw welcomeActivityVeiw) {
        this.mWelcomeActivityVeiw = welcomeActivityVeiw;
    }
}
