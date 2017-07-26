package com.ifilmo.www.testaapdp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by LeoLu on 2017/1/4.
 */

@EActivity(R.layout.activity_welcome)
@RuntimePermissions
public class WelcomeActivity extends AppCompatActivity {

    int versionCode = 0;

    protected void afterView() {
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = this.getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    void checkPermissions() {
        //初始化文件路径
        start();
    }

    @OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    protected void showRationaleForCamera(PermissionRequest request) {
        Toast.makeText(this, "OnShowRationale for camera", Toast.LENGTH_SHORT).show();
        request.proceed();
    }

    @OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    protected void showNeverAskForCamera() {
        Toast.makeText(this, "OnNeverAskAgain for camera", Toast.LENGTH_SHORT).show();
    }


    @UiThread(delay = 1500)
    void start() {

    }
}
