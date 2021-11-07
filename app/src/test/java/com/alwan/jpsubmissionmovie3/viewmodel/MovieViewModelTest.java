package com.alwan.jpsubmissionmovie3.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.alwan.jpsubmissionmovie3.data.RemoteRepository;
import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.alwan.jpsubmissionmovie3.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class MovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MovieViewModel viewModel;
    private RemoteRepository repository = mock(RemoteRepository.class);

    @Before
    public void setUp() {
        viewModel = spy(new MovieViewModel(repository));
    }

    @Test
    public void getMovies() {
        ArrayList<MovieEntity> dummyMovies = FakeDataDummy.generateDummyMovies();

        MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        Observer<List<MovieEntity>> observer = mock(Observer.class);
        viewModel.movieResults = movies;

        viewModel.movieResults.observeForever(observer);
        assertEquals(viewModel.movieResults, viewModel.mLiveMovieData());

        verify(observer).onChanged(dummyMovies);

    }

}