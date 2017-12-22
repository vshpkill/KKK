package com.youlehuo.app.videodemo;

import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;

import java.io.IOException;

/**
 * Created by xiaohe on 17-12-19.
 */

public class ItemFactory {

    public static BaseVideoItem createItemFromAsset(String assetName, int imageResource, AppCompatActivity activity, VideoPlayerManager<MetaData> videoPlayerManager) throws IOException {
        return new AssetVideoItem(assetName, activity.getAssets().openFd(assetName), videoPlayerManager, Picasso.with(activity), imageResource);
    }
}
