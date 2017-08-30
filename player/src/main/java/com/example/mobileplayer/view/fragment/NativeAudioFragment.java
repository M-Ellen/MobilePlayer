package com.example.mobileplayer.view.fragment;


import com.example.mobileplayer.R;
import com.example.mobileplayer.presenter.fragment.BaseFragmentPresenter;
import com.example.mobileplayer.presenter.fragment.NativeVideoFragmentPresenter;
import com.example.mobileplayer.view.callback.NativeAudioFragmentView;

public class NativeAudioFragment extends BaseFragment implements NativeAudioFragmentView {


    private NativeVideoFragmentPresenter mPresenter = null;

    @Override
    protected BaseFragmentPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_native_audio;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void intiData() {
//        mPresenter = new NativeAudioFragmentPresenter(this, this);
    }

    @Override
    protected void setLisenter() {

    }

}
