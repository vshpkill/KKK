package com.youlehuo.app.aboutview.pobupwindow;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;
import com.youlehuo.app.aboutview.pobupwindow.bubbleEngine.HelpPopupManager;

/**
 * Created by dayima on 17-3-9.
 */

public class PobActivity extends BaseActivity {
    @Override
    protected int setView() {
        return R.layout.pobactivity;
    }

    @Override
    protected void initView() {
        TextView tvSmallTest = (TextView)findViewById(R.id.textViewSmallTest);
        HelpPopupManager.addHelpPopupWindow(1, 1, tvSmallTest, "This is small text");
        TextView tvLargeTest = (TextView)findViewById(R.id.textViewLargeTest);
        HelpPopupManager.addHelpPopupWindow(1, 2, tvLargeTest, "This is large text");

        RadioButton radioButtonTest = (RadioButton)findViewById(R.id.radioButtonTest);
        HelpPopupManager.addHelpPopupWindow(1, 3, radioButtonTest, "This is radio button test");
        radioButtonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buttonTest = (Button)findViewById(R.id.buttonTest);
        HelpPopupManager.addHelpPopupWindow(1, 4, buttonTest, "This is button text");
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpPopupManager.setEnabled(true);
                HelpPopupManager.startShowing();
            }
        });
    }

    @Override
    protected void initVariables() {

    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//        if(keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME)
//        {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            startActivity(intent);
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
