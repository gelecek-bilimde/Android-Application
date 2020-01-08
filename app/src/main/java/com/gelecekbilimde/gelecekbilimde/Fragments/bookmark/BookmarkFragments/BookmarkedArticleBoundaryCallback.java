package com.gelecekbilimde.gelecekbilimde.Fragments.bookmark.BookmarkFragments;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;
import com.gelecekbilimde.gelecekbilimde.Repository.ArticleRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
