package com.teyyihan.gelecekbilimde.Fragments.bookmark.BookmarkFragments;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.teyyihan.gelecekbilimde.Models.ArticleModel;
import com.teyyihan.gelecekbilimde.Repository.ArticleRepository;

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
