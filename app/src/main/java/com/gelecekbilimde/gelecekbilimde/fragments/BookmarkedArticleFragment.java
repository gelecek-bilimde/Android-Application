package com.gelecekbilimde.gelecekbilimde.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.adapters.ArticleAdapter;
import com.gelecekbilimde.gelecekbilimde.models.ArticleModel;

import java.util.ArrayList;
import java.util.List;

public class BookmarkedArticleFragment extends Fragment {

    private List<ArticleModel> articles = new ArrayList<>();
    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public BookmarkedArticleFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.bookmarked_article_fragment,container,false);

        mRecyclerview = v.findViewById(R.id.bookmarked_article_recycler);
        mRecyclerview.setHasFixedSize(true) ;
        mRecyclerview.addItemDecoration(new DividerItemDecoration(mRecyclerview.getContext(), DividerItemDecoration.VERTICAL));
        mLayoutManager  = new LinearLayoutManager(getActivity());
        mAdapter = new ArticleAdapter(articles);

        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline1" ,"desc1", "11.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline2" ,"desc2", "12.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline3" ,"desc3", "13.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline4" ,"desc4", "14.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline4" ,"desc4", "14.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline4" ,"desc4", "14.03.1999",false));
        articles.add(new ArticleModel(R.drawable.user, R.drawable.bookmark_unchecked,"Headline4" ,"desc4", "14.03.1999",false));


    }
}
