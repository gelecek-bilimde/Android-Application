package com.gelecekbilimde.gelecekbilimde.Fragments.bookmark.BookmarkFragments;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.gelecekbilimde.gelecekbilimde.Fragments.article.ArticleItemBoundaryCallback;
import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;
import com.gelecekbilimde.gelecekbilimde.Repository.ArticleRepository;

public class BookmarkedArticleViewModel extends AndroidViewModel {
    private ArticleRepository articleRepository;
    private LiveData<PagedList<ArticleModel>> allBookmarkedArticles;


    public BookmarkedArticleViewModel(Application application) {
        super(application);
        articleRepository = new ArticleRepository(application);

        DataSource.Factory factory = articleRepository.getAllBookmarkedArticles();
        BookmarkedArticleBoundaryCallback boundaryCallback = new BookmarkedArticleBoundaryCallback(articleRepository);
        LivePagedListBuilder pagedListBuilder = new LivePagedListBuilder(factory,10).setBoundaryCallback(boundaryCallback);
        allBookmarkedArticles = pagedListBuilder.build();

        //allArticles = articleRepository.getAllArticles();
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

    public LiveData<PagedList<ArticleModel>> getAllBookmarkedArticles() {
        return allBookmarkedArticles;
    }

}
