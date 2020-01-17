package com.gelecekbilimde.teyyihan.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.gelecekbilimde.teyyihan.Adapters.BottomNavViewPagerAdapter;
import com.gelecekbilimde.teyyihan.CustomViewPager;
import com.gelecekbilimde.teyyihan.Fragments.profile.ProfileFragment;
import com.gelecekbilimde.teyyihan.R;
import com.gelecekbilimde.teyyihan.Fragments.article.ArticleFragment;
import com.gelecekbilimde.teyyihan.Fragments.bookmark.BookmarkFragment;
import com.gelecekbilimde.teyyihan.Fragments.video.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    MaterialSearchView materialSearchView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);
        viewPager =  findViewById(R.id.main_viewpager);
        toolbar = findViewById(R.id.tooooooooolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitle("Makaleler");
        materialSearchView = findViewById(R.id.seaaaaaaarch);

        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArticleFragment.title.postValue(query);
                 return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_first:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_second:
                        viewPager.setCurrentItem(1);
                        materialSearchView.closeSearch();
                        break;
                    case R.id.navigation_third:
                        viewPager.setCurrentItem(2);
                        materialSearchView.closeSearch();
                        break;
                    case R.id.navigation_fourth:
                        viewPager.setCurrentItem(3);
                        materialSearchView.closeSearch();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_search_menu,menu);
        MenuItem mmmmm = menu.findItem(R.id.action_search);
        materialSearchView.setMenuItem(mmmmm);
        return super.onCreateOptionsMenu(menu);
    }

    public void  setTitle(String title) {
        toolbar.setTitle(title);
    }

}
