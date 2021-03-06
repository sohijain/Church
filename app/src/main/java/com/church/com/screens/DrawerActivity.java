package com.church.com.screens;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.church.com.AppSettings;
import com.church.com.R;
import com.church.com.bible.BibleFragment;
import com.church.com.give.GiveFragment;
import com.church.com.home.HomeFragment;
import com.church.com.message.MessageFragment;
import com.church.com.model.BasicResponse;
import com.church.com.model.UserProfileResponse;
import com.church.com.presenter.ProfilePresenter;
import com.church.com.view_interface.ProfileInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ProfileInterface {

    private RelativeLayout mRlMenu;
    private BottomNavigationView mBottomNavigationMenu;
    boolean doubleBackToExitPressedOnce = false;
    ProfilePresenter profilePresenter;
    private TextView mTvNavHeaderName,mTvNavHeaderEmail;
    private ImageView mIvNavProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        changeStatusBarColor();
        initToolbar();
        setUpMVP();
        getUserProfile();
    }

    private void getUserProfile() {
        Map<String, String> data = new HashMap<>();
        data.put("user_id", AppSettings.getUserId(DrawerActivity.this));
        profilePresenter.getProfile(data);
    }

    private void setUpMVP() {
        profilePresenter = new ProfilePresenter(this);
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        toggle.setDrawerIndicatorEnabled(false);
        //toggle.setHomeAsUpIndicator(R.drawable.ic_custom_drawer_icon);


        toolbar.setNavigationIcon(R.drawable.path);

        drawer.addDrawerListener(toggle);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final View headerLayout = navigationView.getHeaderView(0);
        mTvNavHeaderName = headerLayout.findViewById(R.id.mTvNavHeaderName);
        mTvNavHeaderEmail = headerLayout.findViewById(R.id.mTvNavHeaderEmail);
        mIvNavProfile = headerLayout.findViewById(R.id.mIvNavProfile);

        navigationView.setNavigationItemSelectedListener(this);

        mRlMenu = findViewById(R.id.rlMenu);
        mRlMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawer.openDrawer(GravityCompat.START);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        init();

    }

    private void init() {

        mBottomNavigationMenu = findViewById(R.id.bottom_nav_view);
        mBottomNavigationMenu.setOnNavigationItemSelectedListener(navigationItemSelectedListener);


    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_frame, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }


    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.bottom_home:
                            mRlMenu.setVisibility(View.VISIBLE);
                            openFragment(new HomeFragment());
                            return true;
                        case R.id.bottom_messages:

                            mRlMenu.setVisibility(View.VISIBLE);
                            openFragment(new MessageFragment());
                            return true;
                        case R.id.bottom_give:
                            mRlMenu.setVisibility(View.VISIBLE);
                            openFragment(new GiveFragment());
                            return true;
                        case R.id.bottom_bibble:
                            mRlMenu.setVisibility(View.VISIBLE);
                            openFragment(new BibleFragment());
                            return true;
                    }
                    return false;
                }
            };


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displaySelectedFragment(item.getItemId());
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        mBottomNavigationMenu.setSelectedItemId(R.id.bottom_home);
    }

    private void displaySelectedFragment(int menuItemId) {
        Fragment fragment = null;
        switch (menuItemId) {
            case R.id.nav_home:
                mBottomNavigationMenu.setSelectedItemId(R.id.bottom_home);

                fragment = new HomeFragment();

                openFragment(fragment);
                break;
            case R.id.nav_prayers:
                Intent intent = new Intent(this, ActivityPrayerSubmit.class);
                startActivity(intent);
                break;
            case R.id.nav_donations:
                mBottomNavigationMenu.setSelectedItemId(R.id.bottom_give);
                fragment = new GiveFragment();
                openFragment(fragment);

                break;

            case R.id.nav_about:
                Intent intentAboutUs = new Intent(this, ActivityAboutUs.class);
                startActivity(intentAboutUs);
                break;


            default:
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // btnAlertDialogClicked();

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int color = ContextCompat.getColor(this, R.color.colorPrimary);
            window.setStatusBarColor(color);
        }
    }

    @Override
    public void onSuccess(BasicResponse bannerResponse) {

    }

    @Override
    public void onGetProfileSuccess(UserProfileResponse userProfileResponse) {
        if(userProfileResponse.getStatus().equals("1")){
            mTvNavHeaderName.setText(userProfileResponse.getLoginResult().getName());
            mTvNavHeaderEmail.setText(userProfileResponse.getLoginResult().getEmail());
            AppSettings.setUserName(DrawerActivity.this,userProfileResponse.getLoginResult().getName());
            AppSettings.setEmail(DrawerActivity.this,userProfileResponse.getLoginResult().getEmail());
            openFragment(new HomeFragment());
        }

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
}