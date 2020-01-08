package com.gelecekbilimde.gelecekbilimde.Network;

import com.gelecekbilimde.gelecekbilimde.Models.RetrofitResponsePOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeRetrofitAPI {

    @GET("search")
    Call<RetrofitResponsePOJO> getVideos(@Query("part") String part,
                                               @Query("channelId") String channelId,
                                               @Query("maxResults") Integer maxResults,
                                               @Query("order") String order,
                                               @Query("key") String apiKey,
                                               @Query("pageToken") String pageToken);

}
