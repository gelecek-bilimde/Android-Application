package com.gelecekbilimde.gelecekbilimde.Fragments.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.gelecekbilimde.gelecekbilimde.Adapters.BookmarkTablayoutAdapter;
import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.Fragments.bookmark.BookmarkFragments.BookmarkedArticleFragment;
import com.gelecekbilimde.gelecekbilimde.Fragments.bookmark.BookmarkFragments.BookmarkedVideoFragment;
import com.google.android.material.tabs.TabLayout;

public class BookmarkFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager viewPager;
    BookmarkTablayoutAdapter bookmarkTablayoutAdapter;
    private BookmarkViewModel bookmarkViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookmarkViewModel =
                ViewModelProviders.of(this).get(BookmarkViewModel.class);
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);

        tabLayout = view.findViewById(R.id.bookmark_tablayout);
        viewPager = view.findViewById(R.id.bookmark_viewpager);
        bookmarkTablayoutAdapter = new BookmarkTablayoutAdapter( getChildFragmentManager());

        bookmarkTablayoutAdapter.addFragment(new BookmarkedArticleFragment(), "Articles");
        bookmarkTablayoutAdapter.addFragment(new BookmarkedVideoFragment(), "Videos");

        viewPager.setAdapter(bookmarkTablayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}