package com.gelecekbilimde.gelecekbilimde.Fragments.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gelecekbilimde.gelecekbilimde.Adapters.VideoAdapter;
import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;
import com.gelecekbilimde.gelecekbilimde.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class VideoFragment extends Fragment {

    private VideoViewModel videoViewModel;

    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        recyclerView = view.findViewById(R.id.video_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdapter = new VideoAdapter();
        recyclerView.setAdapter(videoAdapter);

        videoViewModel.getAllVideos().observe(this, new Observer<List<VideoModel>>() {
            @Override
            public void onChanged(List<VideoModel> videoModels) {
                videoAdapter.submitList(videoModels);
            }
        });

        return view;
    }
}