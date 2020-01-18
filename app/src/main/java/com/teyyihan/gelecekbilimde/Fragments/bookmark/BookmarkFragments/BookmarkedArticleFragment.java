package com.teyyihan.gelecekbilimde.Fragments.bookmark.BookmarkFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teyyihan.gelecekbilimde.R;
import com.teyyihan.gelecekbilimde.Adapters.ArticleAdapter;
import com.teyyihan.gelecekbilimde.Models.ArticleModel;

public class BookmarkedArticleFragment extends Fragment {


    private RecyclerView mRecyclerview;
    private ArticleAdapter mAdapter;
    private BookmarkedArticleViewModel articleViewModel;

    public BookmarkedArticleFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.bookmarked_article_fragment,container,false);

        mRecyclerview = v.findViewById(R.id.bookmarked_article_recycler);
        mRecyclerview.setHasFixedSize(true) ;
        mAdapter = new ArticleAdapter(getContext());
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview.setAdapter(mAdapter);

        articleViewModel = ViewModelProviders.of(this).get(BookmarkedArticleViewModel.class);


        articleViewModel.getAllBookmarkedArticles().observe(this, new Observer<PagedList<ArticleModel>>() {
            @Override
            public void onChanged(PagedList<ArticleModel> articleModels) {
                mAdapter.submitList(articleModels);
            }
        });


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

    }

}
