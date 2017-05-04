package com.youlehuo.app.four_zujian.permission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by dayima on 17-1-13.
 * 小米系统没有再次询问选项，拒绝之后不会再重现询问弹框
 */
@RuntimePermissions
public class PermissionActivity extends BaseActivity {
    @BindView(R.id.but_camera)
    Button but_camera;

    @Override
    protected int setView() {
        return R.layout.permissionactivity;
    }

    @Override
    protected void initView() {
        but_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发起权限申请
                PermissionActivityPermissionsDispatcher.cameraPermissionWithCheck(PermissionActivity.this);
            }
        });
    }

    @Override
    protected void initVariables() {

    }

    /**
     *权限获取后执行
     */
    @NeedsPermission(Manifest.permission.CAMERA)
    void cameraPermission() {
        Toast.makeText(this, "权限通过", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * 调起权限申请界面
     * @param request
     */
    @OnShowRationale(Manifest.permission.CAMERA)
    void shouDialog(final PermissionRequest request) {
        request.proceed();
//        new AlertDialog.Builder(this)
//                .setMessage("申请相机权限")
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //再次执行请求
//                        request.proceed();
//                    }
//                })
//                .show();
    }

    /**
     *权限拒绝调用
     */
    @OnPermissionDenied(Manifest.permission.CAMERA)
    void nextAsk() {
        Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
    }

    /**
     *点击不再询问并拒绝后调用(给出用户提示在应用设置进行权限设置)
     */
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void neverAks() {
        Toast.makeText(this, "不再询问", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(intent);
    }
}
