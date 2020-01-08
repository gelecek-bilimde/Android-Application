package com.gelecekbilimde.gelecekbilimde.Network;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.gelecekbilimde.gelecekbilimde.Models.ArticleModel;
import com.gelecekbilimde.gelecekbilimde.Models.VideoModel;
import com.gelecekbilimde.gelecekbilimde.Repository.ArticleRepository;
import com.gelecekbilimde.gelecekbilimde.Repository.VideoRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class VideoFirebase {
    DatabaseReference myRef;
    FirebaseDatabase firebaseDatabase;
    private String lastKey="";

    public VideoFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Videos");
    }

    public void getTenArticlesFromFirebase(final VideoRepository videoRepository) {
        Query query = myRef.orderByChild("videoDate").limitToLast(10);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                VideoModel videoModel;
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot each: dataSnapshot.getChildren()) {
                        videoModel= each.getValue(VideoModel.class);
                        videoRepository.insertVideo(videoModel);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }

}