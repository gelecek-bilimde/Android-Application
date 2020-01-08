package com.gelecekbilimde.gelecekbilimde.Network;

import com.gelecekbilimde.gelecekbilimde.Models.RetrofitResponsePOJO;
import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;
import com.gelecekbilimde.gelecekbilimde.Models.VideoResponsePOJO.Item;
import com.gelecekbilimde.gelecekbilimde.Repository.VideoRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeVideoGetterNetwork {
    private static final String BASE_URL ="https://www.googleapis.com/youtube/v3/";
    private static final String API_KEY ="AIzaSyBDekJ58-CJ23a275yIzH1K8WTfhpEfZHI";
    private static final String PART = "snippet";
    public static final String CHANNEL_ID="UC03cpKIZShIWoSBhfVE5bog";
    public static final Integer MAX_RESULTS =10;
    public static final String ORDER ="date";

    String nextPageToken =null;

    public YoutubeVideoGetterNetwork() {
    }

    public void getTenVideosViaRetrofit(final VideoRepository videoRepository) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YoutubeRetrofitAPI api = retrofit.create(YoutubeRetrofitAPI.class);

        Call<RetrofitResponsePOJO> call = api.getVideos(PART,CHANNEL_ID,MAX_RESULTS,ORDER,API_KEY,nextPageToken);

        call.enqueue(new Callback<RetrofitResponsePOJO>() {
            @Override
            public void onResponse(Call<RetrofitResponsePOJO>call, Response<RetrofitResponsePOJO> response) {

                if (response.isSuccessful()) {
                    System.out.println("teoooo"+ response.body().getEtag());
                    nextPageToken = response.body().getNextPageToken();

                    VideoModel videoModel;
                    for (Item each: response.body().getItems()) {
                       videoModel = new VideoModel(each.getSnippet().getThumbnails().getHigh().getUrl(),//IMAGE URL
                              each.getSnippet().getTitle(),                                             //TITLE
                               each.getSnippet().getPublishedAt(),                                      //DATE
                               false,
                               each.getSnippet().getDescription(),
                               each.getId().getVideoId());

                       videoRepository.insertVideo(videoModel);
                    }

                }

            }

            @Override
            public void onFailure(Call<RetrofitResponsePOJO> call, Throwable t) {
                System.out.println("teoooo123"+ t.getMessage());
                System.out.println(t.getMessage());
            }
        });

    }
}
