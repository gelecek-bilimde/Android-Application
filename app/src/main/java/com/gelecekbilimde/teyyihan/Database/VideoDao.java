package com.gelecekbilimde.teyyihan.Database;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.gelecekbilimde.teyyihan.Models.VideoModel;

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

    @Query("Select * from video_table where bookmarked=1")
    DataSource.Factory<Integer, VideoModel> getAllBookmarkedVideos();
}
