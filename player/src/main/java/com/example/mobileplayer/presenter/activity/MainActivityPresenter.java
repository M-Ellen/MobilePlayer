package com.example.mobileplayer.presenter.activity;

import android.content.Context;

import com.example.mobileplayer.view.callback.MainActivityView;

/**
 * Created by pzm on 2017/3/23.
 */

public class MainActivityPresenter extends BaseActivityPresenter{
    private Context mContext = null;
    private MainActivityView mVeiw = null;

    public MainActivityPresenter(Context context, MainActivityView veiw) {
        mContext = context;
        mVeiw = veiw;
    }
}
