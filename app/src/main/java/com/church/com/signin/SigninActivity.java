package com.church.com.signin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.church.com.R;
import com.church.com.model.BasicResponse;
import com.church.com.screens.DrawerActivity;
import com.church.com.signup.SignUpActivity;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

public class SigninActivity extends AppCompatActivity implements SignInViewInterface, View.OnClickListener {

    private RelativeLayout rlSignIn;
    private RelativeLayout rlSignUp;
    private LinearLayout ll;
    private ImageView image;
    private AppCompatEditText etEmail;
    private ShowHidePasswordEditText password;
    private ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        changeStatusBarColor();
        initView();
        setListener();
    }

    private void setListener() {
        rlSignUp.setOnClickListener(this);
        rlSignIn.setOnClickListener(this);
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int color = ContextCompat.getColor(this, R.color.white);
            window.setStatusBarColor(color);
        }
    }

    @Override
    public void onSuccess(BasicResponse basicResponse) {

    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showError(String s) {

    }

    private void initView() {
        ll = (LinearLayout) findViewById(R.id.ll);
        image = (ImageView) findViewById(R.id.image);
        etEmail = (AppCompatEditText) findViewById(R.id.etEmail);
        password = (ShowHidePasswordEditText) findViewById(R.id.password);
        image1 = (ImageView) findViewById(R.id.image1);
        rlSignIn = findViewById(R.id.rlSignIn);
        rlSignUp = findViewById(R.id.rlSignUp);
    }

    @Override
    public void onClick(View view) {
        if(view==rlSignIn){
            Intent intent = new Intent(SigninActivity.this, DrawerActivity.class);
            startActivity(intent);
            finish();
        }   if(view==rlSignUp){
            Intent intent = new Intent(SigninActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    }
}
