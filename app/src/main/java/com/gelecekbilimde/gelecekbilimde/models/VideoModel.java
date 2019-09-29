package com.gelecekbilimde.gelecekbilimde.models;

public class VideoModel {
    private int videoThumbnail;
    private int videoPublisherLogo;
    private int videoBookmarkLogo;
    private String videoTitle;
    private String videoDate;
    private boolean isBookmarked;

    public VideoModel(int videoThumbnail, int videoPublisherLogo, int videoBookmarkLogo, String videoTitle, String videoDate, boolean isBookmarked) {
        this.videoThumbnail = videoThumbnail;
        this.videoPublisherLogo = videoPublisherLogo;
        this.videoBookmarkLogo = videoBookmarkLogo;
        this.videoTitle = videoTitle;
        this.videoDate = videoDate;
        this.isBookmarked = isBookmarked;
    }

    public int getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(int videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public int getVideoPublisherLogo() {
        return videoPublisherLogo;
    }

    public void setVideoPublisherLogo(int videoPublisherLogo) {
        this.videoPublisherLogo = videoPublisherLogo;
    }

    public int getVideoBookmarkLogo() {
        return videoBookmarkLogo;
    }

    public void setVideoBookmarkLogo(int videoBookmarkLogo) {
        this.videoBookmarkLogo = videoBookmarkLogo;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDate() {
        return videoDate;
    }

    public void setVideoDate(String videoDate) {
        this.videoDate = videoDate;
    }

    public boolean getIsBookmarked() {
        return isBookmarked;
    }

    public void setIsBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }
}
