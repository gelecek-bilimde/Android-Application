package com.gelecekbilimde.teyyihan.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.gelecekbilimde.teyyihan.Models.ArticleModel;
import com.gelecekbilimde.teyyihan.Models.VideoModel;

@Database(entities = {ArticleModel.class, VideoModel.class}, version = 33)
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
