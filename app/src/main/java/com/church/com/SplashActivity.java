package com.church.com;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.church.com.screens.IntroScreenActivity;
import com.church.com.utility.Constant;

public class SplashActivity extends AppCompatActivity {

    private Context mContext;
    public static final int RequestPermissionCode = 7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        changeStatusBarColor();


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                handlePermission();
            }
        }, Constant.SPLASH_TIME_OUT);
    }

    private void handlePermission() {

        if (CheckingPermissionIsEnabledOrNot()) {
            gotoMain();
        } else {
            RequestMultiplePermission();
        }

    }


    public boolean CheckingPermissionIsEnabledOrNot() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE);
        int FirstPermissionResult1 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int FirstPermissionResult2 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);
        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FirstPermissionResult1 == PackageManager.PERMISSION_GRANTED &&
                FirstPermissionResult2 == PackageManager.PERMISSION_GRANTED;
    }


    //Permission function starts from here
    private void RequestMultiplePermission() {

        // Creating String Array with Permissions.
        ActivityCompat.requestPermissions(SplashActivity.this, new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        }, RequestPermissionCode);

    }

    // Calling override method.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean CameraPermission1 = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean CameraPermission2 = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    if (CameraPermission && CameraPermission1 && CameraPermission2) {

                        gotoMain();
                    } else {
                        finish();
                    }
                }

                break;
        }
    }


    private void gotoMain() {
        Intent intent = new Intent(SplashActivity.this, IntroScreenActivity.class);
        startActivity(intent);
        finish();

    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int color = ContextCompat.getColor(this, R.color.colorPrimary);
            window.setStatusBarColor(color);
        }
    }

}
