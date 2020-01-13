package com.gelecekbilimde.gelecekbilimde.Fragments.bookmark.BookmarkFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.Adapters.VideoAdapter;
import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;

import java.util.ArrayList;
import java.util.List;

public class BookmarkedVideoFragment extends Fragment {

    private RecyclerView mRecyclerview;
    private VideoAdapter mAdapter;
    private BookmarkedVideoViewModel videoViewModel;


    public BookmarkedVideoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.bookmarked_video_fragment,container,false);

        mRecyclerview = v.findViewById(R.id.bookmarked_video_recycler);
        mRecyclerview.setHasFixedSize(true) ;
        mRecyclerview.addItemDecoration(new DividerItemDecoration(mRecyclerview.getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new VideoAdapter(getContext());
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview.setAdapter(mAdapter);

        videoViewModel = ViewModelProviders.of(this).get(BookmarkedVideoViewModel.class);

        videoViewModel.getAllBookmarkedVideos().observe(this, new Observer<PagedList<VideoModel>>() {
            @Override
            public void onChanged(PagedList<VideoModel> videoModels) {
                mAdapter.submitList(videoModels);
            }
        });


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
