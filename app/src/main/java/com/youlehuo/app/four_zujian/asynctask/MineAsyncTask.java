package com.youlehuo.app.four_zujian.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dayima on 17-1-16.
 * 初始化必须在UI线程（文件下载示例）
 */

public class MineAsyncTask extends AsyncTask<URL,Integer,Boolean> {
    private ProgressDialog progressDialog;
    private Context context;
    public MineAsyncTask(Context context){
        this.context = context;
    }
    /**
     * 此方法在主线程中执行，在异步任务执行之前，此方法会被调用，
     * 一般用于一些准备工作，例如下载进度条的初始化。
     */
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("下载进度");
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    /**
     * 此方法在子线程中执行，用于执行异步任务，
     * 注意这里的params就是AsyncTask的第一个参数类型。
     * 在此方法中可以通过调用publicProgress方法来更新任务进度，
     * publicProgress会调用 onProgressUpdate 方法。
     * @param params
     * @return
     */
    @Override
    protected Boolean doInBackground(URL... params) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) params[0].openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setReadTimeout(5000);
            final int contentLength;
            if (conn.getResponseCode()==200){
                contentLength = conn.getContentLength();
                File file = new File(context.getCacheDir(),"hh.jpg");
                InputStream is = null;
                FileOutputStream fos = new FileOutputStream(file);
                is = conn.getInputStream();
                int len;
                long totalSize = 0;
                byte[] bytes = new byte[102];
                while ((len = is.read(bytes))!=-1){
                    fos.write(bytes,0,len);
                    totalSize += len*100;
                    int progress = (int) (totalSize/contentLength);
                    publishProgress(progress);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 此方法在主线程中执行，
     * values的类型就是AsyncTask传入的第二个参数类型，
     * 当后台任务的执行进度发生变化时此方法执行。
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setProgress(values[0]);
    }

    /**
     * 此方法在主线程中执行，
     * 在 doInBackground 方法执行完成以后此方法会被调用，
     * 其中result的类型就是AsyncTask传入的第三个参数类型，
     * 它的值就是doInBackground方法的返回值。
     * @param aBoolean
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        progressDialog.dismiss();
        if (aBoolean){
            Toast.makeText(context,"下载成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"下载失败",Toast.LENGTH_SHORT).show();
        }
    }
}
