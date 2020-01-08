package com.gelecekbilimde.gelecekbilimde.Fragments.article;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.Adapters.ArticleAdapter;
import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;

import java.util.List;

public class ArticleFragment extends Fragment {

    private ArticleViewModel articleViewModel;
    private RecyclerView mRecyclerview;
    private ArticleAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager manager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_articles, container, false);

        mRecyclerview = view.findViewById(R.id.article_recyclerview2);
        mSwipeRefreshLayout = view.findViewById(R.id.article_swipe2);
        manager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(manager);
        mAdapter = new ArticleAdapter(getContext());
        mRecyclerview.setAdapter(mAdapter);
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        articleViewModel.getTenArticlesfromfirebase();

        articleViewModel.getAllArticles().observe(this, new Observer<PagedList<ArticleModel>>() {
            @Override
            public void onChanged(PagedList<ArticleModel> articleModels) {
                mAdapter.submitList(articleModels);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                articleViewModel.deleteAllArticles();

                articleViewModel.getTenArticlesfromfirebase();

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.article_search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_view_item);
    }
}