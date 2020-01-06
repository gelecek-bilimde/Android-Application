package com.gelecekbilimde.gelecekbilimde.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.gelecekbilimde.gelecekbilimde.Database.ArticleDao;
import com.gelecekbilimde.gelecekbilimde.Database.MyDatabase;
import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;
import com.gelecekbilimde.gelecekbilimde.Network.ArticleFirebase;

import java.util.List;

public class ArticleRepository {

    private ArticleDao dao;
    private LiveData<List<ArticleModel>> allArticles;
    private ArticleFirebase articleFirebase;


    public ArticleRepository(Application application) {
        MyDatabase database = MyDatabase.getInstance(application);
        dao = database.articleDao();
        articleFirebase = new ArticleFirebase();
        allArticles = dao.getAllArticles();
    }

    public void insertArticle(ArticleModel article) {
        new InsertArticleAsync(dao).execute(article);
    }
    public void updateArticle(ArticleModel article) {
        new UpdateArticleAsync(dao).execute(article);
    }
    public void deleteArticle(ArticleModel article) {
        new DeleteArticleAsync(dao).execute(article);
    }
    public void deleteAllArticles() {
        new DeleteAllArticlesAsync(dao).execute();
    }

    public LiveData<List<ArticleModel>> getAllArticles(){
        return allArticles;
    }

    public void getTenArticlesFromFirebase(){
        articleFirebase.getTenArticlesFromFirebase(this);
    }




    private static class InsertArticleAsync  extends AsyncTask<ArticleModel,Void,Void>{
        private ArticleDao dao;

        public InsertArticleAsync(ArticleDao articleDao){
            this.dao = articleDao;
        }

        @Override
        protected Void doInBackground(ArticleModel... articleModels) {
            dao.insertArticle(articleModels[0]);
            return null;
        }
    }
    private static class UpdateArticleAsync  extends AsyncTask<ArticleModel,Void,Void>{
        private ArticleDao dao;

        public UpdateArticleAsync(ArticleDao articleDao){
            this.dao = articleDao;
        }

        @Override
        protected Void doInBackground(ArticleModel... articleModels) {
            dao.updateArticle(articleModels[0]);
            return null;
        }
    }
    private static class DeleteArticleAsync  extends AsyncTask<ArticleModel,Void,Void>{
        private ArticleDao dao;

        public DeleteArticleAsync(ArticleDao articleDao){
            this.dao = articleDao;
        }

        @Override
        protected Void doInBackground(ArticleModel... articleModels) {
            dao.deleteArticle(articleModels[0]);
            return null;
        }
    }
    private static class DeleteAllArticlesAsync  extends AsyncTask<Void,Void,Void>{
        private ArticleDao dao;

        public DeleteAllArticlesAsync(ArticleDao articleDao){
            this.dao = articleDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllArticles();
            return null;
        }
    }

}
