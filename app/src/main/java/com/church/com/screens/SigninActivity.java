package com.church.com.screens;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.church.com.R;
import com.church.com.signup.SignUpActivity;

public class SigninActivity extends AppCompatActivity {

    private RelativeLayout rlSignIn;
    private RelativeLayout rlSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        changeStatusBarColor();

        init();
    }

    private void init() {

        rlSignIn = findViewById(R.id.rlSignIn);
        rlSignUp = findViewById(R.id.rlSignUp);

        rlSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        rlSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int color = ContextCompat.getColor(this, R.color.white);
            window.setStatusBarColor(color);
        }
    }
}
