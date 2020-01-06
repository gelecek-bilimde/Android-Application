package com.gelecekbilimde.gelecekbilimde.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends PagedListAdapter<ArticleModel,ArticleAdapter.ArticleViewHolder> {

    Context mContext;

    public ArticleAdapter(Context mContext) {
        super(callback);
        this.mContext = mContext;
    }

    private static final DiffUtil.ItemCallback<ArticleModel> callback = new DiffUtil.ItemCallback<ArticleModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ArticleModel oldItem, @NonNull ArticleModel newItem) {

            return oldItem.getArticleId() == newItem.getArticleId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticleModel oldItem, @NonNull ArticleModel newItem) {

            return oldItem.getArticleTitle().equals(newItem.getArticleTitle());
        }
    };

    public static class ArticleViewHolder extends  RecyclerView.ViewHolder{
        public ImageView  articleImage;
        public ImageView articleBookmarkImage;
        public TextView articleHeadline;
        public TextView articledescription;
        public TextView articleDate;
        private CardView articleCardView;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.article_image);
            articleBookmarkImage = itemView.findViewById(R.id.article_bookmark);
            articleHeadline = itemView.findViewById(R.id.article_headline);
            articledescription = itemView.findViewById(R.id.article_desc);
            articleDate = itemView.findViewById(R.id.article_date);
            articleCardView = itemView.findViewById(R.id.article_cardview);
        }
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_row_layout,parent,false);
        final ArticleViewHolder articleViewHolder = new ArticleViewHolder(view);

        //article cardview onclick methodu
        articleViewHolder.articleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //article bookmark image onclick methodu
        articleViewHolder.articleBookmarkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem(articleViewHolder.getAdapterPosition()).isBookmarked()){
                    articleViewHolder.articleBookmarkImage.setImageResource(R.drawable.bookmark_unchecked);
                    getItem(articleViewHolder.getAdapterPosition()).setBookmarked(false);

                }else{
                articleViewHolder.articleBookmarkImage.setImageResource(R.drawable.bookmark_checked);
                    getItem(articleViewHolder.getAdapterPosition()).setBookmarked(true);

                }
            }
        });

        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        ArticleModel currentArticle = getItem(position);

        Glide.with(mContext).load(currentArticle.getArticleImageURL()).into(holder.articleImage);
        holder.articleBookmarkImage.setImageResource(R.drawable.bookmark_unchecked);
        holder.articleDate.setText(currentArticle.getArticleDate());
        holder.articledescription.setText(currentArticle.getArticleDesc());
        holder.articleHeadline.setText(currentArticle.getArticleTitle());
    }

    public ArticleModel getArticle(int position) {
        return getItem(position);
    }
}
