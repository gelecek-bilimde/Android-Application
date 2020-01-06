package com.gelecekbilimde.gelecekbilimde.Fragments.video;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;
import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;
import com.gelecekbilimde.gelecekbilimde.Repository.ArticleRepository;
import com.gelecekbilimde.gelecekbilimde.Repository.VideoRepository;

import java.util.List;

public class VideoViewModel extends AndroidViewModel {

    private VideoRepository videoRepository;
    private LiveData<List<VideoModel>> allVideos;

    public VideoViewModel(@NonNull Application application) {
        super(application);
        videoRepository = new VideoRepository(application);
        allVideos = videoRepository.getAllVideos();
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
    public void deleteAllArticles() {
        videoRepository.deleteAllVideos();
    }
    public LiveData<List<VideoModel>> getAllVideos() {
        return allVideos;
    }
}