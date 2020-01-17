package com.gelecekbilimde.teyyihan.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.paging.DataSource;

import com.gelecekbilimde.teyyihan.Database.MyDatabase;
import com.gelecekbilimde.teyyihan.Database.VideoDao;
import com.gelecekbilimde.teyyihan.Models.VideoModel;
import com.gelecekbilimde.teyyihan.Network.VideoFirebase;

public class VideoRepository {
    private VideoDao dao;
    private DataSource.Factory<Integer,VideoModel> allVideos;
    private DataSource.Factory<Integer, VideoModel> allBookmarkedVideos;
    private VideoFirebase videoFirebase;

    public VideoRepository(Application application){
        MyDatabase database = MyDatabase.getInstance(application);
        dao = database.videoDao();
        videoFirebase = new VideoFirebase();
        allVideos = dao.getAllVideos();
        allBookmarkedVideos = dao.getAllBookmarkedVideos();
    }
    public void insertVideo(VideoModel video) {
        new InsertVideoAsync(dao).execute(video);
    }
    public void updateVideo(VideoModel video) {
        new UpdateVideoAsync(dao).execute(video);
    }
    public void deleteVideo(VideoModel video) {
        new DeleteVideoAsync(dao).execute(video);
    }
    public void deleteAllVideos() {
        new DeleteAllVideosAsync(dao).execute();
    }

    public DataSource.Factory<Integer,VideoModel> getAllVideos(){
        return allVideos;
    }

    public DataSource.Factory<Integer,VideoModel> getAllBookmarkedVideos(){
        return allBookmarkedVideos;
    }

    public void getTenVideosFromFirebase() {
        videoFirebase.getTenArticlesFromFirebase(this);
    }



    private static class InsertVideoAsync extends AsyncTask<VideoModel,Void,Void> {
        private VideoDao dao;

        public InsertVideoAsync(VideoDao videoDao){
            this.dao = videoDao;
        }

        @Override
        protected Void doInBackground(VideoModel... videoModels) {
            dao.insertVideo(videoModels[0]);
              return null;
        }
    }
    private static class UpdateVideoAsync extends AsyncTask<VideoModel,Void,Void>{
        private VideoDao dao;

        public UpdateVideoAsync(VideoDao videoDao){
            this.dao = videoDao;
        }

        @Override
        protected Void doInBackground(VideoModel... videoModels) {
            dao.updateVideo(videoModels[0]);
            return null;
        }
    }
    private static class DeleteVideoAsync extends AsyncTask<VideoModel,Void,Void>{
        private VideoDao dao;

        public DeleteVideoAsync(VideoDao videoDao){
            this.dao = videoDao;
        }

        @Override
        protected Void doInBackground(VideoModel... videoModels) {
            dao.deleteVideo(videoModels[0]);
            return null;
        }
    }
    private static class DeleteAllVideosAsync extends AsyncTask<Void,Void,Void>{
        private VideoDao dao;

        public DeleteAllVideosAsync(VideoDao videoDao){
            this.dao = videoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllVideos();
            return null;
        }
    }
}
