package com.gelecekbilimde.gelecekbilimde.Fragments.article;

import android.text.TextUtils;

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

public class ItemBoundaryCallback extends PagedList.BoundaryCallback<ArticleModel> {
    ArticleRepository repository;

    public ItemBoundaryCallback(ArticleRepository repository) {
        this.repository =repository;
    }

    @Override
    public void onItemAtEndLoaded(@NonNull ArticleModel itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Articles");

        System.out.println("teoooooo"+itemAtEnd.getArticleTitle()+"   "+itemAtEnd.getArticleId());

        Query query = myRef.orderByChild("articleDate").endAt(itemAtEnd.getArticleDate()).limitToLast(5);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArticleModel articleModel;
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot each: dataSnapshot.getChildren()) {
                        articleModel= each.getValue(ArticleModel.class);
                        repository.insertArticle(articleModel);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }
}
