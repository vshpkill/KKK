package com.youlehuo.app.publicmanager.newfirst;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youlehuo.app.R;

import java.util.List;

/**
 * @author ChayChan
 * @description: 新闻详情页评论的适配器
 * @date 2017/6/28  16:01
 */

public class CommentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;

    public CommentAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String commentData) {
        helper.setText(R.id.tv_name, "名字")
                .setText(R.id.tv_like_count, "名字")
                .setText(R.id.tv_content, "名字")
                .setText(R.id.tv_time, "名字");
    }
}
