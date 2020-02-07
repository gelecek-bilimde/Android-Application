package com.teyyihan.gelecekbilimde.Models.RetrofitArticleModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Excerpt {

    @SerializedName("rendered")
    @Expose
    private String rendered;

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

}
