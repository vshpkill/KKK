package com.youlehuo.app.publicmanager.newfirst;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ChayChan
 * @description: 新闻详情页的基类
 * @date 2017/7/4  15:59
 */

public abstract class NewsDetailBaseActivity extends BaseActivity implements  BaseQuickAdapter.RequestLoadMoreListener {

    public static final String CHANNEL_CODE = "channelCode";
    public static final String PROGRESS = "progress";
    public static final String POSITION = "position";
    public static final String DETAIL_URL = "detailUrl";
    public static final String GROUP_ID = "groupId";
    public static final String ITEM_ID = "itemId";

    @BindView(R.id.fl_content)
    FrameLayout mFlContent;

    @BindView(R.id.rv_comment)
    PowerfulRecyclerView mRvComment;


    private List<String> mCommentList = new ArrayList<>();
    private CommentAdapter mCommentAdapter;
    protected NewsDetailHeaderView mHeaderView;
    private String mDetalUrl;
    private String mGroupId;
    private String mItemId;

    protected String mChannelCode;
    protected int mPosition;




    @Override
    public void initView() {

        mCommentAdapter = new CommentAdapter(context, R.layout.item_comment, mCommentList);
        mRvComment.setAdapter(mCommentAdapter);

        mHeaderView = new NewsDetailHeaderView(this);
        mCommentAdapter.addHeaderView(mHeaderView);

        mCommentAdapter.setEnableLoadMore(true);
        mCommentAdapter.setOnLoadMoreListener(this, mRvComment);

        mRvComment.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<50;i++){
                    mCommentList.add("dfsdfdsfdsfrgt");
                    mCommentAdapter.notifyDataSetChanged();
                }
                mHeaderView.setDetail(null, new NewsDetailHeaderView.LoadWebListener() {
                    @Override
                    public void onLoadFinished() {
                        //加载完成后，显示内容布局
                    }
                });
            }
        },3000);

    }



   /* @Override
    public void onGetCommentSuccess(CommentResponse response) {

        mCommentResponse = response;

        if (ListUtils.isEmpty(response.data)){
            //没有评论了
            mCommentAdapter.loadMoreEnd();
            return;
        }

        if (response.total_number > 0) {
            //如果评论数大于0，显示红点
            mTvCommentCount.setVisibility(View.VISIBLE);
            mTvCommentCount.setText(String.valueOf(response.total_number));
        }

        mCommentList.addAll(response.data);
        mCommentAdapter.notifyDataSetChanged();

        if (!response.has_more) {
            mCommentAdapter.loadMoreEnd();
        }else{
            mCommentAdapter.loadMoreComplete();
        }
    }*/


   /* @Override
    public void onError() {
        mStateView.showRetry();
    }*/


    /*@OnClick({R.id.fl_comment_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_comment_icon:
                //底部评论的图标
                RecyclerView.LayoutManager layoutManager = mRvComment.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    int firstPosition = linearManager.findFirstVisibleItemPosition();
                    int last = linearManager.findLastVisibleItemPosition();
                    if (firstPosition == 0 && last == 0) {
                        //处于头部，滚动到第一个条目
                        mRvComment.scrollToPosition(1);
                    } else {
                        //不是头部，滚动到头部
                        mRvComment.scrollToPosition(0);
                    }
                }
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getComment(mGroupId, mItemId, mCommentList.size() / Constant.COMMENT_PAGE_SIZE + 1);
    }


    *//**
     *  发送事件，用于更新上个页面的播放进度以及评论数
     *//*
    protected void postVideoEvent(boolean isVideoDetail) {
        DetailCloseEvent event = new DetailCloseEvent();
        event.setChannelCode(mChannelCode);
        event.setPosition(mPosition);

        if (mCommentResponse != null){
            event.setCommentCount(mCommentResponse.total_number);
        }

        if (isVideoDetail && JCMediaManager.instance().mediaPlayer != null && JCVideoPlayerManager.getCurrentJcvd() != null){
            //如果是视频详情
            int progress = JCMediaManager.instance().mediaPlayer.getCurrentPosition();
            event.setProgress(progress);
        }

        EventBus.getDefault().postSticky(event);
        finish();
    }*/
}
