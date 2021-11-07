package com.alwan.jpsubmissionmovie3.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.alwan.jpsubmissionmovie3.data.RemoteRepository;
import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;

public class FavoriteViewModel extends ViewModel {
    private final RemoteRepository repository;

    FavoriteViewModel(@NonNull RemoteRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("xxx", "data cleared");
    }

    public LiveData<PagedList<MovieEntity>> getAllMovie() {
        return new LivePagedListBuilder<>(repository.getAllMovie(), 10).build();
    }

    public LiveData<PagedList<TvShowEntity>> getAllTv() {
        return new LivePagedListBuilder<>(repository.getAllTv(), 10).build();
    }

}
