package com.youlehuo.app.aboutview.pulltorefresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by xiaohe on 18-1-9.
 */

public class SwipeRefreshRecycleView extends SwipeRefreshLayout {

    private RecyclerView recyclerView;
    private boolean isCanLoadMore = true;
    private boolean noMoreData = false;
    public SwipeRefreshRecycleView(Context context) {
        super(context);
        initView();
    }

    public SwipeRefreshRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        recyclerView = new RecyclerView(getContext());
        addView(recyclerView);
        SwipeRefreshRecycleView.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT-60);
        recyclerView.setLayoutParams(layoutParams);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    public void setIsCanLoadMore(boolean isCanLoadMore) {
        this.isCanLoadMore = isCanLoadMore;
    }

    public void setRefreshListener(final RefreshListener refreshListener) {
        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshListener.refresh();
                noMoreData = false;
            }
        });
    }

    public void setLoadMoreListener(final LoadMoreListener moreListener) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (isCanLoadMore && isLastItem()) {
                    if (!noMoreData){
                        noMoreData = true;
                        dataLoading();
                        moreListener.loadMore();
                    }
                }
            }
        });
    }

    private boolean isLastItem() {
        Log.e("看看是不是次数太多了","啦啦啦啦");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int lastVisibleItemPosition = getLastVisibleItemPosition(layoutManager);
        return visibleItemCount > 0 && lastVisibleItemPosition >= totalItemCount - 1 &&
                totalItemCount >= visibleItemCount;

    }

    private int getLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        int lastVisibleItemPosition;
        if (layoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
            ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
            lastVisibleItemPosition = findMax(into);
        } else {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        return lastVisibleItemPosition;
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public void noMoreData() {
        noMoreData = true;
    }

    public void dataLoading() {
    }

    public void dataLoadMore() {
        noMoreData = false;
    }

    public interface LoadMoreListener {
        void loadMore();
    }

    public interface RefreshListener {
        void refresh();
    }
    @Override
    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
