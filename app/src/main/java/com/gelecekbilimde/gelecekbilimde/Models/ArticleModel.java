package com.gelecekbilimde.gelecekbilimde.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_table")
public class ArticleModel {

    @PrimaryKey(autoGenerate = true)
    private int articleId;
    private String articleDate;
    private String articleImageURL;
    private String articleTitle;
    private String articleDesc;
    private boolean bookmarked;

    @Ignore
    public ArticleModel() {
    }

    public ArticleModel(  String articleDate, String articleDesc, String articleImageURL, String articleTitle, boolean bookmarked) {
        this.articleDate = articleDate;
        this.articleImageURL = articleImageURL;
        this.articleTitle = articleTitle;
        this.bookmarked = bookmarked;
        this.articleDesc = articleDesc;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }


    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }

    public String getArticleImageURL() {
        return articleImageURL;
    }

    public void setArticleImageURL(String articleImageURL) {
        this.articleImageURL = articleImageURL;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }
}
