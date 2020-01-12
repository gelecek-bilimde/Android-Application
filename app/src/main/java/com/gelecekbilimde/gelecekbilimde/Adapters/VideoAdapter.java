package com.gelecekbilimde.gelecekbilimde.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.gelecekbilimde.gelecekbilimde.Activities.YoutubeVideoActivity;
import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VideoAdapter extends PagedListAdapter<VideoModel,VideoAdapter.VideoViewHolder> {

    Context mContext;

    public VideoAdapter(Context context) {
        super(callback);
        this.mContext = context;
    }

    private static final DiffUtil.ItemCallback<VideoModel> callback = new DiffUtil.ItemCallback<VideoModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull VideoModel oldItem, @NonNull VideoModel newItem) {
            return oldItem.getVideoURLId().equals(newItem.getVideoURLId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull VideoModel oldItem, @NonNull VideoModel newItem) {
            return oldItem.getVideoTitle().equals(newItem.getVideoTitle()) && oldItem.isBookmarked()==newItem.isBookmarked();
        }
    };

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        public ImageView videoThumbnail;
        public ImageView videoPublisherLogo;
        public ImageView videoBookmarkLogo;
        public TextView videoTitle;
        public TextView videoDate;
        private RelativeLayout videoRelativeLayout;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoThumbnail= itemView.findViewById(R.id.video_thumbnail);
            videoPublisherLogo= itemView.findViewById(R.id.video_publisher_logo);
            videoBookmarkLogo= itemView.findViewById(R.id.video_bookmark_logo);
            videoTitle= itemView.findViewById(R.id.video_title);
            videoDate= itemView.findViewById(R.id.video_date);
            videoRelativeLayout = itemView.findViewById(R.id.video_cardview);
        }
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_row_layout,parent,false);
        final VideoViewHolder videoViewHolder = new VideoViewHolder(view);

        //video onclick methodu
        videoViewHolder.videoRelativeLayout.setOnClickListener(new View.OnClickListener() {
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


    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
    final VideoModel selectedVideo = getItem(position);

        if (selectedVideo != null) {
            if (selectedVideo.getVideoDate() != null) {
                String sdf = calculateDate(selectedVideo.getVideoDate());
                holder.videoDate.setText(sdf);
            }

            Glide.with(mContext).load(selectedVideo.getVideoImageURL())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .apply(new RequestOptions().override(1000, 550))
                    .into(holder.videoThumbnail);

            holder.videoTitle.setText(Html.fromHtml(selectedVideo.getVideoTitle()));
        }

        holder.videoThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, YoutubeVideoActivity.class);
                intent.putExtra("VIDEO_URL",selectedVideo.getVideoURLId());
                mContext.startActivity(intent);
            }
        });

    }

    private String calculateDate(String videoDate) {

        Date date= null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            date = format.parse(videoDate.replaceAll("Z$", "+0000"));
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sdf = new SimpleDateFormat("dd/MM/yyyy").format(date);


        return sdf;
    }


}
