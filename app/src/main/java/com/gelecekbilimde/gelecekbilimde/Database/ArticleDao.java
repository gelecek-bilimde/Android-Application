package com.gelecekbilimde.gelecekbilimde.Database;


import androidx.paging.DataSource;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;


@androidx.room.Dao
public interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertArticle(ArticleModel article);

    @Update
    void updateArticle(ArticleModel article);

    @Delete
    void deleteArticle(ArticleModel article);

    @Query("DELETE FROM article_table")
    void deleteAllArticles();

    @Query("Select * From article_table order by articleDate desc")
    DataSource.Factory<Integer, ArticleModel> getAllArticles();

    @Query("Select * from article_table where bookmarked=1")
    DataSource.Factory<Integer, ArticleModel> getAllBookmarkedArticles();

}
