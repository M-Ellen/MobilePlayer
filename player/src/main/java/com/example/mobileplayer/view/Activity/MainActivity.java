package com.example.mobileplayer.view.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mobileplayer.R;
import com.example.mobileplayer.presenter.activity.BaseActivityPresenter;
import com.example.mobileplayer.presenter.activity.MainActivityPresenter;
import com.example.mobileplayer.view.adapter.MainContentAdapter;
import com.example.mobileplayer.view.callback.MainActivityView;
import com.example.mobileplayer.view.fragment.BaseFragment;
import com.example.mobileplayer.view.fragment.NativeAudioFragment;
import com.example.mobileplayer.view.fragment.NativeVideoFragment;
import com.example.mobileplayer.view.fragment.NetworkAudioFragment;
import com.example.mobileplayer.view.fragment.NetworkVideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * user： pzm
 * date： 2016/11/23
 */
public class MainActivity extends BaseActivity implements MainActivityView {


    private LinearLayout mTitle;
    private FrameLayout mContent;
    private RadioButton mVideo;
    private RadioButton mNetVideo;
    private RadioButton mAudio;
    private RadioButton mNetAudio;
    private RadioGroup mRadioGroup;

    private MainContentAdapter mMainContentAdapter;
    private List<BaseFragment> mBaseFragment;
    private BaseFragment mCurrent;
    private int mPositon;

    private MainActivityPresenter mPresenter = null;

    @Override
    protected BaseActivityPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTitle =  (LinearLayout) findViewById(R.id.linearlayout_main_title);
        mContent = (FrameLayout) findViewById(R.id.frame_main_content);
        mRadioGroup = (RadioGroup)findViewById(R.id.radiogroup_main);

        mVideo = (RadioButton) findViewById(R.id.radiobutton_main_video);
        mNetVideo = (RadioButton) findViewById(R.id.radiobutton_main_netvideo);
        mAudio = (RadioButton) findViewById(R.id.radiobutton_main_audio);
        mNetAudio = (RadioButton) findViewById(R.id.radiobutton_main_netaudio);
    }

    @Override
    protected void initData() {
        mPresenter = new MainActivityPresenter(this, this);
        initFragment();
    }

    @Override
    protected void setLisenter() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case  R.id.radiobutton_main_video:
                        mPositon = 0;
                        break;
                    case  R.id.radiobutton_main_audio:
                        mPositon = 1;
                        break;
                    case  R.id.radiobutton_main_netvideo:
                        mPositon = 2;
                        break;
                    case  R.id.radiobutton_main_netaudio:
                        mPositon = 3;
                        break;
                    default:
                        mPositon = 0;
                        break;
                }
                switchFragment(getFragment());

            }
        });
        //初始化选中fragment
        mRadioGroup.check(R.id.radiobutton_main_video);
    }



    @Override
    public void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new NativeVideoFragment());//本地视频
        mBaseFragment.add(new NativeAudioFragment());//本地音频
        mBaseFragment.add(new NetworkVideoFragment());//网络音频
        mBaseFragment.add(new NetworkAudioFragment());//网络音频
    }

    @Override
    public BaseFragment getFragment() {
        return mBaseFragment.get(mPositon);
    }

    @Override
    public void switchFragment(BaseFragment baseFragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main_content,baseFragment);
        fragmentTransaction.commit();
    }
}

