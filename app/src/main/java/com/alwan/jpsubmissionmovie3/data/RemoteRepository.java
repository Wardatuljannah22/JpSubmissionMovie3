package com.alwan.jpsubmissionmovie3.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.alwan.jpsubmissionmovie3.data.database.MovieDatabase;
import com.alwan.jpsubmissionmovie3.data.database.dao.MovieDao;
import com.alwan.jpsubmissionmovie3.data.database.dao.TvShowDao;
import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;
import com.alwan.jpsubmissionmovie3.service.NetworkCall;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private final MovieDao movieDao;
    private final TvShowDao tvDao;
    private final NetworkCall networkCall;
    private final ExecutorService executorService;

    private RemoteRepository(NetworkCall networkCall, Application application) {
        this.networkCall = networkCall;
        executorService = Executors.newSingleThreadExecutor();

        MovieDatabase db = MovieDatabase.getDatabase(application);
        movieDao = db.movieDao();
        tvDao = db.tvDao();
    }

    public static RemoteRepository getInstance(NetworkCall networkCall, Application application) {

        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(networkCall, application);
        }
        return INSTANCE;

    }

    // Methods for MovieFragment
    public void mLiveMovieData(MutableLiveData<List<MovieEntity>> liveData) {
        networkCall.getPopularMovie(liveData);
    }

    // Methods for MovieFragment
    public void mLiveMovieDataUpcoming(MutableLiveData<List<MovieEntity>> liveData) {
        networkCall.getUpcomingMovie(liveData);
    }

    // Methods for TvFragment
    public void mLiveTvData(MutableLiveData<List<TvShowEntity>>liveData) {
        networkCall.getPopularTv(liveData);
    }

    // Methods for MovieMovie
    public LiveData<MovieEntity> mLiveMovieDataById(Integer id) {
        return networkCall.getMovieById(id);
    }

    // Methods for DetailTv
    public LiveData<TvShowEntity> mLiveTvDataById(Integer id) {
        return networkCall.getTvById(id);
    }

    public DataSource.Factory<Integer, MovieEntity> getAllMovie() {
        return movieDao.getAllmovie();
    }

    public DataSource.Factory<Integer, TvShowEntity> getAllTv() {
        return tvDao.getAllTv();
    }

    public LiveData<MovieEntity> getMovieByIdRoom(int id) {
        return movieDao.getMovieById(id);
    }

    public LiveData<TvShowEntity> getTvByIdRoom(int id) {
        return tvDao.getTvById(id);
    }


    public void insertMovie(final MovieEntity MovieEntity) {
        executorService.execute(() -> movieDao.insert(MovieEntity));
    }

    public void deleteMovie(final MovieEntity MovieEntity) {
        executorService.execute(() -> movieDao.delete(MovieEntity));
    }

    public void insertTv(final TvShowEntity TvShowEntity) {
        executorService.execute(() -> tvDao.insert(TvShowEntity));
    }

    public void deleteTv(final TvShowEntity TvShowEntity) {
        executorService.execute(() -> tvDao.delete(TvShowEntity));
    }
}

