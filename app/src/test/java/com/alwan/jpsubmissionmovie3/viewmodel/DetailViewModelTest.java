package com.alwan.jpsubmissionmovie3.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.alwan.jpsubmissionmovie3.data.RemoteRepository;
import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;
import com.alwan.jpsubmissionmovie3.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailViewModel viewModel;
    private final RemoteRepository repository = mock(RemoteRepository.class);
    private final MovieEntity dummyMovie = FakeDataDummy.generateDummyMovies().get(0);
    private final Integer movieId = dummyMovie.getId();

    private final TvShowEntity dummyTv = FakeDataDummy.generateDummyTvs().get(0);
    private final Integer tvId = dummyTv.getId();


    @Before
    public void setUp() {
        viewModel = new DetailViewModel(repository);
        viewModel.setMovieId(movieId);
        viewModel.setTvId(tvId);
    }

    @Test
    public void getMovieById() {
        MutableLiveData<MovieEntity> movieResult = new MutableLiveData<>();
        movieResult.setValue(dummyMovie);

        when(repository.mLiveMovieDataById(movieId)).thenReturn(movieResult);

        Observer<MovieEntity> observer = mock(Observer.class);

        viewModel.getMovieById().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }
    @Test
    public void getTvById() {
        MutableLiveData<TvShowEntity> tvResult = new MutableLiveData<>();
        tvResult.setValue(dummyTv);

        when(repository.mLiveTvDataById(tvId)).thenReturn(tvResult);

        Observer<TvShowEntity> observer = mock(Observer.class);

        viewModel.getTvById().observeForever(observer);

        verify(observer).onChanged(dummyTv);
    }

}