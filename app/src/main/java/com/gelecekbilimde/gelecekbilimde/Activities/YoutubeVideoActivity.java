package com.gelecekbilimde.gelecekbilimde.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gelecekbilimde.gelecekbilimde.R;
import com.gelecekbilimde.gelecekbilimde.YoutubeAPI.YoutubeAPI;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeVideoActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener listener;
    String videoURL= " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video);

        youTubePlayerView = findViewById(R.id.youtube_video_player);

        getIncomingIntent();

        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoURL);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };


        youTubePlayerView.initialize(YoutubeAPI.youtbeAndroidAPI,listener);


    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("VIDEO_URL") ) {
            videoURL = getIntent().getStringExtra("VIDEO_URL");
        }

    }
}
