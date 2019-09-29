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
import com.gelecekbilimde.gelecekbilimde.models.ArticleModel;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    public List<ArticleModel> mArticles;

    public static class ArticleViewHolder extends  RecyclerView.ViewHolder{
        public ImageView articleImage;
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

    public ArticleAdapter(List<ArticleModel> articles) {
        mArticles= articles;
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
                if(mArticles.get(articleViewHolder.getAdapterPosition()).getIsBookmarked()){
                    articleViewHolder.articleBookmarkImage.setImageResource(R.drawable.bookmark_unchecked);
                    mArticles.get(articleViewHolder.getAdapterPosition()).setIsBookmarked(false);

                }else{
                articleViewHolder.articleBookmarkImage.setImageResource(R.drawable.bookmark_checked);
                    mArticles.get(articleViewHolder.getAdapterPosition()).setIsBookmarked(true);

                }
            }
        });

        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        ArticleModel currentArticle = mArticles.get(position);
        holder.articleImage.setImageResource(currentArticle.getArticleImage());
        holder.articleBookmarkImage.setImageResource(currentArticle.getArticleBookmark());
        holder.articleDate.setText(currentArticle.getArticleDate());
        holder.articledescription.setText(currentArticle.getArticleDescription());
        holder.articleHeadline.setText(currentArticle.getArticleHeadline());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}
