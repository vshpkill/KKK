package com.youlehuo.app.publicmanager.ipc;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;
import com.youlehuo.app.four_zujian.service.ClickService;

import java.io.DataOutputStream;
import java.io.OutputStream;

import butterknife.BindView;

/**
 * Created by xiaohe on 17-12-22.
 */

public class IPCActivity extends BaseActivity {
    @BindView(R.id.but_click1)
    Button but_click1;
    @BindView(R.id.but_click2)
    Button but_click2;

    @Override
    protected int setView() {
        return R.layout.ipcactivity;
    }

    @Override
    protected void initView() {
        but_click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("触发点击", "----");
            }
        });
        but_click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                execShell(amd);
                Intent intent = new Intent(context, ClickService.class);
                startService(intent);
            }
        });
    }

    @Override
    protected void initVariables() {
    }

    private String[] search = {
            "input keyevent 3",// 返回到主界面，数值与按键的对应关系可查阅KeyEvent
            "sleep 1",// 等待1秒
            "am start -n com.tencent.mm/com.tencent.mm.ui.LauncherUI",// 打开微信的启动界面，am命令的用法可自行百度、Google
            "sleep 3",// 等待3秒
            "am start -n com.tencent.mm/com.tencent.mm.plugin.search.ui.SearchUI",// 打开微信的搜索
            "input text 123",// 像搜索框中输入123，但是input不支持中文，蛋疼，而且这边没做输入法处理，默认会自动弹出输入法
    };

    private String amd = "input tap 200 200";

    public void execShell(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec("sh");
            OutputStream outputStream = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(
                    outputStream);
            dataOutputStream.writeBytes(cmd + "\n");
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
