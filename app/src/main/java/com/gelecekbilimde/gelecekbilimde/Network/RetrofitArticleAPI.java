package com.gelecekbilimde.gelecekbilimde.Network;

import com.gelecekbilimde.gelecekbilimde.Models.RetrofitArticleModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitArticleAPI {

    @GET("{id}")
    Call<RetrofitArticleModel> getPost(@Path("id") int articleID);

}
