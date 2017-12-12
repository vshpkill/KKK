package com.youlehuo.app.aboutview.bottomtabview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by xiaohe on 17-11-10.
 */

public class FragmentTabOne extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Fragment执行状态","FragmentTabOne--onCreate");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("Fragment执行状态","FragmentTabOne");
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setBackgroundColor(0xFFffffff);
        return frameLayout;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            loadData();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void loadData() {
        Log.e("Fragment执行状态","FragmentTabOne--loadData");
    }
}