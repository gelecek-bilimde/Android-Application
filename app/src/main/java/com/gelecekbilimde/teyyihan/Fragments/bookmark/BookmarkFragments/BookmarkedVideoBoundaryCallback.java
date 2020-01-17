package com.gelecekbilimde.teyyihan.Fragments.bookmark.BookmarkFragments;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.gelecekbilimde.teyyihan.Models.VideoModel;
import com.gelecekbilimde.teyyihan.Repository.VideoRepository;

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
