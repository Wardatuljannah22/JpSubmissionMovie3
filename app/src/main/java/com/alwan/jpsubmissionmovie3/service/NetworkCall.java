package com.alwan.jpsubmissionmovie3.service;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;
import com.alwan.jpsubmissionmovie3.data.network.MovieResponse;
import com.alwan.jpsubmissionmovie3.data.network.TvShowResponse;
import com.alwan.jpsubmissionmovie3.utils.EspressoIdlingResource;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall {
    private static final String LANGUAGE = "en-US";
    private static NetworkCall INSTANCE;
    private static final TMDBApi apiClient = ApiClient.getClient().create(TMDBApi.class);
    private final String API_KEY = "7c5793b8ef1a32c7d47441dbbff44771";

    private NetworkCall() {
    }

    public static NetworkCall getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkCall();
        }
        return INSTANCE;
    }

    public void getPopularTv(MutableLiveData<List<TvShowEntity>> liveData) {
        EspressoIdlingResource.increment();
        Call<TvShowResponse> call = apiClient.getTvPopular(API_KEY, LANGUAGE, 1);
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NotNull Call<TvShowResponse> call, @NotNull Response<TvShowResponse> response) {
                if (response.isSuccessful()) {
                    TvShowResponse TvShowResponse = response.body();
                    if (TvShowResponse != null && TvShowResponse.getResults() != null) {
                        liveData.postValue(TvShowResponse.getResults());
                    } else {
                        Log.d("NetworkCall", "Empty Data");
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<TvShowResponse> call, @NotNull Throwable t) {
                Log.d("NetworkCall", "Failed Fetch getPopularMovie()/Failure");
            }
        });
        EspressoIdlingResource.decrement();
    }

    public void getPopularMovie(MutableLiveData<List<MovieEntity>> liveData) {
        EspressoIdlingResource.increment();
        Call<MovieResponse> call = apiClient.getMoviePopular(API_KEY, LANGUAGE, 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse moviesResponse = response.body();
                    if (moviesResponse != null && moviesResponse.getResults() != null) {
                        liveData.postValue(moviesResponse.getResults());
                    } else {
                        Log.d("NetworkCall", "Empty Data");
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.d("NetworkCall", "Failed Fetch getPopularMovie()/Failure");
            }
        });
        EspressoIdlingResource.decrement();
    }

    public LiveData<MovieEntity> getMovieById(int id) {
        EspressoIdlingResource.increment();
        MutableLiveData<MovieEntity> movieDataById = new MutableLiveData<>();
        Call<MovieEntity> MovieEntityCall = apiClient.getMovieById(id, API_KEY, LANGUAGE);
        MovieEntityCall.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(@NotNull Call<MovieEntity> call, @NotNull Response<MovieEntity> response) {
                if (response.isSuccessful()) {
                    MovieEntity MovieEntity = response.body();
                    if (MovieEntity != null) {
                        movieDataById.postValue(MovieEntity);
                    } else {
                        Log.d("NetworkCall", "Empty Data");
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<MovieEntity> call, @NotNull Throwable t) {
                Log.d("NetworkCall", "Failed Fetch getPopularMovie()/Failure");
            }
        });
        EspressoIdlingResource.decrement();
        return movieDataById;

    }

    public LiveData<TvShowEntity> getTvById(int id) {
        EspressoIdlingResource.increment();
        MutableLiveData<TvShowEntity> tvDataById = new MutableLiveData<>();
        Call<TvShowEntity> TvShowEntityCall = apiClient.getTvById(id, API_KEY, LANGUAGE);
        TvShowEntityCall.enqueue(new Callback<TvShowEntity>() {
            @Override
            public void onResponse(@NotNull Call<TvShowEntity> call, @NotNull Response<TvShowEntity> response) {
                if (response.isSuccessful()) {
                    TvShowEntity TvShowEntity = response.body();
                    if (TvShowEntity != null) {
                        tvDataById.postValue(TvShowEntity);
                    } else {
                        Log.d("NetworkCall", "Empty Data");
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<TvShowEntity> call, @NotNull Throwable t) {
                Log.d("NetworkCall", "Failed Fetch getPopularMovie()/Failure");
            }
        });
        EspressoIdlingResource.decrement();
        return tvDataById;
    }

    public void getUpcomingMovie(MutableLiveData<List<MovieEntity>> liveData) {
        EspressoIdlingResource.increment();
        Call<MovieResponse> call = apiClient.getMovieUpcoming(API_KEY, LANGUAGE, 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse moviesResponse = response.body();
                    if (moviesResponse != null && moviesResponse.getResults() != null) {
                        liveData.postValue(moviesResponse.getResults());
                    } else {
                        Log.d("NetworkCall", "Empty Data");
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.d("NetworkCall", "Failed Fetch getPopularMovie()/Failure");
            }
        });
        EspressoIdlingResource.decrement();
    }

}
