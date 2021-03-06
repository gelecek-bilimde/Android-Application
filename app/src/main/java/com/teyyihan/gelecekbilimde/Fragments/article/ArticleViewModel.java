package com.teyyihan.gelecekbilimde.Fragments.article;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.teyyihan.gelecekbilimde.Models.ArticleModel;
import com.teyyihan.gelecekbilimde.Repository.ArticleRepository;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository articleRepository;
    private LiveData<PagedList<ArticleModel>> allArticles;
    public static MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public ArticleViewModel(Application application) {
        super(application);
        articleRepository = new ArticleRepository(application);
        DataSource.Factory factory = articleRepository.getAllArticles();
        ArticleItemBoundaryCallback boundaryCallback = new ArticleItemBoundaryCallback(articleRepository);
        LivePagedListBuilder pagedListBuilder = new LivePagedListBuilder(factory,10).setBoundaryCallback(boundaryCallback);
        allArticles = pagedListBuilder.build();
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

    public LiveData<PagedList<ArticleModel>> getAllArticles() {
        return allArticles;
    }

    public void getTenArticlesfromFirebaseAndRetrofit(int page) {
        articleRepository.getTenArticlesfromFirebaseAndRetrofit(page);
    }


}
