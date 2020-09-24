package com.church.com.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.church.com.R;
import com.church.com.utility.Constant;
import com.church.com.utility.StorageUtils;

public class IntroScreenActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mDotsLayout;
    private int[] mLayouts;
    private Button mText_introSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        changeStatusBarColor();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mViewPager = (ViewPager) findViewById(R.id.pagerIntroSlider);
        mDotsLayout = (LinearLayout) findViewById(R.id.tabs);

        mLayouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};
        addBottomDots(0);
        mText_introSkip = findViewById(R.id.btnSkip);
        MyViewPagerAdapter mViewPagerAdapter = new MyViewPagerAdapter();
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        mText_introSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });
    }

    private void addBottomDots(int currentPage) {
        ImageView[] mDots = new ImageView[mLayouts.length];

        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new ImageView(this);
            mDots[i].setBackgroundResource(R.drawable.ic_intro_white);
            mDotsLayout.addView(mDots[i]);
            if (i != mDots.length - 1) {
                ImageView hiddenDotIv = new ImageView(this);
                hiddenDotIv.setBackgroundResource(R.drawable.selected_activedots);
                hiddenDotIv.setVisibility(View.INVISIBLE);
                mDotsLayout.addView(hiddenDotIv);
            }
        }
        if (mDots.length > 0)
            mDots[currentPage].setBackgroundResource(R.drawable.ic_intro_red);
    }


    private void launchHomeScreen() {
        firstCheckLaunch();

        Intent intent = new Intent(IntroScreenActivity.this, SigninActivity.class);
        //intent.putExtra(Constant.INTENT_ENTRY_POINT_FOR_LOGIN_SCREEN,Constant.LOGIN_ENTRY_POINT_BY_INTRO_ACTIVITY);
        startActivity(intent);
        finish();
    }


    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(mLayouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return mLayouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private void firstCheckLaunch() {
        StorageUtils.putPref(this, Constant.PREF_FIRST_LAUNCH, true);
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
