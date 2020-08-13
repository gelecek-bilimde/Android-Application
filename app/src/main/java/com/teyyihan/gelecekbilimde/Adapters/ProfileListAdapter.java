package com.teyyihan.gelecekbilimde.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teyyihan.gelecekbilimde.Models.SettingModel;
import com.teyyihan.gelecekbilimde.R;

import java.util.HashMap;
import java.util.List;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ProfileListViewHolder> {

    private List<SettingModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    public ProfileListAdapter(Context context, List<SettingModel> settings){
        this.mInflater = LayoutInflater.from(context);
        this.mData = settings;
    }

    @NonNull
    @Override
    public ProfileListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.settings_row_layout, parent, false);
        return new ProfileListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProfileListViewHolder holder, int position) {
        String title = mData.get(position).getTitle();
        int image = mData.get(position).getIcon();

        holder.myTextView.setText(title);
        holder.mImageView.setImageResource(image);
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ProfileListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView myTextView;
        ImageView mImageView;

        public ProfileListViewHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.settings_row_textView);
            mImageView = itemView.findViewById(R.id.settings_row_imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public SettingModel getItem(int position) {
        return mData.get(position);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
