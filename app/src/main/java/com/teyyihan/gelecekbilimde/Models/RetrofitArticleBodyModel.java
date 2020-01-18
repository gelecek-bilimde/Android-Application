package com.teyyihan.gelecekbilimde.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitArticleBodyModel {

    @SerializedName("content")
    @Expose
    private Content content;
    @SerializedName("link")
    @Expose
    private String link;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
