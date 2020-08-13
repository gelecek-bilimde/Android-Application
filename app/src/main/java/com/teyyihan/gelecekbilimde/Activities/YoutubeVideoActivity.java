package com.teyyihan.gelecekbilimde.Activities;

import android.os.Bundle;
import android.view.View;

import com.teyyihan.gelecekbilimde.R;
import com.teyyihan.gelecekbilimde.YoutubeAPI;
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


        youTubePlayerView.initialize(YoutubeAPI.api,listener);


    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("VIDEO_URL") ) {
            videoURL = getIntent().getStringExtra("VIDEO_URL");
        }

    }

    public void exitVideoActivity(View view) {
        finish();
    }
}