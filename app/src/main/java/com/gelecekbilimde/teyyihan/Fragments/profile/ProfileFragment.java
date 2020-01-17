package com.gelecekbilimde.teyyihan.Fragments.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.auth.AuthUI;
import com.gelecekbilimde.teyyihan.Activities.LoginActivity;
import com.gelecekbilimde.teyyihan.Activities.MainActivity;
import com.gelecekbilimde.teyyihan.Models.CurrentUserInfo;
import com.gelecekbilimde.teyyihan.R;
import com.gelecekbilimde.teyyihan.Repository.ArticleRepository;
import com.gelecekbilimde.teyyihan.Repository.VideoRepository;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private Context context;
    private TextView nameText;
    private ImageView profileImage;
    private ArticleRepository articleRepository;
    private VideoRepository videoRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        nameText = view.findViewById(R.id.profile_name);
        profileImage = view.findViewById(R.id.profile_image);

        articleRepository= new ArticleRepository(getActivity().getApplication());
        videoRepository= new VideoRepository(getActivity().getApplication());


        context = getContext();
        setupSettingList(view);
        setupProfileInfo();

        return view;
    }

    private void setupProfileInfo() {
        nameText.setText(CurrentUserInfo.userInfo.get("displayName"));
        Glide.with(this).load(CurrentUserInfo.userInfo.get("photoURL"))
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(profileImage);
    }


    private void setupSettingList(View view) {
        ListView listView = view.findViewById(R.id.profile_settings_list);

        ArrayList<String> settings = new ArrayList<>();
        settings.add("Bizi Destekle");
        settings.add("Çıkış Yap");

        ArrayAdapter arrayAdapter = new ArrayAdapter(context,R.layout.simple_list_item_white,settings);
        listView.setAdapter(arrayAdapter);


        // TODO: SETTINGLER İÇİN ACTIVITY OLUŞTUR VE BUNUNLA BAĞLA
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(getContext(),"Yakında!",Toast.LENGTH_LONG).show();
                        break;
                    case 1:

                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
                        alertBuilder.setMessage("Çıkmak istiyor musun?")
                                .setCancelable(false)
                                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        FirebaseAuth.getInstance().signOut();
                                        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                                .requestIdToken(getString(R.string.default_web_client_id))
                                                .requestEmail()
                                                .build();
                                        // [END config_signin]

                                        articleRepository.deleteAllArticles();
                                        videoRepository.deleteAllVideos();

                                        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
                                        mGoogleSignInClient.signOut().addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                System.out.println("teooo fail "+e.getMessage()+"  "+e.getLocalizedMessage());
                                            }
                                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                                CurrentUserInfo.userInfo.clear();

                                                startActivity(new Intent(getContext(), LoginActivity.class));
                                            }
                                        });

                                    }
                                }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.setTitle("Hey nereye?");
                        alertDialog.show();

                        break;
                }
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.action_search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
        ((MainActivity)getActivity()).setTitle("Profil");
    }
}
