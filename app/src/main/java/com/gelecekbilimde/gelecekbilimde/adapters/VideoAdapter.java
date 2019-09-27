package com.gelecekbilimde.gelecekbilimde.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.models.VideoModel;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    public ArrayList<VideoModel> mVideos;

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        public ImageView videoThumbnail;
        public ImageView videoPublisherLogo;
        public ImageView videoBookmarkLogo;
        public TextView videoTitle;
        public TextView videoDate;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoThumbnail= itemView.findViewById(R.id.video_thumbnail);
            videoPublisherLogo= itemView.findViewById(R.id.video_publisher_logo);
            videoBookmarkLogo= itemView.findViewById(R.id.video_bookmark_logo);
            videoTitle= itemView.findViewById(R.id.video_title);
            videoDate= itemView.findViewById(R.id.video_date);
        }
    }

    public VideoAdapter(ArrayList<VideoModel> videos) {
        mVideos = videos;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_row_layout,parent,false);
        VideoViewHolder videoViewHolder = new VideoViewHolder(view);
        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
    VideoModel selectedVideo = mVideos.get(position);
    holder.videoThumbnail.setImageResource(selectedVideo.getVideoThumbnail());
    holder.videoPublisherLogo.setImageResource(selectedVideo.getVideoPublisherLogo());
    holder.videoBookmarkLogo.setImageResource(selectedVideo.getVideoBookmarkLogo());
    holder.videoTitle.setText(selectedVideo.getVideoTitle());
    holder.videoDate.setText(selectedVideo.getVideoDate());
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

}
