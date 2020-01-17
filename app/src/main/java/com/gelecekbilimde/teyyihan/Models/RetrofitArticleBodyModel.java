package com.gelecekbilimde.teyyihan.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitArticleBodyModel {

    @SerializedName("content")
    @Expose
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}
