package com.youlehuo.app.aboutview.pulltorefresh;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaohe on 18-1-9.
 */

public class SwipeRefreshActivity extends BaseActivity {
    @BindView(R.id.srf_recycle)
    SwipeRefreshRecycleView srf_recycle;
    private List<String> strings;
    private DataAdapter dataAdapter;

    @Override
    protected int setView() {
        return R.layout.swiperefreshactivity;
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        srf_recycle.setLayoutManager(layoutManager);
        srf_recycle.setRefreshListener(new SwipeRefreshRecycleView.RefreshListener() {
            @Override
            public void refresh() {
                srf_recycle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srf_recycle.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        srf_recycle.setLoadMoreListener(new SwipeRefreshRecycleView.LoadMoreListener() {
            @Override
            public void loadMore() {
                Toast.makeText(context,"kakaka",Toast.LENGTH_SHORT).show();
                strings.add("按钮");
                strings.add("按钮");
                dataAdapter.notifyDataSetChanged();
                if (strings.size()>50){
                    srf_recycle.noMoreData();
                }else {
                    srf_recycle.dataLoadMore();
                }
            }
        });
    }

    @Override
    protected void initVariables() {
        strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("按钮" + i);
        }
        dataAdapter = new DataAdapter();
        srf_recycle.setAdapter(dataAdapter);
    }

    class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

        @Override
        public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DataViewHolder(new Button(context));
        }

        @Override
        public void onBindViewHolder(DataViewHolder holder, int position) {
            holder.button.setText(strings.get(position));
        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        class DataViewHolder extends RecyclerView.ViewHolder {
            Button button;

            public DataViewHolder(View itemView) {
                super(itemView);
                button = (Button) itemView;
            }
        }
    }
}
