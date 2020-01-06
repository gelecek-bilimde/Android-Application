package com.gelecekbilimde.gelecekbilimde.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "video_table")
public class VideoModel {

    @PrimaryKey(autoGenerate = true)
    private int videoId;

    private int videoThumbnail;
    private int videoPublisherLogo;
    private int videoBookmarkLogo;
    private String videoTitle;
    private String videoDate;
    private boolean bookmarked;

    public VideoModel(int videoThumbnail, int videoPublisherLogo, int videoBookmarkLogo, String videoTitle, String videoDate, boolean bookmarked) {
        this.videoThumbnail = videoThumbnail;
        this.videoPublisherLogo = videoPublisherLogo;
        this.videoBookmarkLogo = videoBookmarkLogo;
        this.videoTitle = videoTitle;
        this.videoDate = videoDate;
        this.bookmarked = bookmarked;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getVideoThumbnail() {
        return videoThumbnail;
    }


    public int getVideoPublisherLogo() {
        return videoPublisherLogo;
    }



    public int getVideoBookmarkLogo() {
        return videoBookmarkLogo;
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
