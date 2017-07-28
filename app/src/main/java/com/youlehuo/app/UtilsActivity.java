package com.youlehuo.app;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by dayima on 17-7-13.
 */

public class UtilsActivity extends BaseActivity {
    @Override
    protected int setView() {
        return R.layout.utilsactivity;
    }

    @Override
    protected void initView() {
//        uidToPhoneNum();

//        FileReader fr = null;
//        FileWriter wr = null;
//        try {
//            fr = new FileReader("/storage/emulated/0/a");
//            wr = new FileWriter("/storage/emulated/0/b.txt");
//            BufferedReader bf = new BufferedReader(fr);
//            BufferedWriter writer = new BufferedWriter(wr);
//            String line;
//            int i = 0;
//            String id = "";
//            int j = -1;
//            while ((line = bf.readLine()) != null) {
//                String out = "";
//                String[] data = line.split(",");
//                if (data.length > 3) {
//                    if (TextUtils.equals(data[0], id)) {
//                        i++;
//                        out = "events"+j+"[" + i + "].dateline=" + data[3] + ";events"+j+"[" + i + "].type=" + data[4]+";";
//                        writer.write(out);
//                    } else {
//                        j++;
//                        if (i>0){
//                            writer.write(","+(i+1));
//                        }
//                        i = 0;
//                        id = data[0];
//                        out = data[0] + "," + data[1] + "," + data[2]+","+"events"+j+"[0].dateline=" + data[3] + ";events"+j+"[0].type=" + data[4]+";";
//                        writer.newLine();
//                        writer.write(out);
//                    }
//                    writer.flush();
//                }
//            }
//            writer.close();
//            bf.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void initVariables() {
        FileReader fr = null;
        FileWriter wr = null;
        try {
            fr = new FileReader("/storage/emulated/0/e.txt");
            wr = new FileWriter("/storage/emulated/0/c.txt");
            BufferedReader bf = new BufferedReader(fr);
            BufferedWriter writer = new BufferedWriter(wr);
            String line;
            int i = 0;
            while ((line = bf.readLine()) != null) {
                String out = "";
                String[] data = line.split(",");
                if (data.length > 3) {
                    out = "EVENT_ITEM events"+i+"["+data[4]+"];"+data[3]+"cacCustomCal("+data[0]+","+data[1]+","+data[2]+",events"+i+",sizeof(events"+i+")/sizeof(EVENT_ITEM));";
                    writer.newLine();
                    writer.write(out);
                    writer.flush();
                }
                i++;
            }
            writer.close();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uidToPhoneNum(){
        FileReader frold = null;
        FileReader fr = null;
        FileWriter wr = null;
        try {
            frold = new FileReader("/storage/emulated/0/b.txt");
            fr = new FileReader("/storage/emulated/0/d");
            wr = new FileWriter("/storage/emulated/0/e.txt");
            BufferedReader bfold = new BufferedReader(frold);
            BufferedReader bf = new BufferedReader(fr);
            BufferedWriter writer = new BufferedWriter(wr);
            String oldline;
            String line;
            while ((line = bfold.readLine()) != null) {
                String out = "";
                String[] data = line.split(",");
                boolean has = false;
                while ((oldline = bf.readLine())!= null){
                    String[] dataold = oldline.split(" ");
                    if (TextUtils.equals(data[0],dataold[0])){
                        out = dataold[1]+","+data[1]+","+data[2]+","+data[3]+","+data[4];
                        has = true;
                        writer.newLine();
                        writer.write(out);
                        writer.flush();
                        break;
                    }
                }
                if (!has){
                    out = line;
                    writer.newLine();
                    writer.write(out);
                    writer.flush();
                }
            }
            writer.close();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //返回键按下调用方法
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
