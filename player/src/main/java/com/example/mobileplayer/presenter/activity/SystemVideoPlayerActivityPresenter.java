package com.example.mobileplayer.presenter.activity;

import android.content.Context;

import com.example.mobileplayer.view.callback.SystemVideoPlayerActivityView;

/**
 * Created by pzm on 2017/8/30
 */

public class SystemVideoPlayerActivityPresenter extends BaseActivityPresenter{
    private Context mContext = null;
    private SystemVideoPlayerActivityView mVeiw = null;

    public SystemVideoPlayerActivityPresenter(Context context, SystemVideoPlayerActivityView veiw) {
        mContext = context;
        mVeiw = veiw;
    }
}
