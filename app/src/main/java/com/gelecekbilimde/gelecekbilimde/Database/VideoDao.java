package com.gelecekbilimde.gelecekbilimde.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;

import java.util.List;

@Dao
public interface VideoDao {

    @Insert
    void insertVideo(VideoModel video);

    @Update
    void updateVideo(VideoModel video);

    @Delete
    void deleteVideo(VideoModel video);

    @Query("DELETE FROM video_table")
    void deleteAllVideos();

    @Query("Select * From video_table")
    LiveData<List<VideoModel>> getAllVideos();
}
