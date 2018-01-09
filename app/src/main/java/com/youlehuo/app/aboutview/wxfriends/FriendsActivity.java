package com.youlehuo.app.aboutview.wxfriends;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

/**
 * Created by xiaohe on 18-1-3.
 */

public class FriendsActivity extends BaseActivity {
    @BindView(R.id.tv_test)
    TextView textView;

    @Override
    protected int setView() {
        return R.layout.friendsactivity;
    }

    @Override
    protected void initView() {
        String content = "小明回复小丽：阿拉斯加州的太阳很圆啊，不像我们家乡的都是半拉的";
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new ClickableColorSpan(getResources().getColor(R.color.red_1)) {
            @Override
            public void onClick(View widget) {
                Log.e("ces", "呵呵哒1");
            }
        }, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableColorSpan(getResources().getColor(R.color.red_1)) {
            @Override
            public void onClick(View widget) {
                Log.e("ces", "呵呵哒2");
            }
        }, 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableColorSpan(getResources().getColor(R.color.red_1)) {
            @Override
            public void onClick(View widget) {
                Log.e("ces", "呵呵哒3");
            }
        }, 7, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
//        avoidHintColor(textView);
    }
    public abstract class ClickableColorSpan extends ClickableSpan {
        int color;
        public ClickableColorSpan(int color) {
            // this.str = str;
            this.color = color;
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);//是否显示下划线
            ds.setColor(color);
        }

    }
    private void avoidHintColor(View view){
        if(view instanceof TextView)
            ((TextView)view).setHighlightColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    protected void initVariables() {

    }
}
