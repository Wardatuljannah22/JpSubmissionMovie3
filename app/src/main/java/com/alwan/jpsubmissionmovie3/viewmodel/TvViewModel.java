package com.alwan.jpsubmissionmovie3.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alwan.jpsubmissionmovie3.data.RemoteRepository;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;

import java.util.List;

public class TvViewModel extends ViewModel {
    private final RemoteRepository repository;
    protected MutableLiveData<List<TvShowEntity>> tvResults = new MutableLiveData<>();


    TvViewModel(RemoteRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("xxx", "data cleared");
    }

    public LiveData<List<TvShowEntity>> mLiveTvData() {
        if (tvResults.getValue() == null) {
            repository.mLiveTvData(tvResults);
        }
        return tvResults;
    }

}
