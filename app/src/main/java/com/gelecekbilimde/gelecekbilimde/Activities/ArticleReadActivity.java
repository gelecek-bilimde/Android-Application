package com.gelecekbilimde.gelecekbilimde.Activities;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gelecekbilimde.gelecekbilimde.R;

public class ArticleReadActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar ;
    AppBarLayout appBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_read);
        toolbar = (Toolbar) findViewById(R.id.article_read_toolbar);
        appBarLayout = findViewById(R.id.article_read_appbar);
        setSupportActionBar(toolbar);
        imageView = findViewById(R.id.article_read_image);
        textView = findViewById(R.id.article_read_text);
        getIncomingIntent();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("ARTICLE_IMAGE_URL") && getIntent().hasExtra("ARTICLE_BODY")&&getIntent().hasExtra("ARTICLE_TITLE")) {
            String imageUrl = getIntent().getStringExtra("ARTICLE_IMAGE_URL");
            String body = getIntent().getStringExtra("ARTICLE_BODY");
            String title = getIntent().getStringExtra("ARTICLE_TITLE");
            setImageAndBody(imageUrl, body,title);
        }

    }

    private void setImageAndBody(String imageUrl, String body,String title) {
        Glide.with(this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        textView.setText(Html.fromHtml(body));
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitleEnabled(false);
        collapsingToolbarLayout.setTitle(title);
    }
}
