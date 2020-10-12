package com.church.com.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.church.com.R;
import com.church.com.events.ActivityEvents;
import com.church.com.home_message.ActivityHomeMessage;
import com.church.com.model.BannerResponse;
import com.church.com.presenter.BannerPresenter;
import com.church.com.screens.ActivityGathering;
import com.church.com.screens.ActivityHomeDetailed;
import com.church.com.screens.ActivityPrayerSubmit;
import com.church.com.screens.SignInActivity;
import com.church.com.utility.Constant;
import com.church.com.utility.Util;
import com.church.com.view_interface.BannerInterface;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, IClickHomeDetailed, BannerInterface {

    private SliderLayout mDemoSlider;
    private Context mContext;
    private View view;
    private RecyclerView recyclerView;
    private WatchListAdapter mWatchListAdapter;
    private List<WatchBean> mWatchList;
    private Button mCLPrayer;
    BannerPresenter bannerPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        setUpMVP();
        getBanner();
        return view;
    }

    private void getBanner() {
        bannerPresenter.getBanner();
    }

    private void setUpMVP() {
        bannerPresenter=new BannerPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }


    private void init() {


        setslider();

        setWatchList();

        mCLPrayer = view.findViewById(R.id.btnReq);
        mCLPrayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ActivityPrayerSubmit.class);
                startActivity(intent);
            }
        });


    }

    private void setWatchList() {

        recyclerView = view.findViewById(R.id.mRvHomeLocalContact);

        mWatchList = new ArrayList<>();

        setList();
        mWatchListAdapter = new WatchListAdapter(mContext, mWatchList, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mWatchListAdapter);

    }

    private void setList() {


        WatchBean watchBean = new WatchBean();
        watchBean.setStrText("Watch Live");

        mWatchList.add(watchBean);

        WatchBean watchBean1 = new WatchBean();
        watchBean1.setStrText("Events");

        mWatchList.add(watchBean1);


        WatchBean watchBean2 = new WatchBean();
        watchBean2.setStrText("Today's prayer");

        mWatchList.add(watchBean2);


        WatchBean watchBean3 = new WatchBean();
        watchBean3.setStrText("Messages");

        mWatchList.add(watchBean3);


        WatchBean watchBean4 = new WatchBean();
        watchBean4.setStrText("Sunday Messages");

        mWatchList.add(watchBean4);

        WatchBean watchBean5 = new WatchBean();
        watchBean5.setStrText("Sunday gathering");

        mWatchList.add(watchBean5);

        WatchBean watchBean6 = new WatchBean();
        watchBean6.setStrText("Inetrcessor");

        mWatchList.add(watchBean6);


    }


    private void setslider() {

        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Church App", R.drawable.banner_a);
        file_maps.put("Church App1", R.drawable.banner_b);
        file_maps.put("Church App2", R.drawable.banner_c);
        file_maps.put("Church App3", R.drawable.banner_d);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(mContext);
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

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
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
    public void IClickHomeDetailed(WatchBean aWatchBean) {
        Intent intent = null;
        if (aWatchBean.getStrText().equals("Events")) {
            intent = new Intent(mContext, ActivityEvents.class);
            startActivity(intent);

        } else if (aWatchBean.getStrText().equals("Watch Live")) {
            intent = new Intent(mContext, ActivityHomeDetailed.class);
            intent.putExtra("screen_index_title", "Watch Live");

            startActivity(intent);

        } else if (aWatchBean.getStrText().equals("Today's prayer")) {
            intent = new Intent(mContext, ActivityHomeDetailed.class);
            intent.putExtra("screen_index", Constant.ACTIVITY_CALL_INDEX);
            intent.putExtra("screen_index_title", "Today's prayer");
            startActivity(intent);

        } else if (aWatchBean.getStrText().equals("Messages")) {
            intent = new Intent(mContext, ActivityHomeMessage.class);
            startActivity(intent);
        } else if (aWatchBean.getStrText().equals("Sunday gathering")) {
            intent = new Intent(mContext, ActivityGathering.class);
            intent.putExtra("screen_index_title", "Sunday gathering");
            startActivity(intent);
        } else if (aWatchBean.getStrText().equals("Inetrcessor")) {

        } else {
            intent = new Intent(mContext, ActivityHomeDetailed.class);
            intent.putExtra("screen_index", Constant.ACTIVITY_CALL_INDEX);
            intent.putExtra("screen_index_title", "Sunday Messages");
            startActivity(intent);
        }

    }

    @Override
    public void onSuccess(BannerResponse bannerResponse) {
        Log.e(getTag(),"banner list size..."+bannerResponse.getBannerData().size());
    }

    @Override
    public void showToast(String s) {
        Util.ShowToast(getActivity(),s);

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
}