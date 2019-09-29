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
import android.view.MenuItem;
import android.widget.Toast;

import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.adapters.VideoAdapter;
import com.gelecekbilimde.gelecekbilimde.models.VideoModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView videoRecycler;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ArrayList<VideoModel> videos = new ArrayList<>();

        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1",false));
        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1",false));
        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1",false));
        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1",false));
        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1",false));
        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1",false));

        swipeRefreshLayout = findViewById(R.id.video_swipe);
        videoRecycler = findViewById(R.id.video_recyclerview);
        videoRecycler.setHasFixedSize(true);
        videoRecycler.addItemDecoration(new DividerItemDecoration(videoRecycler.getContext(), DividerItemDecoration.VERTICAL));
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new VideoAdapter(videos);

        videoRecycler.setAdapter(mAdapter);
        videoRecycler.setLayoutManager(mLayoutManager);

        Toolbar toolbar =  findViewById(R.id.video_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Videolar");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        setUpBottomNavigationBar();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(VideoActivity.this, "Yenilendi", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }
    private void setUpBottomNavigationBar() {

            bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);

            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_second);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Intent intent;
                    switch (menuItem.getItemId()){
                        case R.id.bottom_nav_first:
                            intent = new Intent(VideoActivity.this, ArticleActivity.class);
                            startActivity(intent);
                            showTransitionAnim();
                            break;
                        case R.id.bottom_nav_second:
                            break;
                        case R.id.bottom_nav_third:
                            intent = new Intent(VideoActivity.this, BookmarkActivity.class);
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
}
