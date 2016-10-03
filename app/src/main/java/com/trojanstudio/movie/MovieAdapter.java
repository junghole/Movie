package com.trojanstudio.movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.trojanstudio.movie.models.Movie;

import java.util.List;
import java.util.Random;

/**
 * Created by smjs2000 on 8/30/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static Context context;
    private List<Movie> movies;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView movieImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            Random random = new Random();
            int i = random.nextInt(100) + 200;
            float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, context.getResources().getDisplayMetrics());
            CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, (int) height);
            itemView.findViewById(R.id.item_frame).setLayoutParams(params);

            movieImageView = (ImageView) itemView.findViewById(R.id.item_poster);
        }

        public ImageView getMovieImageView() {
            return movieImageView;
        }
    }

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        final String imageRequest = context.getString(R.string.image_base_url_small) + movie.getPosterPath();

        ImageView imageView = holder.getMovieImageView();
        Glide.with(context).load(imageRequest).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.KEY_MOVIE_OBJECT, movie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
