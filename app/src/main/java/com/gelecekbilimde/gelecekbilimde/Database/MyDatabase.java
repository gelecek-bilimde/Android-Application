package com.gelecekbilimde.gelecekbilimde.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;
import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;
import com.gelecekbilimde.gelecekbilimde.R;

@Database(entities = {ArticleModel.class, VideoModel.class}, version = 11)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase myDatabaseInstance;

    public abstract ArticleDao articleDao();

    public abstract VideoDao videoDao();

    public static synchronized MyDatabase getInstance(Context context) {
        if (myDatabaseInstance == null) {
            myDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class, "database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return myDatabaseInstance;
    }


    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };


}
