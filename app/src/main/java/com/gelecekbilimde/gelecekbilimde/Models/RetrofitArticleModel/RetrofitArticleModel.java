package com.gelecekbilimde.gelecekbilimde.Models.RetrofitArticleModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitArticleModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;


    @SerializedName("title")
    @Expose
    private Title title;


    @SerializedName("content")
    @Expose
    private Content content;


    @SerializedName("excerpt")
    @Expose
    private Excerpt excerpt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Excerpt getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(Excerpt excerpt) {
        this.excerpt = excerpt;
    }


}
