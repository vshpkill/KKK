package com.lib.idmquick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    private EditText et_hour;
    private EditText et_minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_hour = findViewById(R.id.et_hour);
        et_minute = findViewById(R.id.et_minute);

    }

    public void startService(View view) {
        String hour = et_hour.getText().toString();
        String minute = et_minute.getText().toString();
        if (TextUtils.isEmpty(hour) || TextUtils.isEmpty(minute)) {
            Toast.makeText(this, "先设置个时间啊", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "开始运行", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ClickService.class);
        intent.putExtra(HOUR, hour);
        intent.putExtra(MINUTE, minute);
        startService(intent);
    }
}
