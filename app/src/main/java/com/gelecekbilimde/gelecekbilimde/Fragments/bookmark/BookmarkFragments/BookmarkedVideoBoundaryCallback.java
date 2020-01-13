package com.gelecekbilimde.gelecekbilimde.Fragments.bookmark.BookmarkFragments;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;
import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;
import com.gelecekbilimde.gelecekbilimde.Repository.ArticleRepository;
import com.gelecekbilimde.gelecekbilimde.Repository.VideoRepository;

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
