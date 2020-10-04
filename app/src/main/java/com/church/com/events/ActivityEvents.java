package com.church.com.events;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.church.com.R;
import com.church.com.screens.ActivityGathering;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityEvents extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, IEventsClick {

    private RelativeLayout rlMenuBack;

    private SliderLayout mEventsSlider;

    private RecyclerView mRvEvents;
    private EventsListAdapter mEventListAdapter;
    private List<EventsBean> mEventList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
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

        setslider();

        setEventsList();

    }

    private void setEventsList() {

        mRvEvents = findViewById(R.id.mRvEvents);

        mEventList = new ArrayList<>();
        mEventListAdapter = new EventsListAdapter(this, mEventList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRvEvents.setLayoutManager(mLayoutManager);
        mRvEvents.setAdapter(mEventListAdapter);

    }


    private void setslider() {

        mEventsSlider = (SliderLayout) findViewById(R.id.sliderEvents);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Church App", R.drawable.banner_a);
        file_maps.put("Church App1", R.drawable.banner_b);
        file_maps.put("Church App2", R.drawable.banner_c);
        file_maps.put("Church App3", R.drawable.banner_d);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mEventsSlider.addSlider(textSliderView);
        }
        mEventsSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mEventsSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mEventsSlider.setCustomAnimation(new DescriptionAnimation());
        mEventsSlider.setDuration(4000);
        mEventsSlider.addOnPageChangeListener(this);
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
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void IEventsClick() {

        Intent intent = new Intent(this, ActivityGathering.class);
        intent.putExtra("screen_index_title", "Events");
        startActivity(intent);
    }
}
