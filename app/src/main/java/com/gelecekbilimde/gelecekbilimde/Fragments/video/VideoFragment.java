package com.gelecekbilimde.gelecekbilimde.Fragments.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gelecekbilimde.gelecekbilimde.Activities.MainActivity;
import com.gelecekbilimde.gelecekbilimde.Adapters.VideoAdapter;
import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;
import com.gelecekbilimde.gelecekbilimde.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class VideoFragment extends Fragment {

    private VideoViewModel videoViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        recyclerView = view.findViewById(R.id.video_recycler);
        swipeRefreshLayout = view.findViewById(R.id.videos_swipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdapter = new VideoAdapter(getContext());
        recyclerView.setAdapter(videoAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                videoViewModel.deleteAllVideos();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        videoViewModel.getAllVideos().observe(this, new Observer<PagedList<VideoModel>>() {
            @Override
            public void onChanged(PagedList<VideoModel> videoModels) {
                videoAdapter.submitList(videoModels);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.action_search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
        ((MainActivity)getActivity()).setTitle("Videolar");
    }
}