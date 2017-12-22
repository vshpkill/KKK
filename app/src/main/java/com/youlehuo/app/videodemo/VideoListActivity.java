package com.youlehuo.app.videodemo;

import android.widget.AbsListView;
import android.widget.ListView;

import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator;
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.volokh.danylo.visibility_utils.scroll_utils.ItemsPositionGetter;
import com.volokh.danylo.visibility_utils.scroll_utils.ListViewItemPositionGetter;
import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by xiaohe on 17-12-19.
 */

public class VideoListActivity extends BaseActivity {
    @BindView(R.id.video_list)
    ListView video_list;
    private final ArrayList<BaseVideoItem> mList = new ArrayList<>();

    private final ListItemsVisibilityCalculator mListItemVisibilityCalculator =
            new SingleListViewItemActiveCalculator(new DefaultSingleItemCalculatorCallback(), mList);
    private ItemsPositionGetter mItemsPositionGetter;
    private final VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
        @Override
        public void onPlayerItemChanged(MetaData metaData) {

        }
    });
    private int mScrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
    VideoListViewAdapter videoListViewAdapter = new VideoListViewAdapter(mVideoPlayerManager, this, mList);

    @Override
    protected int setView() {
        return R.layout.videolistactivity;
    }

    @Override
    protected void initView() {
        try {
            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.img001, this, mVideoPlayerManager));
            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.capture04, this, mVideoPlayerManager));
            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.video_sample_1_pic, this, mVideoPlayerManager));
            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.official, this, mVideoPlayerManager));
            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.head, this, mVideoPlayerManager));
            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.capture01, this, mVideoPlayerManager));
            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.capture02, this, mVideoPlayerManager));
            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.capture03, this, mVideoPlayerManager));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mItemsPositionGetter = new ListViewItemPositionGetter(video_list);

        video_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                mScrollState = scrollState;
                if (scrollState == SCROLL_STATE_IDLE && !mList.isEmpty()) {
                    mListItemVisibilityCalculator.onScrollStateIdle(mItemsPositionGetter, view.getFirstVisiblePosition(), view.getLastVisiblePosition());
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (!mList.isEmpty()) {
                    // on each scroll event we need to call onScroll for mListItemVisibilityCalculator
                    // in order to recalculate the items visibility
                    mListItemVisibilityCalculator.onScroll(mItemsPositionGetter, firstVisibleItem, visibleItemCount, mScrollState);
                }
            }
        });
    }

    @Override
    protected void initVariables() {
        video_list.setAdapter(videoListViewAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!mList.isEmpty()) {
            // need to call this method from list view handler in order to have list filled previously
            video_list.post(new Runnable() {
                @Override
                public void run() {
                    mListItemVisibilityCalculator.onScrollStateIdle(
                            mItemsPositionGetter,
                            video_list.getFirstVisiblePosition(),
                            video_list.getLastVisiblePosition());

                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // we have to stop any playback in onStop
        mVideoPlayerManager.resetMediaPlayer();
    }

}
