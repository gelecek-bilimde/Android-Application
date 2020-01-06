package com.gelecekbilimde.gelecekbilimde.Fragments.profile;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.gelecekbilimde.gelecekbilimde.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private Context context;
    private RelativeLayout relativeLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        context = getContext();
        setupSettingList(view);


        return view;
    }



    private void setupSettingList(View view) {
        ListView listView = view.findViewById(R.id.profile_settings_list);

        ArrayList<String> settings = new ArrayList<>();
        settings.add("Edit Profile");
        settings.add("Support us");
        settings.add("Sign Out");

        ArrayAdapter arrayAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,settings);
        listView.setAdapter(arrayAdapter);


        // TODO: SETTINGLER İÇİN ACTIVITY OLUŞTUR VE BUNUNLA BAĞLA
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }


}
