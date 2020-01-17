package com.gelecekbilimde.teyyihan.Network;

import com.gelecekbilimde.teyyihan.Models.RetrofitArticleBodyModel;
import com.gelecekbilimde.teyyihan.Models.RetrofitArticleModel.RetrofitArticleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitArticleAPI {

    @GET("{id}")
    Call<RetrofitArticleBodyModel> getPost(@Path("id") int articleID);

    @GET("posts")
    Call<List<RetrofitArticleModel>> getPageOfPosts(@Query("page") Integer page);

}
