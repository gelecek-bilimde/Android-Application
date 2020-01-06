package com.gelecekbilimde.gelecekbilimde.Fragments.bookmark.BookmarkFragments;

import android.os.Bundle;
import android.os.Parcelable;
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
import com.gelecekbilimde.gelecekbilimde.Adapters.ArticleAdapter;
import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;

import java.util.ArrayList;
import java.util.List;

public class BookmarkedArticleFragment extends Fragment {

    private List<ArticleModel> articles = new ArrayList<>();
    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;
    private Parcelable savedRecyclerLayoutState;

    public BookmarkedArticleFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.bookmarked_article_fragment,container,false);

        mRecyclerview = v.findViewById(R.id.bookmarked_article_recycler);
        mRecyclerview.setHasFixedSize(true) ;
        mRecyclerview.addItemDecoration(new DividerItemDecoration(mRecyclerview.getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new ArticleAdapter(getContext());
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview.setAdapter(mAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        articles.add(new ArticleModel("Author" ,"ADSJKALSDJKŞLİADİŞADSİSADŞ", "Date" ,"Desc", "https://bilimtreni.com/wp-content/uploads/1058-bilim.jpg","Title",false));
        articles.add(new ArticleModel("Author" ,"ADSJKALSDJKŞLİADİŞADSİSADŞ", "Date" ,"Desc", "https://bilimtreni.com/wp-content/uploads/1058-bilim.jpg","Title",false));
        articles.add(new ArticleModel("Author" ,"ADSJKALSDJKŞLİADİŞADSİSADŞ", "Date" ,"Desc", "https://bilimtreni.com/wp-content/uploads/1058-bilim.jpg","Title",false));
        articles.add(new ArticleModel("Author" ,"ADSJKALSDJKŞLİADİŞADSİSADŞ", "Date" ,"Desc", "https://bilimtreni.com/wp-content/uploads/1058-bilim.jpg","Title",false));
        articles.add(new ArticleModel("Author" ,"ADSJKALSDJKŞLİADİŞADSİSADŞ", "Date" ,"Desc", "https://bilimtreni.com/wp-content/uploads/1058-bilim.jpg","Title",false));


    }

}
