package com.gelecekbilimde.teyyihan.Fragments.bookmark.BookmarkFragments;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.gelecekbilimde.teyyihan.Models.ArticleModel;
import com.gelecekbilimde.teyyihan.Repository.ArticleRepository;

public class BookmarkedArticleBoundaryCallback extends PagedList.BoundaryCallback<ArticleModel> {
    ArticleRepository repository;

    public BookmarkedArticleBoundaryCallback(ArticleRepository repository) {
        this.repository =repository;
    }

    @Override
    public void onItemAtEndLoaded(@NonNull ArticleModel itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);

    }
}
