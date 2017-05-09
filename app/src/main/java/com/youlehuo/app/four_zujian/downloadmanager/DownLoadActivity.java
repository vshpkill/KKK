package com.youlehuo.app.four_zujian.downloadmanager;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import com.youlehuo.app.BaseActivity;

import java.io.File;

import static android.app.DownloadManager.Request.NETWORK_WIFI;
import static android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED;

/**
 * Created by dayima on 17-5-9.
 */

public class DownLoadActivity extends BaseActivity {
    private String downloadUrl = "http://apk.hiapk.com/appdown/net.csdn.csdnplus";
    private DownloadManager downloadManager;
    private DownloadManager.Request request;

    @Override
    protected int setView() {
        return 0;
    }

    @Override
    protected void initView() {
        downFile();
    }

    @Override
    protected void initVariables() {
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        // 将下载请求加入下载队列, 返回一个下载ID
        long downloadId = downloadManager.enqueue(request);
        // 如果中途想取消下载, 可以调用remove方法, 根据返回的下载ID取消下载, 取消下载后下载保存的文件将被删除
        // manager.remove(downloadId);

        // 创建一个查询对象
        DownloadManager.Query query = new DownloadManager.Query();
        // 根据 下载ID 过滤结果
        query.setFilterById(downloadId);
        // 还可以根据状态过滤结果
        // query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);

        Cursor cursor = downloadManager.query(query);
        if (!cursor.moveToFirst()) {
            cursor.close();
            return;
        }

        // 下载ID
        long id = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
        // 下载请求的状态
        int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
        // 下载文件在本地保存的路径（Android 7.0 以后 COLUMN_LOCAL_FILENAME 字段被弃用, 需要用 COLUMN_LOCAL_URI 字段来获取本地文件路径的 Uri）
        String localFilename = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
        // 已下载的字节大小
        long downloadedSoFar = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
        // 下载文件的总字节大小
        long totalSize = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

        cursor.close();

        System.out.println("下载进度: " + downloadedSoFar  + "/" + totalSize);

        /*
         * 判断是否下载成功，其中状态 status 的值有 5 种:
         *     DownloadManager.STATUS_SUCCESSFUL:   下载成功
         *     DownloadManager.STATUS_FAILED:       下载失败
         *     DownloadManager.STATUS_PENDING:      等待下载
         *     DownloadManager.STATUS_RUNNING:      正在下载
         *     DownloadManager.STATUS_PAUSED:       下载暂停
         */
        if (status == DownloadManager.STATUS_SUCCESSFUL) {
        /*
         * 特别注意: 查询获取到的 localFilename 才是下载文件真正的保存路径，在创建
         * 请求时设置的保存路径不一定是最终的保存路径，因为当设置的路径已是存在的文件时，
         * 下载器会自动重命名保存路径，例如: .../demo-1.apk, .../demo-2.apk
         */
            System.out.println("下载成功, 打开文件, 文件路径: " + localFilename);
        }

    }

    private void downFile() {
        // 创建下载请求
        request = new DownloadManager.Request(Uri.parse(downloadUrl));

        //设置通知栏下载进度通知
        //VISIBILITY_VISIBLE:下载过程中可见, 下载完后自动消失(默认)
        //VISIBILITY_VISIBLE_NOTIFY_COMPLETED:下载过程中和下载完成后均可见
        //VISIBILITY_HIDDEN:始终不显示通知
        request.setNotificationVisibility(VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("下载进度通知");
        request.setDescription("快下载，不然挨揍了！");

        //设置允许使用网络类型
        //NETWORK_MOBILE:      移动网络
        //NETWORK_WIFI:        WIFI网络
        //NETWORK_BLUETOOTH:   蓝牙网络
        //默认为所有网络都允许
        request.setAllowedNetworkTypes(NETWORK_WIFI);

        // 添加请求头
        // request.addRequestHeader("User-Agent", "Chrome Mozilla/5.0");

        // 设置下载文件的保存位置
        File saveFile = new File(Environment.getExternalStorageDirectory(), "demo.apk");
        request.setDestinationUri(Uri.fromFile(saveFile));
    }
}
