package com.teyyihan.gelecekbilimde.Fragments.video;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.teyyihan.gelecekbilimde.Models.VideoModel;
import com.teyyihan.gelecekbilimde.Repository.VideoRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class VideoItemBoundaryCallback extends PagedList.BoundaryCallback<VideoModel> {

    private VideoRepository repository;
    public VideoItemBoundaryCallback(VideoRepository videoRepository) {
        this.repository = videoRepository;
    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        repository.getTenVideosFromFirebase();

    }

    @Override
    public void onItemAtEndLoaded(@NonNull VideoModel itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Videos");

        Query query = myRef.orderByChild("videoDate").endAt(itemAtEnd.getVideoDate()).limitToLast(3);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                VideoModel videoModel;
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot each: dataSnapshot.getChildren()) {
                        videoModel= each.getValue(VideoModel.class);
                        repository.insertVideo(videoModel);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }
}
