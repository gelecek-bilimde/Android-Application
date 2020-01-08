package com.gelecekbilimde.gelecekbilimde.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "video_table")
public class VideoModel {

    @PrimaryKey(autoGenerate = true)
    private int videoId;

    private String videoThumbnail;
    private String videoTitle;
    private String videoDate;
    private String videoDesc;
    private boolean bookmarked;
    private String videoURLId;

    public VideoModel(String videoThumbnail, String videoTitle, String videoDate, boolean bookmarked,String videoDesc,String videoURLId) {
        this.videoThumbnail = videoThumbnail;
        this.videoTitle = videoTitle;
        this.videoDate = videoDate;
        this.bookmarked = bookmarked;
        this.videoDesc = videoDesc;
        this.videoURLId =videoURLId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public String getVideoURLId() {
        return videoURLId;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public String getVideoTitle() {
        return videoTitle;
    }


    public String getVideoDate() {
        return videoDate;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }
}
