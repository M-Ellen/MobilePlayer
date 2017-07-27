package com.example.mobileplayer.view;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.example.mobileplayer.view.fragment.BaseFragment;

/**
 * Created by pzm on 2017/7/26.
 */

public class MyApplication extends Application {

    private static MyApplication mApplication;

    public static MyApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static void switchFragment(Activity activity, BaseFragment from, BaseFragment to, int id){

        if (activity.isDestroyed()){
            return;
        }
        if (to == null){
            return;
        }

        if (from != to) {
            FragmentManager manager = activity.getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            if (from == null) {
                if (!to.isAdded()){
                    transaction.add(id, to).commit();
                }
            }else {
                if (!to.isAdded()){
                    transaction.hide(from).add(id, to).commit();
                }else {
                    transaction.hide(from).show(to).commit();
                }
            }
        }
    }
}
