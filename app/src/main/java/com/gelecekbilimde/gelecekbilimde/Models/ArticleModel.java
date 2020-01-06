package com.gelecekbilimde.gelecekbilimde.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_table")
public class ArticleModel {

    @PrimaryKey(autoGenerate = true)
    private int articleId;
    private String articleAuthor;
    private String articleBody;
    private String articleDate;
    private String articleDesc;
    private String articleImageURL;
    private String articleTitle;
    private boolean bookmarked;

    @Ignore
    public ArticleModel() {
    }

    public ArticleModel( String articleAuthor, String articleBody, String articleDate, String articleDesc, String articleImageURL, String articleTitle, boolean bookmarked) {
        this.articleAuthor = articleAuthor;
        this.articleBody = articleBody;
        this.articleDate = articleDate;
        this.articleDesc = articleDesc;
        this.articleImageURL = articleImageURL;
        this.articleTitle = articleTitle;
        this.bookmarked = bookmarked;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
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
