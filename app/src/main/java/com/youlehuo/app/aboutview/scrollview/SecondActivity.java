package com.youlehuo.app.aboutview.scrollview;

import android.app.Activity;
import android.os.Bundle;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.exceptions.InvalidDataException;
import com.youlehuo.app.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dayima on 16-12-26.
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);
        try {
            JSONObject properties = new JSONObject();
            properties.put("ProductID", 123456);                    // 设置商品ID
            properties.put("ProductCatalog", "Laptop Computer");    // 设置商品类别
            properties.put("IsAddedToFav", false);                  // 是否被添加到收藏夹

            SensorsDataAPI.sharedInstance(this).track("ViewProduct", properties);
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
