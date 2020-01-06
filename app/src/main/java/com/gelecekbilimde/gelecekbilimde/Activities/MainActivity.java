package com.gelecekbilimde.gelecekbilimde.Activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.gelecekbilimde.gelecekbilimde.Adapters.BottomNavViewPagerAdapter;
import com.gelecekbilimde.gelecekbilimde.CustomViewPager;
import com.gelecekbilimde.gelecekbilimde.Fragments.profile.ProfileFragment;
import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.Fragments.article.ArticleFragment;
import com.gelecekbilimde.gelecekbilimde.Fragments.bookmark.BookmarkFragment;
import com.gelecekbilimde.gelecekbilimde.Fragments.video.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CustomViewPager viewPager;
    Fragment first = new ArticleFragment();
    Fragment second = new VideoFragment();
    Fragment third = new BookmarkFragment();
    Fragment fourth = new ProfileFragment();
    MenuItem menuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);
        viewPager = (CustomViewPager) findViewById(R.id.main_viewpager);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_first:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_second:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_third:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.navigation_fourth:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                menuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        BottomNavViewPagerAdapter myAdapter = new BottomNavViewPagerAdapter(getSupportFragmentManager());
        myAdapter.addFragment(first);
        myAdapter.addFragment(second);
        myAdapter.addFragment(third);
        myAdapter.addFragment(fourth);
        viewPager.setAdapter(myAdapter);
    }


}
