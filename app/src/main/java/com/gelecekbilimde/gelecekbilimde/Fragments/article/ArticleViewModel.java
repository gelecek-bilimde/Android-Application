package com.gelecekbilimde.gelecekbilimde.Fragments.article;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;
import com.gelecekbilimde.gelecekbilimde.Repository.ArticleRepository;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository articleRepository;
    private LiveData<List<ArticleModel>> allArticles;


    public ArticleViewModel(Application application) {
        super(application);
        articleRepository = new ArticleRepository(application);
        allArticles = articleRepository.getAllArticles();
    }

    public void insertArticle(ArticleModel article) {
        articleRepository.insertArticle(article);
    }

    public void deleteArticle(ArticleModel article) {
        articleRepository.deleteArticle(article);
    }

    public void deleteAllArticles() {
        articleRepository.deleteAllArticles();
    }

    public void updateArticle(ArticleModel article) {
        articleRepository.updateArticle(article);
    }

    public LiveData<List<ArticleModel>> getAllArticles() {
        return articleRepository.getAllArticles();
    }
    public void getTenArticlesfromfirebase() {
        articleRepository.getTenArticlesFromFirebase();
    }


}
