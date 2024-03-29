package com.alwan.jpsubmissionmovie3.service;

import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;
import com.alwan.jpsubmissionmovie3.data.network.MovieResponse;
import com.alwan.jpsubmissionmovie3.data.network.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBApi {
    //region MOVIE
    // query for movies
    @GET("movie/popular")
    Call<MovieResponse> getMoviePopular(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page);

    // query for movies upcoming
    @GET("movie/upcoming")
    Call<MovieResponse> getMovieUpcoming(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page);

    //get_movie_by_id
    @GET("movie/{movie_id}")
    Call<MovieEntity> getMovieById(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
    //endregion

    //region TV
    // query for tvs
    @GET("tv/popular")
    Call<TvShowResponse> getTvPopular(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page);

    //get_tv_by_id
    @GET("tv/{tv_id}")
    Call<TvShowEntity> getTvById(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
    //endregion
}
