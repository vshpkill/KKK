package com.youlehuo.app.publicmanager.refreshview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;
import com.youlehuo.app.publicmanager.refreshmy.RefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by xiaohe on 18-2-26.
 */

public class RefreshViewActivity extends BaseActivity {
    @BindView(R.id.pull_to_refresh)
    RefreshView mPullToRefreshView;
    @BindView(R.id.list_view)
    ListView listView;


    public static final String KEY_ICON = "icon";
    public static final String KEY_COLOR = "color";
    protected List<Map<String, Integer>> mSampleList;

    @Override
    protected int setView() {
        return R.layout.refreshviewactivity;
    }

    @Override
    protected void initView() {
        Map<String, Integer> map;
        mSampleList = new ArrayList<>();

        int[] icons = {
                R.drawable.icon_1,
                R.drawable.icon_2,
                R.drawable.icon_3,
                R.drawable.icon_1,
                R.drawable.icon_2,
                R.drawable.icon_3};

        int[] colors = {
                R.color.saffron,
                R.color.eggplant,
                R.color.sienna,
                R.color.saffron,
                R.color.eggplant,
                R.color.sienna};

        for (int i = 0; i < icons.length; i++) {
            map = new HashMap<>();
            map.put(KEY_ICON, icons[i]);
            map.put(KEY_COLOR, colors[i]);
            mSampleList.add(map);
        }
    }

    @Override
    protected void initVariables() {
        listView.setAdapter(new SampleAdapter(this, R.layout.list_item, mSampleList));
        mPullToRefreshView.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                Log.e("刷新状态", "刷新");
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                Log.e("刷新状态", "加载");
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                    }
                }, 2000);
            }
        });
    }

    class SampleAdapter extends ArrayAdapter<Map<String, Integer>> {

        public static final String KEY_ICON = "icon";
        public static final String KEY_COLOR = "color";

        private final LayoutInflater mInflater;
        private final List<Map<String, Integer>> mData;

        public SampleAdapter(Context context, int layoutResourceId, List<Map<String, Integer>> data) {
            super(context, layoutResourceId, data);
            mData = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_item, parent, false);
                viewHolder.imageViewIcon = (ImageView) convertView.findViewById(R.id.image_view_icon);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.imageViewIcon.setImageResource(mData.get(position).get(KEY_ICON));
            convertView.setBackgroundResource(mData.get(position).get(KEY_COLOR));

            return convertView;
        }

        class ViewHolder {
            ImageView imageViewIcon;
        }

    }
}
