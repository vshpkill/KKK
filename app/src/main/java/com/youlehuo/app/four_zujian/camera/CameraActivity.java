package com.youlehuo.app.four_zujian.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStoragePublicDirectory;

/**
 * Created by dayima on 17-2-22.
 */

public class CameraActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.but_common)
    Button but_common;
    @BindView(R.id.im_show)
    ImageView im_show;
    @BindView(R.id.but_file)
    Button but_file;
    @BindView(R.id.im_file)
    ImageView im_file;
    @BindView(R.id.but_get)
    Button but_get;
    @BindView(R.id.but_video)
    Button but_video;
    @BindView(R.id.video_view)
    VideoView video_view;

    private static final int REQUEST_CAMERA = 102;
    private static final int REQUEST_FILE = 103;
    private static final int REQUEST_VIDEO = 104;
    @Override
    protected int setView() {
        return R.layout.cameraactivity;
    }

    @Override
    protected void initView() {
        but_common.setOnClickListener(this);
        but_file.setOnClickListener(this);
        but_get.setOnClickListener(this);
        but_video.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_common:
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                /**
                 * startActivityForResult() 方法被一个调用 resolveActivity() 方法的条件所保护，
                 * 这个方法返回了可以处理这个Intent的第一个Activity组件。执行这项检查是非常重要的，
                 * 因为如果你调用 startActivityForResult() 方法所使用的Intent没有APP可以处理的话，那么你的APP将会崩溃。
                 * 所以只要结果不是null，那么就意味着可以安全使用这个Intent
                 */
                if (camera.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(camera,REQUEST_CAMERA);
                }
            break;
            case R.id.but_file:
                File photoFile = null;
                try {
                    photoFile = setLocalFilePath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent savePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (savePhoto.resolveActivity(getPackageManager())!=null){
                    savePhoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    startActivityForResult(savePhoto,REQUEST_FILE);
            }
                break;
            case R.id.but_get:
                getAllPhoto();
                break;
            case R.id.but_video:
                openVideo();
                break;
        }
    }

    private void openVideo() {
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (videoIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(videoIntent,REQUEST_VIDEO);
        }
    }

    /**
     * 设置保存照片文件
     */
    private String photoPath;
    private File setLocalFilePath() throws IOException {
        //保存在设备外部存储器上的一个公共文件夹中，可以使所有的APP都可以访问
        getExternalStoragePublicDirectory(DIRECTORY_PICTURES);
        //保存在APP的私有目录中,卸载APP时会一并删除
        getExternalFilesDir(DIRECTORY_PICTURES);

        String filename = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        filename = "JPEG_"+filename+"_";
        File storageDir = getExternalStoragePublicDirectory(DIRECTORY_PICTURES);
        File image = File.createTempFile(
                filename,
                ".jpg",
                storageDir);
        photoPath = "file:"+image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 获取拍照后缩略图
         */
        if (resultCode==RESULT_OK&&requestCode == REQUEST_CAMERA){
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            im_show.setImageBitmap(bitmap);
        }
        if (resultCode==RESULT_OK&&requestCode == REQUEST_FILE){
            im_file.setImageURI(Uri.parse(photoPath));
        }
        if (resultCode==RESULT_OK&&requestCode == REQUEST_VIDEO){
            video_view.setVideoURI(data.getData());
        }
    }
    //调用系统扫描器添加你的照片到媒体扫描器(Media Provider)的数据库中，使这些照片可以被系统的相册应用或者其它APP可访问
    private void getAllPhoto(){
        Intent getPhoto = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(photoPath);
        Uri uri = Uri.fromFile(f);
        getPhoto.setData(uri);
        sendBroadcast(getPhoto);
    }
    //处理图片降低内存占用按照控件大小进行显示
    private void setPic(){
        int width = im_file.getWidth();
        int height = im_file.getHeight();

        BitmapFactory.Options options = new BitmapFactory.Options();
        //只读取图片,不加载到内存中
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath,options);

        int photoW = options.outWidth;
        int photoH = options.outHeight;
        //获取缩放比例取最小缩放比
        int scaleFactor = Math.min(photoW/width,photoH/height);

        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;
        //设置当内存占用较高时可以回收，当再次用到时会再次进行绘制存储
        options.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath,options);
        im_file.setImageBitmap(bitmap);
    }
}
