package com.gelecekbilimde.gelecekbilimde.Network;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;
import com.gelecekbilimde.gelecekbilimde.Repository.ArticleRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ArticleFirebase {
    DatabaseReference myRef;
    FirebaseDatabase database ;
    private MutableLiveData<PagedList<ArticleModel>> searchedArticles = new MutableLiveData<>();
    private String lastkey="";

    public ArticleFirebase() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Articles");
    }


    public void getTenArticlesFromFirebase(final ArticleRepository articleRepository) {
        Query query ;
        if (TextUtils.isEmpty(lastkey)) {
            query = myRef.orderByChild("articleDate").limitToLast(10);
        } else {
            query = myRef.orderByChild("articleDate").limitToLast(10);
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArticleModel articleModel;
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot each: dataSnapshot.getChildren()) {
                        articleModel= each.getValue(ArticleModel.class);
                        articleRepository.insertArticle(articleModel);
                        lastkey = each.getKey();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }
}
