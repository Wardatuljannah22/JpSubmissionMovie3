package com.alwan.jpsubmissionmovie3.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alwan.jpsubmissionmovie3.data.RemoteRepository;
import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;

import java.util.List;

public class MovieViewModel extends ViewModel {
    protected MutableLiveData<List<MovieEntity>> movieResults = new MutableLiveData<>();
    private final RemoteRepository repository;

    MovieViewModel(@NonNull RemoteRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("xxx", "data cleared");
    }

    public LiveData<List<MovieEntity>> mLiveMovieData() {
        if (movieResults.getValue() == null) {
            repository.mLiveMovieData(movieResults);
        }
        return movieResults;
    }

}

