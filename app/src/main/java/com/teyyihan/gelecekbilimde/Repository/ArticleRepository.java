package com.teyyihan.gelecekbilimde.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.paging.DataSource;

import com.teyyihan.gelecekbilimde.Database.ArticleDao;
import com.teyyihan.gelecekbilimde.Database.MyDatabase;
import com.teyyihan.gelecekbilimde.Models.ArticleModel;
import com.teyyihan.gelecekbilimde.Network.ArticleFirebaseAndRetrofit;

public class ArticleRepository {

    private ArticleDao dao;

    private DataSource.Factory<Integer, ArticleModel> allArticles;
    private DataSource.Factory<Integer, ArticleModel> allBookmarkedArticles;
    private ArticleFirebaseAndRetrofit articleFirebaseAndRetrofit;


    public ArticleRepository(Application application) {
        MyDatabase database = MyDatabase.getInstance(application);
        dao = database.articleDao();


        articleFirebaseAndRetrofit = new ArticleFirebaseAndRetrofit();
        allArticles = dao.getAllArticles();
        allBookmarkedArticles = dao.getAllBookmarkedArticles();
    }

    public void insertArticle(ArticleModel article) {
        new InsertArticleAsync(dao).execute(article);
    }

    public void updateArticle(ArticleModel article) {
        new UpdateArticleAsync(dao).execute(article);
    }

    public void updateArticleImage(String imageURL,int articleID) {
        new UpdateArticleImageAsync(dao).execute(new ArticleWithImage(imageURL,articleID));
    }


    public void deleteArticle(ArticleModel article) {
        new DeleteArticleAsync(dao).execute(article);
    }
    public void deleteAllArticles() {
        new DeleteAllArticlesAsync(dao).execute();
    }

    public DataSource.Factory<Integer, ArticleModel> getAllArticles(){
        return allArticles;
    }

    public DataSource.Factory<Integer, ArticleModel> getAllBookmarkedArticles(){
        return allBookmarkedArticles;
    }

    public void getTenArticlesfromFirebaseAndRetrofit(int page){
        articleFirebaseAndRetrofit.getTenArticlesfromFirebaseAndRetrofit(this,page);
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

    private static class UpdateArticleImageAsync  extends AsyncTask<ArticleWithImage,Void,Void>{
        private ArticleDao dao;

        public UpdateArticleImageAsync(ArticleDao articleDao){
            this.dao = articleDao;
        }

        @Override
        protected Void doInBackground(ArticleWithImage... articleModels) {
            dao.updateArticleImage(articleModels[0].imageURL,articleModels[0].id);
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

    class ArticleWithImage{
        String imageURL;
        int id;

        public ArticleWithImage(String imageURL, int id) {
            this.imageURL = imageURL;
            this.id = id;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
