package com.church.com.home_message;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.church.com.R;
import com.church.com.message.MessageBean;
import com.church.com.message.MessageListAdapter;
import com.church.com.screens.ActivityBibleDetailed;
import com.church.com.screens.ActivityHomeMessageDetail;

import java.util.ArrayList;
import java.util.List;

public class ActivityHomeMessage extends AppCompatActivity implements IClickHomeMessageDetailed{

    private RelativeLayout rlMenuBack;
    private RecyclerView mRvHomeMessage;
    private HomeMessageListAdapter mHomeMessageListAdapter;
    private List<HomeMessageBean> mMessageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_message);
        changeStatusBarColor();
        init();
    }


    private void init() {
        rlMenuBack = findViewById(R.id.rlMenuBack);
        rlMenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setMessageList();
    }


    private void setMessageList() {

        mRvHomeMessage = findViewById(R.id.mRvHomeMessage);

        mMessageList = new ArrayList<>();
        mHomeMessageListAdapter = new HomeMessageListAdapter(this, mMessageList,this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRvHomeMessage.setLayoutManager(mLayoutManager);
        mRvHomeMessage.setAdapter(mHomeMessageListAdapter);

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
    public void IClickHomeMessageDetailed() {
        Intent intent = new Intent(this, ActivityHomeMessageDetail.class);
        startActivity(intent);
    }
}
