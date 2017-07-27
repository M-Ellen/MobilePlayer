package com.example.mobileplayer.view.fragment;


import android.content.Intent;
import android.net.Uri;
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
import com.example.mobileplayer.view.callback.NativeVedioFragmentView;


public class NativeVideoFragment extends BaseFragment implements NativeVedioFragmentView {

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
        return null;
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
                Toast.makeText(mContext,mPresenter.getMediaData().get(position).toString(),Toast.LENGTH_SHORT);

//                //1.调起系统所有的播放-隐式意图
//                Intent intent = new Intent();
//                intent.setDataAndType(Uri.parse(mPresenter.getMediaData().get(position).getData()),"video/*");
//                startActivity(intent);

//                2.调用自己写的播放器-显示意图--一个播放地址
            Intent intent = new Intent(getActivity(),SystemVideoPlayerActivity.class);
            intent.setDataAndType(Uri.parse(mPresenter.getMediaData().get(position).getData()),"video/*");
            startActivity(intent);
            }
        });

    }

}
