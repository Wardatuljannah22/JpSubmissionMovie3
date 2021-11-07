package com.alwan.jpsubmissionmovie3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.alwan.jpsubmissionmovie3.data.RemoteRepository;
import com.alwan.jpsubmissionmovie3.di.Injection;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final RemoteRepository repository;

    private ViewModelFactory(RemoteRepository repository) {
        this.repository = repository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieViewModel(repository);
        } else if (modelClass.isAssignableFrom(TvViewModel.class)) {
            //noinspection unchecked
            return (T) new TvViewModel(repository);
        } else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailViewModel(repository);
        }else if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            //noinspection unchecked
            return (T) new FavoriteViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
