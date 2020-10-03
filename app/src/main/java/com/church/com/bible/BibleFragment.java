package com.church.com.bible;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.church.com.R;
import com.google.android.material.tabs.TabLayout;

public class BibleFragment extends Fragment {


    private Context mContext;
    private View view;

    private BibleFragmentA mBibleFragmentA;
    private BibleFragmentB mBibleFragmentB;

    private TabLayout mTableLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bible, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();

        init();


    }

    private void init() {

        mTableLayout = view.findViewById(R.id.tabs);
        setupTabLayout();

    }

    private void setupTabLayout() {
        mBibleFragmentA = new BibleFragmentA();
        mBibleFragmentB = new BibleFragmentB();

        mTableLayout.addTab(mTableLayout.newTab().setText("Bible"), 0 );
        mTableLayout.addTab(mTableLayout.newTab().setText("Reading Plan"), 1, true);

        replaceFragment(mBibleFragmentB);

        bindWidgetsWithAnEvent();
    }

    private void bindWidgetsWithAnEvent() {
        mTableLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case 0:
                replaceFragment(mBibleFragmentA);
                break;
            case 1:
                replaceFragment(mBibleFragmentB);
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.viewpager, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

}
