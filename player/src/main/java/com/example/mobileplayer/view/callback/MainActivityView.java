package com.example.mobileplayer.view.callback;


import com.example.mobileplayer.view.fragment.BaseFragment;

/**
 * Created by pengzhimao on 2017/3/23.
 */

public interface MainActivityView {

    void initFragment();
    BaseFragment getFragment();
    void switchFragment(BaseFragment baseFragment);
}
