package com.youlehuo.app.aboutview.tablayout;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dayima on 17-8-4.
 */

public class TabLayoutActivity extends BaseActivity {
    @BindView(R.id.tagview)
    TagListView mTagListView;
    private final List<Tag> mTags = new ArrayList<Tag>();
    private final String[] titles = {"安全必备", "音乐", "父母学", "上班族必备",
            "360手机卫士", "QQ", "输入法", "微信", "最美应用", "AndevUI", "蘑菇街"};

    @Override
    protected int setView() {
        return R.layout.tablayoutactivity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initVariables() {
        for (int i = 0; i < 10; i++) {
            Tag tag = new Tag();
            tag.setId(i);
            tag.setChecked(true);
            tag.setTitle(titles[i]);
            mTags.add(tag);
        }
        mTagListView.setTags(mTags);
    }
}
