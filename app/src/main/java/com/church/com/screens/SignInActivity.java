package com.church.com.screens;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.church.com.AppSettings;
import com.church.com.R;
import com.church.com.model.BasicResponse;
import com.church.com.model.LoginResponse;
import com.church.com.presenter.SignInPresenter;
import com.church.com.utility.Util;
import com.church.com.view_interface.SignInViewInterface;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity implements SignInViewInterface, View.OnClickListener {

    private RelativeLayout rlSignIn;
    private RelativeLayout rlSignUp;
    private LinearLayout ll;
    private ImageView image;
    private AppCompatEditText etEmail;
    private ShowHidePasswordEditText password;
    private ImageView image1;
    SignInPresenter signInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        changeStatusBarColor();
        initView();
        setListener();
        setUpMVP();
    }

    private void setUpMVP() {
        signInPresenter = new SignInPresenter(this);
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
    public void onSuccess(LoginResponse loginResponse) {
        if(loginResponse.getStatus().equals("1")){
            AppSettings.setLogin(SignInActivity.this,true);
            AppSettings.setUserId(SignInActivity.this,loginResponse.getLoginResult().getId());
            Intent intent = new Intent(SignInActivity.this, DrawerActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void showToast(String s) {
        Util.ShowToast(SignInActivity.this,s);
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

        if (view == rlSignIn) {
            String type = "";
            if (Util.isEmpty(etEmail.getText().toString().trim())) {
                showToast("Please enter email/mobile");
                return;
            }
            if (Util.isEmpty(password.getText().toString().trim())) {
                showToast("Please enter password");
                return;
            }
            if (Character.isDigit(etEmail.getText().toString().charAt(0))) {
                type = "mobile";
            } else {
                type = "email";
            }

            login(etEmail.getText().toString(), etEmail.getText().toString(), password.getText().toString(), "", "", "", "", type);

        }
        if (view == rlSignUp) {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    }

    /*http://www.awesomemedia.in/church/webservice/login?name=ritesh&mobile=7894562115&
    email=ritesh@gmail.com&password=123456&social_id=hgd235ddnjks&register_id=vshdh565&lat=7865.20&lon=5646&type=mobile*/
    public void login(String mobile, String email, String password, String lat, String lon, String register_id, String social_id, String type) {
        Map<String, String> data = new HashMap<>();
        data.put("mobile", mobile);
        data.put("email", email);
        data.put("password", password);
        data.put("lat", lat);
        data.put("lon", lon);
        data.put("register_id", register_id);
        data.put("social_id", social_id);
        data.put("type", type);
        signInPresenter.signIn(data);
    }

}