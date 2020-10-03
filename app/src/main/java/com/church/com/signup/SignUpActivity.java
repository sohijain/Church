package com.church.com.signup;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.church.com.R;
import com.church.com.model.BasicResponse;
import com.church.com.screens.DrawerActivity;
import com.church.com.signin.SigninActivity;
import com.church.com.utility.Util;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

public class SignUpActivity extends AppCompatActivity implements SignUpViewInterface, View.OnClickListener {

    private RelativeLayout rlsign;
    private RelativeLayout rlregister;
    private RelativeLayout ll;
    private TextView tvHeader;
    private RelativeLayout rlName;
    private ImageView imgUser;
    private AppCompatEditText etName;
    private RelativeLayout rlEmail;
    private ImageView imgUserEmail;
    private AppCompatEditText etEmail;
    private RelativeLayout rlphone;
    private ImageView imgUserPhone;
    private AppCompatEditText etPhone;
    private RelativeLayout rlPass;
    private ImageView imgUserPass;
    private AppCompatEditText etPass;
    private RelativeLayout rlcpass;
    private ImageView imgUsercPass;
    private AppCompatEditText etcPass;
    private TextView rlBottom;
    SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        changeStatusBarColor();
        initView();
        setListener();
        setUpMVP();
    }

    private void setListener() {
        rlregister.setOnClickListener(this);
        rlsign.setOnClickListener(this);
    }

    private void setUpMVP() {
        signUpPresenter = new SignUpPresenter(this);
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int color = ContextCompat.getColor(this, R.color.colorAccent);
            window.setStatusBarColor(color);
        }
    }

    @Override
    public void onSuccess(BasicResponse basicResponse) {
        Intent intent = new Intent(SignUpActivity.this, DrawerActivity.class);
        startActivity(intent);
        finish();
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
        ll = (RelativeLayout) findViewById(R.id.ll);
        tvHeader = (TextView) findViewById(R.id.tvHeader);
        rlName = (RelativeLayout) findViewById(R.id.rlName);
        imgUser = (ImageView) findViewById(R.id.imgUser);
        etName = (AppCompatEditText) findViewById(R.id.etName);
        rlEmail = (RelativeLayout) findViewById(R.id.rlEmail);
        imgUserEmail = (ImageView) findViewById(R.id.imgUserEmail);
        etEmail = (AppCompatEditText) findViewById(R.id.etEmail);
        rlphone = (RelativeLayout) findViewById(R.id.rlphone);
        imgUserPhone = (ImageView) findViewById(R.id.imgUserPhone);
        etPhone = (AppCompatEditText) findViewById(R.id.etPhone);
        rlPass = (RelativeLayout) findViewById(R.id.rlPass);
        imgUserPass = (ImageView) findViewById(R.id.imgUserPass);
        etPass = (AppCompatEditText) findViewById(R.id.etPass);
        rlcpass = (RelativeLayout) findViewById(R.id.rlcpass);
        imgUsercPass = (ImageView) findViewById(R.id.imgUsercPass);
        etcPass = (AppCompatEditText) findViewById(R.id.etcPass);
        rlBottom = (TextView) findViewById(R.id.rlBottom);
        rlsign = findViewById(R.id.rlsign);
        rlregister = findViewById(R.id.rlregister);
    }

    @Override
    public void onClick(View view) {
        if (view == rlregister) {
            if(!Util.isEmpty(etName.getText().toString().trim())){
                if(!Util.isEmpty(etPhone.getText().toString().trim())){
                    if(!Util.isEmpty(etEmail.getText().toString().trim())){
                        if(!Util.isEmpty(etPass.getText().toString().trim())){
                            createAccount(etName.getText().toString(), etPhone.getText().toString(), etEmail.getText().toString(), etPass.getText().toString(), "", "", "");
                        }
                        else {
                            showToast("Please enter password");
                        }
                    }
                    else {
                        showToast("Please enter email");
                    }
                }
                else {
                    showToast("Please enter phone");
                }
            }
            else {
                showToast("Please enter name");
            }
        }
        if(view==rlsign){
            Intent intent = new Intent(SignUpActivity.this, SigninActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void createAccount(String name, String mobile, String email, String password, String lat, String lon, String register_id) {
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("mobile", mobile);
        data.put("email", email);
        data.put("password", password);
        data.put("lat", lat);
        data.put("lon", lon);
        data.put("register_id", register_id);

        signUpPresenter.signUp(data);
    }
}
