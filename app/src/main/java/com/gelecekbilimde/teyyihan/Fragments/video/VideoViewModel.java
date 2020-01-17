package com.gelecekbilimde.teyyihan.Fragments.video;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.gelecekbilimde.teyyihan.Models.VideoModel;
import com.gelecekbilimde.teyyihan.Repository.VideoRepository;

public class VideoViewModel extends AndroidViewModel {

    private VideoRepository videoRepository;
    private LiveData<PagedList<VideoModel>> allVideos;
    public static MutableLiveData<Boolean> isLoading =new MutableLiveData<>();

    public VideoViewModel(@NonNull Application application) {
        super(application);
        videoRepository = new VideoRepository(application);

        DataSource.Factory factory = videoRepository.getAllVideos();
        VideoItemBoundaryCallback boundaryCallback = new VideoItemBoundaryCallback(videoRepository);
        LivePagedListBuilder pagedListBuilder = new LivePagedListBuilder(factory,3).setBoundaryCallback(boundaryCallback);

        allVideos = pagedListBuilder.build();

    }

    public void insertVideo(VideoModel video) {
        videoRepository.insertVideo(video);
    }
    public void updateVideo(VideoModel video) {
        videoRepository.updateVideo(video);
    }
    public void deleteVideo(VideoModel video) {
        videoRepository.deleteVideo(video);
    }
    public void deleteAllVideos() {
        videoRepository.deleteAllVideos();
    }
    public LiveData<PagedList<VideoModel>> getAllVideos() {
        return allVideos;
    }
    public void getTenVideosFromFirebase() {
        videoRepository.getTenVideosFromFirebase();
    }
}