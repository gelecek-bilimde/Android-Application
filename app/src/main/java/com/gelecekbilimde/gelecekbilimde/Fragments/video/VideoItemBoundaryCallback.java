package com.gelecekbilimde.gelecekbilimde.Fragments.video;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;
import com.gelecekbilimde.gelecekbilimde.Network.YoutubeVideoGetterNetwork;
import com.gelecekbilimde.gelecekbilimde.Repository.VideoRepository;

public class VideoItemBoundaryCallback extends PagedList.BoundaryCallback<VideoModel> {

    private VideoRepository repository;

    public VideoItemBoundaryCallback(VideoRepository videoRepository) {
        this.repository = videoRepository;
    }

    @Override
    public void onItemAtEndLoaded(@NonNull VideoModel itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);

        YoutubeVideoGetterNetwork getterNetwork = new YoutubeVideoGetterNetwork();

        getterNetwork.getTenVideosViaRetrofit(repository);
        //TODO: GET VIDEOS WITH RETROFIT AND INSERT THEM


    }
}
