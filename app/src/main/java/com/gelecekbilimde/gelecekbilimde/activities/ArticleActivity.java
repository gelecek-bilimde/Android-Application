package com.gelecekbilimde.gelecekbilimde.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.adapters.ArticleAdapter;
import com.gelecekbilimde.gelecekbilimde.models.ArticleModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity {
    MaterialSearchView searchView;
    BottomNavigationView bottomNavigationView;
    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        ArrayList<ArticleModel> articles = new ArrayList<>();
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline1" ,"desc1", "11.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline2" ,"desc2", "12.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline3" ,"desc3", "13.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline4" ,"desc4", "14.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline4" ,"desc4", "14.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline4" ,"desc4", "14.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline4" ,"desc4", "14.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline4" ,"desc4", "14.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline4" ,"desc4", "14.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline4" ,"desc4", "14.03.1999",false));

        mRecyclerview = findViewById(R.id.article_recyclerview);
        mSwipeRefreshLayout = findViewById(R.id.article_swipe);
        mRecyclerview.setHasFixedSize(true) ;
        mRecyclerview.addItemDecoration(new DividerItemDecoration(mRecyclerview.getContext(), DividerItemDecoration.VERTICAL));
        mLayoutManager  = new LinearLayoutManager(this);
        mAdapter = new ArticleAdapter(articles);

        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);

        Toolbar toolbar =  findViewById(R.id.article_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Makaleler");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        searchView = findViewById(R.id.article_search_view);
        searchView.setBackgroundColor(Color.parseColor("#EBEBEB"));
        searchView.setHint("Ara");
        setUpBottomNavigationBar();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(ArticleActivity.this, "Yenilendi", Toast.LENGTH_LONG).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setUpBottomNavigationBar() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);

        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_first);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_first:
                        break;
                    case R.id.bottom_nav_second:
                        intent = new Intent(ArticleActivity.this, VideoActivity.class);
                        startActivity(intent);
                        showTransitionAnim();
                        break;
                    case R.id.bottom_nav_third:
                        intent = new Intent(ArticleActivity.this, BookmarkActivity.class);
                        startActivity(intent);
                        showTransitionAnim();
                        break;
                }
                return false;
            }
        });
    }

    private void showTransitionAnim() {
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_view_item);
        searchView.setMenuItem(item);
        return true;
    }
}
