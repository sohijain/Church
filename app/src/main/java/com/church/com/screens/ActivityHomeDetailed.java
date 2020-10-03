package com.church.com.screens;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.church.com.R;
import com.church.com.utility.Constant;

public class ActivityHomeDetailed extends AppCompatActivity {

    private RelativeLayout rlMenuBack;
    private RelativeLayout rlClick;
    private TextView tvToolbarText;
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detailed);
        changeStatusBarColor();
        init();
    }


    private void init() {


        rlMenuBack = findViewById(R.id.rlMenuBack);
        rlClick = findViewById(R.id.rlClick);
        tvToolbarText = findViewById(R.id.tvToolbarText);
        tvText = findViewById(R.id.tvText);
        rlMenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (getIntent() != null) {

            int screen_index = getIntent().getIntExtra("screen_index", -1);
            String screen_index_title = getIntent().getStringExtra("screen_index_title");

            if (screen_index != -1) {
                rlClick.setVisibility(View.GONE);
            } else {
                rlClick.setVisibility(View.VISIBLE);

            }

            tvToolbarText.setText(screen_index_title);
            tvText.setText(screen_index_title);
        }

    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int color = ContextCompat.getColor(this, R.color.colorAccent);
            window.setStatusBarColor(color);
        }
    }
}
