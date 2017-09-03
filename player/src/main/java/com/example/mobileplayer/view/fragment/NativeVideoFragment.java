package com.example.mobileplayer.view.fragment;


import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.mobileplayer.R;
import com.example.mobileplayer.presenter.fragment.BaseFragmentPresenter;
import com.example.mobileplayer.presenter.fragment.NativeVideoFragmentPresenter;
import com.example.mobileplayer.view.Activity.SystemVideoPlayerActivity;
import com.example.mobileplayer.view.adapter.MyRecycleViewAdapter;
import com.example.mobileplayer.view.callback.NativeVideoFragmentView;


public class NativeVideoFragment extends BaseFragment implements NativeVideoFragmentView {

    private RecyclerView mRecyclerView;
    private MyRecycleViewAdapter mRecycleViewAdapter;
    private NativeVideoFragmentPresenter mPresenter;

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_video);
    }

    @Override
    protected void intiData() {

        mPresenter = new NativeVideoFragmentPresenter(mContext,this);

        mRecycleViewAdapter = new MyRecycleViewAdapter(mContext,mPresenter.getMediaData());
        mRecyclerView.setAdapter(mRecycleViewAdapter);
        //设置布局
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        //设置分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        //设置动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置监听
        setLisenter();
    }

    @Override
    protected BaseFragmentPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_native_video;
    }

    @Override
    protected void setLisenter() {
        mRecycleViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.onItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                mPresenter.onItemClick(position);
                Toast.makeText(mContext,mPresenter.getMediaData().get(position).toString(),Toast.LENGTH_SHORT);

            }
        });

    }

    @Override
    public void enterToPlay(Intent intent) {
        intent.setClass(getActivity(), SystemVideoPlayerActivity.class);
        startActivity(intent);
    }
}
