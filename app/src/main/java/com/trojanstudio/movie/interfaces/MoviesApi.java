package com.trojanstudio.movie.interfaces;

import com.trojanstudio.movie.models.MovieDB;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by smjs2000 on 10/3/16.
 */
public interface MoviesApi {

    @GET("/3/movie/{list_type}")
    Call<MovieDB> getMovies(@Path("list_type")String listType, @Query("api_key")String apiKey);

}
