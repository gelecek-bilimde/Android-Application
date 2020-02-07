package com.teyyihan.gelecekbilimde.Fragments.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.teyyihan.gelecekbilimde.Activities.MainActivity;
import com.teyyihan.gelecekbilimde.Adapters.BookmarkTablayoutAdapter;
import com.teyyihan.gelecekbilimde.R;
import com.teyyihan.gelecekbilimde.Fragments.bookmark.BookmarkFragments.BookmarkedArticleFragment;
import com.teyyihan.gelecekbilimde.Fragments.bookmark.BookmarkFragments.BookmarkedVideoFragment;
import com.google.android.material.tabs.TabLayout;

public class BookmarkFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager viewPager;
    BookmarkTablayoutAdapter bookmarkTablayoutAdapter;
    private BookmarkViewModel bookmarkViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookmarkViewModel =
                ViewModelProviders.of(this).get(BookmarkViewModel.class);
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);

        tabLayout = view.findViewById(R.id.bookmark_tablayout);
        viewPager = view.findViewById(R.id.bookmark_viewpager);
        bookmarkTablayoutAdapter = new BookmarkTablayoutAdapter( getChildFragmentManager());

        bookmarkTablayoutAdapter.addFragment(new BookmarkedArticleFragment(), "Makaleler");
        bookmarkTablayoutAdapter.addFragment(new BookmarkedVideoFragment(), "Videolar");

        viewPager.setAdapter(bookmarkTablayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.action_search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
        ((MainActivity)getActivity()).setTitle("Kaydedilenler");
    }
}