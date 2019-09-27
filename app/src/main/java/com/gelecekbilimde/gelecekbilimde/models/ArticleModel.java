package com.gelecekbilimde.gelecekbilimde.models;

import android.widget.ImageView;
import android.widget.TextView;

public class ArticleModel {
    private int articleImage;
    private int articleBookmark;
    private String articleHeadline;
    private String articleDescription;
    private String articleDate;

    public ArticleModel(int articleImage, int articleBookmark, String articleHeadline, String articleDescription, String articleDate) {
        this.articleImage = articleImage;
        this.articleBookmark = articleBookmark;
        this.articleHeadline = articleHeadline;
        this.articleDescription = articleDescription;
        this.articleDate = articleDate;
    }

    public int getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(int articleImage) {
        this.articleImage = articleImage;
    }

    public int getArticleBookmark() {
        return articleBookmark;
    }

    public void setArticleBookmark(int articleBookmark) {
        this.articleBookmark = articleBookmark;
    }

    public String getArticleHeadline() {
        return articleHeadline;
    }

    public void setArticleHeadline(String articleHeadline) {
        this.articleHeadline = articleHeadline;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }
}
