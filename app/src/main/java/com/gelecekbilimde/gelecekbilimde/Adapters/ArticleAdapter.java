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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gelecekbilimde.gelecekbilimde.Activities.ArticleReadActivity;
import com.gelecekbilimde.gelecekbilimde.Fragments.article.ArticleViewModel;
import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleAdapter extends PagedListAdapter<ArticleModel,ArticleAdapter.ArticleViewHolder> {

    Context mContext;
    ArticleViewModel articleViewModel;
    public ArticleAdapter(Context mContext) {
        super(callback);
        this.mContext = mContext;
        articleViewModel = ViewModelProviders.of((FragmentActivity) mContext).get(ArticleViewModel.class);
    }

    private static final DiffUtil.ItemCallback<ArticleModel> callback = new DiffUtil.ItemCallback<ArticleModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ArticleModel oldItem, @NonNull ArticleModel newItem) {

            return oldItem.getArticleId() == newItem.getArticleId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticleModel oldItem, @NonNull ArticleModel newItem) {

            return oldItem.getArticleTitle().equals(newItem.getArticleTitle()) && oldItem.isBookmarked()==newItem.isBookmarked();
        }
    };

    public static class ArticleViewHolder extends  RecyclerView.ViewHolder{
        public ImageView  articleImage;
        public ImageView articleBookmarkImage;
        public TextView articleHeadline;
        public TextView articledescription;
        public TextView articleDate;
        private RelativeLayout articleRelativeLay;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.article_image);
            articleBookmarkImage = itemView.findViewById(R.id.article_bookmark);
            articleHeadline = itemView.findViewById(R.id.article_headline);
            articledescription = itemView.findViewById(R.id.article_desc);
            articleDate = itemView.findViewById(R.id.article_date);
            articleRelativeLay = itemView.findViewById(R.id.article_cardview);
        }
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_row_layout,parent,false);
        final ArticleViewHolder articleViewHolder = new ArticleViewHolder(view);

        //article cardview onclick methodu
        articleViewHolder.articleRelativeLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticleViewHolder holder, int position) {
        final ArticleModel currentArticle = getItem(position);
        String finalDateTime = " ";
        String initialStringDate = currentArticle.getArticleDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = format.parse(initialStringDate);
            String stringDate = new SimpleDateFormat("dd/MM/yyyy").format(date);

            finalDateTime = stringDate;
        } catch (ParseException e) { e.printStackTrace(); }


        if (!(currentArticle.getArticleImageURL().matches("null"))) {
            Glide.with(mContext).load(currentArticle.getArticleImageURL())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .circleCrop()
                    .into(holder.articleImage);
        }

        if (currentArticle.isBookmarked()) {
            holder.articleBookmarkImage.setImageResource(R.drawable.bookmark_checked);
        } else {
            holder.articleBookmarkImage.setImageResource(R.drawable.bookmark_unchecked);
        }

        holder.articleDate.setText(finalDateTime);

        try {
            holder.articledescription.setText(Html.fromHtml(currentArticle.getArticleDesc()));
            holder.articleHeadline.setText(Html.fromHtml(currentArticle.getArticleTitle()));
        } catch (Exception e) {
            holder.articledescription.setText(" ");
            holder.articleHeadline.setText(" ");
        }



        // cardview onclick
        holder.articleRelativeLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent intent = new Intent(mContext, ArticleReadActivity.class);
            intent.putExtra("ARTICLE_IMAGE_URL",currentArticle.getArticleImageURL());
            intent.putExtra("ARTICLE_BODY",currentArticle.getArticleBody());
            intent.putExtra("ARTICLE_TITLE",currentArticle.getArticleTitle());
            mContext.startActivity(intent);
            }
        });


        // bookmark click event
        holder.articleBookmarkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!currentArticle.isBookmarked()) {
                    setBookmarkedTrue(currentArticle);
                } else {
                    setBookmarkFalse(currentArticle);
                }

            }

            private void setBookmarkFalse(ArticleModel currentArticle) {
                currentArticle.setBookmarked(false);
                articleViewModel.updateArticle(currentArticle);
                holder.articleBookmarkImage.setImageResource(R.drawable.bookmark_unchecked);
            }

            private void setBookmarkedTrue(ArticleModel currentArticle) {
                currentArticle.setBookmarked(true);
                articleViewModel.updateArticle(currentArticle);
                holder.articleBookmarkImage.setImageResource(R.drawable.bookmark_checked);
            }
        });

    }

    public ArticleModel getArticle(int position) {
        return getItem(position);
    }
}
