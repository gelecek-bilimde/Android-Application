package com.gelecekbilimde.teyyihan.Network;

import androidx.annotation.NonNull;

import com.gelecekbilimde.teyyihan.Fragments.article.ArticleViewModel;
import com.gelecekbilimde.teyyihan.Models.ArticleModel;
import com.gelecekbilimde.teyyihan.Models.RetrofitArticleModel.RetrofitArticleModel;
import com.gelecekbilimde.teyyihan.Repository.ArticleRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleFirebaseAndRetrofit {
    DatabaseReference myRef;
    FirebaseDatabase database ;
    private String lastkey="";

    public ArticleFirebaseAndRetrofit() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Articles");
    }


    public void getTenArticlesfromFirebaseAndRetrofit(final ArticleRepository articleRepository, int page) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.bilimtreni.com/wp-json/wp/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitArticleAPI api = retrofit.create(RetrofitArticleAPI.class);

        Call<List<RetrofitArticleModel>> call = api.getPageOfPosts(page);


        //RETROFIT
        call.enqueue(new Callback<List<RetrofitArticleModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitArticleModel>> call, Response<List<RetrofitArticleModel>> response) {
                if (response.isSuccessful()) {
                    if (response != null) {

                        for (RetrofitArticleModel each: response.body()) {


                            //FIREBASE
                            myRef.child(String.valueOf(each.getId())).child("articleImageURL").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String imageURL = dataSnapshot.getValue(String.class);



                                    ArticleModel article = new ArticleModel(each.getId(),each.getDate(),each.getExcerpt().getRendered(),
                                            imageURL,each.getTitle().getRendered(),false,page);
                                    articleRepository.insertArticle(article);
                                    ArticleViewModel.isLoading.postValue(false);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                    }
                } else {
                    ArticleViewModel.isLoading.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitArticleModel>> call, Throwable t) {

            }
        });

/*
        Query query = myRef.orderByChild("articleDate").limitToLast(10);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArticleModel articleModel;
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot each: dataSnapshot.getChildren()) {
                        articleModel= each.getValue(ArticleModel.class);
                        articleRepository.insertArticle(articleModel);
                        ArticleViewModel.isLoading.postValue(false);
                        lastkey = each.getKey();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });*/
    }
}
