package com.gelecekbilimde.gelecekbilimde.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "video_table")
public class VideoModel {

    @PrimaryKey(autoGenerate = true)
    private int videoId;
    private String videoImageURL;
    private String videoTitle;
    private String videoDate;
    private String videoDesc;
    private boolean bookmarked;
    private String videoURLId;

    @Ignore
    public VideoModel() {

    }

    public VideoModel(String videoImageURL, String videoTitle, String videoDate, boolean bookmarked, String videoDesc, String videoURLId) {
        this.videoImageURL = videoImageURL;
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

    public String getVideoImageURL() {
        return videoImageURL;
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
