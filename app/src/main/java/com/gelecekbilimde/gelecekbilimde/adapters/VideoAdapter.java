package com.gelecekbilimde.gelecekbilimde.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.models.VideoModel;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    public List<VideoModel> mVideos;

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        public ImageView videoThumbnail;
        public ImageView videoPublisherLogo;
        public ImageView videoBookmarkLogo;
        public TextView videoTitle;
        public TextView videoDate;
        private CardView videoCardView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoThumbnail= itemView.findViewById(R.id.video_thumbnail);
            videoPublisherLogo= itemView.findViewById(R.id.video_publisher_logo);
            videoBookmarkLogo= itemView.findViewById(R.id.video_bookmark_logo);
            videoTitle= itemView.findViewById(R.id.video_title);
            videoDate= itemView.findViewById(R.id.video_date);
            videoCardView = itemView.findViewById(R.id.video_cardview);
        }
    }

    public VideoAdapter(List<VideoModel> videos) {
        mVideos = videos;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_row_layout,parent,false);
        final VideoViewHolder videoViewHolder = new VideoViewHolder(view);

        //video onclick methodu
        videoViewHolder.videoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //bookmark onclick method
        videoViewHolder.videoBookmarkLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if( mVideos.get(videoViewHolder.getAdapterPosition()).getIsBookmarked()){
                   mVideos.get(videoViewHolder.getAdapterPosition()).setIsBookmarked(false);
                   videoViewHolder.videoBookmarkLogo.setImageResource(R.drawable.bookmark_unchecked);
               }else{
                   mVideos.get(videoViewHolder.getAdapterPosition()).setIsBookmarked(true);
                   videoViewHolder.videoBookmarkLogo.setImageResource(R.drawable.bookmark_checked);
               }

            }
        });

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
