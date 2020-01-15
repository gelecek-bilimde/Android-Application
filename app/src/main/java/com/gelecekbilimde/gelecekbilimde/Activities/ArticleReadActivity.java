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
import com.gelecekbilimde.gelecekbilimde.Models.RetrofitArticleModel;
import com.gelecekbilimde.gelecekbilimde.Network.RetrofitArticleAPI;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gelecekbilimde.gelecekbilimde.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleReadActivity extends AppCompatActivity implements Html.ImageGetter {

    ImageView imageView;
    TextView bodyTextView;
    TextView titleTxt;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    Button exitBtn;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_read);
        toolbar =  findViewById(R.id.article_read_toolbar);
        appBarLayout = findViewById(R.id.article_read_appbar);
        setSupportActionBar(toolbar);
        imageView = findViewById(R.id.article_read_image);
        bodyTextView = findViewById(R.id.article_read_text);
        titleTxt = findViewById(R.id.article_read_title);
        progressBar = findViewById(R.id.article_read_progress);
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

        if (getIntent().hasExtra("ARTICLE_IMAGE_URL") && getIntent().hasExtra("ARTICLE_TITLE") && getIntent().hasExtra("ARTICLE_ID")) {
            String imageUrl = getIntent().getStringExtra("ARTICLE_IMAGE_URL");
            int articleID = getIntent().getIntExtra("ARTICLE_ID",0);
            String title = getIntent().getStringExtra("ARTICLE_TITLE");
            progressBar.setVisibility(View.VISIBLE);
            setImageAndBody(imageUrl,articleID,  title);
        }

    }

    private void setImageAndBody(String imageUrl,int articleID, final String title) {
        Glide.with(this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        //bodyTextView.setText(Html.fromHtml(body));
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(title);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.bilimtreni.com/wp-json/wp/v2/posts/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitArticleAPI api = retrofit.create(RetrofitArticleAPI.class);

        Call<RetrofitArticleModel> call = api.getPost(articleID);

        call.enqueue(new Callback<RetrofitArticleModel>() {
            @Override
            public void onResponse(Call<RetrofitArticleModel> call, Response<RetrofitArticleModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Hata: " + response.code(),Toast.LENGTH_LONG).show();
                } else {
                    progressBar.setVisibility(View.GONE);
                    RetrofitArticleModel model = response.body();
                    String body = model.getContent().getRendered();
                    body = body.replace("<figcaption>", "<br><figcaption>");

                    Spanned spanned = Html.fromHtml(body, s -> {
                        LevelListDrawable d = new LevelListDrawable();
                        Drawable empty = getResources().getDrawable(R.drawable.empty_blue);
                        d.addLevel(0, 0, empty);
                        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

                        new LoadImage().execute(s, d);

                        return d;
                    }, null);
                    bodyTextView.setText(spanned);
                    bodyTextView.setMovementMethod(LinkMovementMethod.getInstance());

                    titleTxt.setText(title);
                }

            }

            @Override
            public void onFailure(Call<RetrofitArticleModel> call, Throwable t) {

            }
        });

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

