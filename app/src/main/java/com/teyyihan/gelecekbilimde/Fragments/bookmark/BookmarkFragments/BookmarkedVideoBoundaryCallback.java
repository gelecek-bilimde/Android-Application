package com.teyyihan.gelecekbilimde.Fragments.bookmark.BookmarkFragments;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.teyyihan.gelecekbilimde.Models.VideoModel;
import com.teyyihan.gelecekbilimde.Repository.VideoRepository;

public class BookmarkedVideoBoundaryCallback extends PagedList.BoundaryCallback<VideoModel> {
    VideoRepository repository;

    public BookmarkedVideoBoundaryCallback(VideoRepository repository) {
        this.repository =repository;
    }

    @Override
    public void onItemAtEndLoaded(@NonNull VideoModel itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);

    }
}
