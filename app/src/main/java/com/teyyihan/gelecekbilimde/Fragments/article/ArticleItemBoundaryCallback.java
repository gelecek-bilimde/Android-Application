package com.teyyihan.gelecekbilimde.Fragments.article;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.teyyihan.gelecekbilimde.Models.ArticleModel;
import com.teyyihan.gelecekbilimde.Repository.ArticleRepository;

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
