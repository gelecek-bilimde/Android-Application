package com.gelecekbilimde.gelecekbilimde.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
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
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gelecekbilimde.gelecekbilimde.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ArticleReadActivity extends AppCompatActivity implements Html.ImageGetter {

    ImageView imageView;
    TextView bodyTextView;
    TextView titleTxt;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    Button exitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_read);
        toolbar = (Toolbar) findViewById(R.id.article_read_toolbar);
        appBarLayout = findViewById(R.id.article_read_appbar);
        setSupportActionBar(toolbar);
        imageView = findViewById(R.id.article_read_image);
        bodyTextView = findViewById(R.id.article_read_text);
        titleTxt = findViewById(R.id.article_read_title);
        exitBtn = findViewById(R.id.video_quit_btn);
        getIncomingIntent();



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    private void getIncomingIntent() {

        if (getIntent().hasExtra("ARTICLE_IMAGE_URL") && getIntent().hasExtra("ARTICLE_BODY") && getIntent().hasExtra("ARTICLE_TITLE")) {
            String imageUrl = getIntent().getStringExtra("ARTICLE_IMAGE_URL");
            String body = getIntent().getStringExtra("ARTICLE_BODY");
            String title = getIntent().getStringExtra("ARTICLE_TITLE");
            setImageAndBody(imageUrl, body, title);
        }

    }

    private void setImageAndBody(String imageUrl, String body, final String title) {
        Glide.with(this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        //bodyTextView.setText(Html.fromHtml(body));
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(title);

        titleTxt.setText(title);

        body = body.replace("<figcaption>", "<br><figcaption>");

        Spanned spanned = Html.fromHtml(body, this, null);
        bodyTextView.setText(spanned);
        bodyTextView.setMovementMethod(LinkMovementMethod.getInstance());



        //top bar title
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Makale");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

    }

    @Override
    public Drawable getDrawable(String s) {
        LevelListDrawable d = new LevelListDrawable();
        Drawable empty = getResources().getDrawable(R.drawable.empty_blue);
        d.addLevel(0, 0, empty);
        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        new LoadImage().execute(s, d);

        return d;
    }

    class LoadImage extends AsyncTask<Object, Void, Bitmap> {

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];

            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = bodyTextView.getText();
                bodyTextView.setText(t);
            }
        }
    }
}

