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

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleItemBoundaryCallback extends PagedList.BoundaryCallback<ArticleModel> {
    ArticleRepository repository;

    public ArticleItemBoundaryCallback(ArticleRepository repository) {
        this.repository =repository;
    }


    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        repository.getTenArticlesfromFirebaseAndRetrofit(1);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull ArticleModel itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);

        int page=0;
        if (itemAtEnd.getPage() == 0) {
            page = itemAtEnd.getPage() + 2;
        } else {
            page = itemAtEnd.getPage()+1;
        }

        System.out.println("teooo"+itemAtEnd.getPage());
       repository.getTenArticlesfromFirebaseAndRetrofit(page);

        /*
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Articles");

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


        });*/

    }
}
