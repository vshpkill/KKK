package com.youlehuo.app.publicmanager.newfirst;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.youlehuo.app.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ChayChan
 * @description: 非视频新闻详情
 * @date 2017/6/24  19:3
 */

public class NewsDetailDetailActivity extends NewsDetailBaseActivity {


   /* @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
*/
//    @BindView(R.id.tv_author)
//    TextView mTvAuthor;

    @Override
    protected int setView() {
        return  R.layout.activity_news_detail;
    }
    @Override
    public void initView() {
        super.initView();
    }

    @Override
    protected void initVariables() {


    }

   /* @Override
    public void initListener() {

        int llInfoBottom = mHeaderView.mLlInfo.getBottom();
//        LinearLayoutManager layoutManager = (LinearLayoutManager) mRvComment.getLayoutManager();
        *//*mRvComment.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int position = layoutManager.findFirstVisibleItemPosition();
                View firstVisiableChildView = layoutManager.findViewByPosition(position);
                int itemHeight = firstVisiableChildView.getHeight();
                int scrollHeight = (position) * itemHeight - firstVisiableChildView.getTop();

                KLog.i("scrollHeight: " + scrollHeight);
                KLog.i("llInfoBottom: " + llInfoBottom);

                mLlUser.setVisibility(scrollHeight > llInfoBottom ? View.VISIBLE : View.GONE);//如果滚动超过用户信息一栏，显示标题栏中的用户头像和昵称
            }
        });*//*
    }*/

//    @Override
//    public void onGetNewsDetailSuccess(NewsDetail newsDetail) {
//        mHeaderView.setDetail(newsDetail, new NewsDetailHeaderView.LoadWebListener() {
//            @Override
//            public void onLoadFinished() {
//                //加载完成后，显示内容布局
//                mStateView.showContent();
//            }
//        });
//
//        mLlUser.setVisibility(View.GONE);
//
//        if (newsDetail.media_user != null){
//            GlideUtils.loadRound(this,newsDetail.media_user.avatar_url, mIvAvatar);
//            mTvAuthor.setText(newsDetail.media_user.screen_name);
//        }
//    }


    @Override
    public void onLoadMoreRequested() {

    }
}
