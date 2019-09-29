package com.gelecekbilimde.gelecekbilimde.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.adapters.BookmarkViewPagerAdapter;
import com.gelecekbilimde.gelecekbilimde.fragments.BookmarkedArticleFragment;
import com.gelecekbilimde.gelecekbilimde.fragments.BookmarkedVideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.tabs.TabLayout;

public class BookmarkActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    BookmarkViewPagerAdapter bookmarkViewPagerAdapter;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        Toolbar toolbar =  findViewById(R.id.bookmark_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kaydedilenler");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        tabLayout = findViewById(R.id.bookmark_tablayout);
        viewPager = findViewById(R.id.bookmark_viewpager);
        bookmarkViewPagerAdapter = new BookmarkViewPagerAdapter( getSupportFragmentManager());

        setupBottomNavigation();

        bookmarkViewPagerAdapter.addFragment(new BookmarkedArticleFragment(), "Articles");
        bookmarkViewPagerAdapter.addFragment(new BookmarkedVideoFragment(), "Videos");

        viewPager.setAdapter(bookmarkViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);

        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_third);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_first:
                        intent = new Intent(BookmarkActivity.this, ArticleActivity.class);
                        startActivity(intent);
                        showTransitionAnim();
                        break;
                    case R.id.bottom_nav_second:
                        intent = new Intent(BookmarkActivity.this, VideoActivity.class);
                        startActivity(intent);
                        showTransitionAnim();
                        break;
                    case R.id.bottom_nav_third:
                        break;
                }
                return false;
            }
        });
    }

    private void showTransitionAnim() {
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
