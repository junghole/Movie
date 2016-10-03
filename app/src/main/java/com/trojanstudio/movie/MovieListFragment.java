package com.trojanstudio.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trojanstudio.movie.interfaces.MoviesApi;
import com.trojanstudio.movie.models.MovieDB;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by smjs2000 on 10/3/16.
 */
public class MovieListFragment extends Fragment {

    public static final String KEY_LIST_TYPE = "LIST_TYPE";

    @BindView(R.id.movie_recycler_view)
    RecyclerView recyclerView;

    @BindString(R.string.base_url)
    String baseUrl;
    @BindString(R.string.api_key)
    String apiKey;

    public static MovieListFragment newInstance(String listType) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_LIST_TYPE, listType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesApi api = retrofit.create(MoviesApi.class);

        Call<MovieDB> call = api.getMovies(getArguments().getString(KEY_LIST_TYPE), apiKey);
        call.enqueue(new Callback<MovieDB>() {
            @Override
            public void onResponse(Call<MovieDB> call, Response<MovieDB> response) {
                recyclerView.setAdapter(new MovieAdapter(getActivity(), response.body().getResults()));
            }

            @Override
            public void onFailure(Call<MovieDB> call, Throwable t) {

            }
        });

        return view;
    }
}
