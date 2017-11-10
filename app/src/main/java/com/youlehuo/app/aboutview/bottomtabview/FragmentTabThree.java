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

public class FragmentTabThree extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("Fragment执行状态","FragmentTabThree");
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setBackgroundColor(0xFFf5f67e);
        return frameLayout;
    }
}
