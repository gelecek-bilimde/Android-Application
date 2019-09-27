package com.gelecekbilimde.gelecekbilimde;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.gelecekbilimde.gelecekbilimde.adapters.VideoAdapter;
import com.gelecekbilimde.gelecekbilimde.models.VideoModel;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
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

        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1"));
        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1"));
        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1"));
        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1"));
        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1"));
        videos.add(new VideoModel(R.drawable.video,R.drawable.gelecekbilimdelogo,R.drawable.bookmark_unchecked,"ASD","1"));

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
    }
}
