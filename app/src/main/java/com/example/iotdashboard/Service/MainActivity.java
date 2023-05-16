package com.example.iotdashboard.Service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.iotdashboard.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    static DashBoardFragment homeFragment = new DashBoardFragment();
    static MainFragment searchFragment = new MainFragment();
    static SettingFragment settingFragment = new SettingFragment();
    static UserProfileFragment userProfileFragment = new UserProfileFragment();

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.view_pager);

        // Tạo adapter cho ViewPager
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // Đặt listener cho BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dash_board:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.main:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.chart:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.logs:
                        viewPager.setCurrentItem(3);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private static class PagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_PAGES = 4;

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return homeFragment;
                case 1:
                    return searchFragment;
                case 2:
                    return userProfileFragment;
                case 3:
                    return settingFragment;
                default:
                    return homeFragment;
            }
        }
    }

}