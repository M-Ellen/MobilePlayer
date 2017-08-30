package com.example.mobileplayer.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobileplayer.presenter.fragment.BaseFragmentPresenter;

/**
 * Created by pzm on 2017/2/21.
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();
        mView = inflater.inflate(getLayoutViewId(),container,false);
        initView();
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intiData();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().onDestroy();
    }

    protected abstract BaseFragmentPresenter getPresenter();

    protected abstract int getLayoutViewId();

    protected abstract void initView();

    protected abstract void intiData();

    protected abstract void setLisenter();
}
