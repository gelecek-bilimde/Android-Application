package com.gelecekbilimde.gelecekbilimde.Database;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;

import java.util.List;

@Dao
public interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertVideo(VideoModel video);

    @Update
    void updateVideo(VideoModel video);

    @Delete
    void deleteVideo(VideoModel video);

    @Query("DELETE FROM video_table")
    void deleteAllVideos();

    @Query("Select * From video_table order by videoDate desc")
    DataSource.Factory<Integer, VideoModel> getAllVideos();
}
