package com.trojanstudio.movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trojanstudio.movie.models.Movie;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY_MOVIE_OBJECT = "MOVIE_OBJECT";

    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.detail_movie_poster)
    ImageView moviePoster;
    @BindView(R.id.detail_overview)
    TextView textOverview;

    @BindString(R.string.image_base_url_large)
    String imageBaseUrl;

    @OnClick(R.id.detail_email)
    public void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, overview);
        if (intent.resolveActivity(getPackageManager()) != null) startActivity(intent);
    }

    String title;
    String posterUrl;
    String overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Movie movie = getIntent().getParcelableExtra(KEY_MOVIE_OBJECT);

        title = movie.getTitle();

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        posterUrl = imageBaseUrl+movie.getPosterPath();
        Glide.with(this).load(posterUrl).into(moviePoster);

        overview = movie.getOverview();
        textOverview.setText(overview);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}