package com.gelecekbilimde.teyyihan.Fragments.article;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gelecekbilimde.teyyihan.Activities.MainActivity;
import com.gelecekbilimde.teyyihan.R;
import com.gelecekbilimde.teyyihan.Adapters.ArticleAdapter;
import com.gelecekbilimde.teyyihan.Models.ArticleModel;

public class ArticleFragment extends Fragment {

    private ArticleViewModel articleViewModel;
    private RecyclerView mRecyclerview;
    private ArticleAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager manager;
    public static MutableLiveData<String> title = new MutableLiveData<>();
    private boolean isloadedBefore = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

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

        if (!isloadedBefore) {
            refreshArticleFeed();
            isloadedBefore=true;
        }


        articleViewModel.getAllArticles().observe(this, new Observer<PagedList<ArticleModel>>() {
            @Override
            public void onChanged(PagedList<ArticleModel> articleModels) {
                mAdapter.submitList(articleModels);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //articleViewModel.deleteAllArticles();
                manager.scrollToPosition(0);
                refreshArticleFeed();

            }
        });

        articleViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mSwipeRefreshLayout.setRefreshing(aBoolean);
            }
        });

        return view;
    }

    private void refreshArticleFeed() {
        articleViewModel.isLoading.postValue(true);
        articleViewModel.getTenArticlesfromFirebaseAndRetrofit(1);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.action_search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
        ((MainActivity)getActivity()).setTitle("Makaleler");
    }
}