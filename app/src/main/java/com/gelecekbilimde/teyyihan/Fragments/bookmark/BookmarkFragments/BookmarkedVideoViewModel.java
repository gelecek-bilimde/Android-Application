package com.gelecekbilimde.teyyihan.Fragments.bookmark.BookmarkFragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.gelecekbilimde.teyyihan.Models.VideoModel;
import com.gelecekbilimde.teyyihan.Repository.VideoRepository;

public class BookmarkedVideoViewModel extends AndroidViewModel {

    private VideoRepository videoRepository;
    private LiveData<PagedList<VideoModel>> allBookmarkedVideos;

    public BookmarkedVideoViewModel(@NonNull Application application) {
        super(application);
        videoRepository = new VideoRepository(application);

        DataSource.Factory factory = videoRepository.getAllBookmarkedVideos();
        BookmarkedVideoBoundaryCallback boundaryCallback = new BookmarkedVideoBoundaryCallback(videoRepository);
        LivePagedListBuilder pagedListBuilder = new LivePagedListBuilder(factory,10).setBoundaryCallback(boundaryCallback);
        allBookmarkedVideos = pagedListBuilder.build();
    }

    public LiveData<PagedList<VideoModel>> getAllBookmarkedVideos() {
        return allBookmarkedVideos;
    }
}
