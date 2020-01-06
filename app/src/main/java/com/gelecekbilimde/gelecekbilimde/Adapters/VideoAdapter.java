package com.gelecekbilimde.gelecekbilimde.Adapters;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends ListAdapter<VideoModel,VideoAdapter.VideoViewHolder> {

    public VideoAdapter() {
        super(callback);
    }

    private static final DiffUtil.ItemCallback<VideoModel> callback = new DiffUtil.ItemCallback<VideoModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull VideoModel oldItem, @NonNull VideoModel newItem) {
            return oldItem.getVideoId() == newItem.getVideoId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull VideoModel oldItem, @NonNull VideoModel newItem) {
            return oldItem.getVideoTitle().equals(newItem.getVideoTitle());
        }
    };

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
               if( getItem(videoViewHolder.getAdapterPosition()).isBookmarked()){
                   getItem(videoViewHolder.getAdapterPosition()).setBookmarked(false);
                   videoViewHolder.videoBookmarkLogo.setImageResource(R.drawable.bookmark_unchecked);
               }else{
                   getItem(videoViewHolder.getAdapterPosition()).setBookmarked(true);
                   videoViewHolder.videoBookmarkLogo.setImageResource(R.drawable.bookmark_checked);
               }

            }
        });

        return videoViewHolder;
    }


    public VideoModel getVideoAt(int position) {
        return getItem(position);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
    VideoModel selectedVideo = getItem(position);
    holder.videoThumbnail.setImageResource(selectedVideo.getVideoThumbnail());
    holder.videoPublisherLogo.setImageResource(selectedVideo.getVideoPublisherLogo());
    holder.videoBookmarkLogo.setImageResource(selectedVideo.getVideoBookmarkLogo());
    holder.videoTitle.setText(selectedVideo.getVideoTitle());
    holder.videoDate.setText(selectedVideo.getVideoDate());
    }


}
