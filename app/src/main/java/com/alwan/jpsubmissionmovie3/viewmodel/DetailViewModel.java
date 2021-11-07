package com.alwan.jpsubmissionmovie3.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alwan.jpsubmissionmovie3.data.RemoteRepository;
import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;

public class DetailViewModel extends ViewModel {

    private Integer movieId;
    private Integer tvId;
    private final RemoteRepository repository;

    DetailViewModel(RemoteRepository repository) {
        this.repository = repository;
    }


    public void setTvId(Integer tvId) {
        this.tvId = tvId;
    }


    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("xxx", "data cleared");
    }

    public LiveData<MovieEntity> getMovieById() {
        return repository.mLiveMovieDataById(movieId);
    }

    public LiveData<TvShowEntity> getTvById() {
        return repository.mLiveTvDataById(tvId);
    }

    public LiveData<TvShowEntity> getTvByIdRoom() {
        return repository.getTvByIdRoom(tvId);
    }

    public void insertTv(TvShowEntity tvResults) {
        repository.insertTv(tvResults);
    }

    public void deleteTv(TvShowEntity tvResults) {
        repository.deleteTv(tvResults);
    }

    public LiveData<MovieEntity> getMovieByIdRoom() {
        return repository.getMovieByIdRoom(movieId);
    }

    public void insertMovie(MovieEntity movieResults) {
        repository.insertMovie(movieResults);
    }

    public void deleteMovie(MovieEntity movieResults) {
        repository.deleteMovie(movieResults);
    }

}

