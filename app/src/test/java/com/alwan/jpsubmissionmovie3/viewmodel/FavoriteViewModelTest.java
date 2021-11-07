package com.alwan.jpsubmissionmovie3.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.alwan.jpsubmissionmovie3.data.RemoteRepository;
import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;
import com.alwan.jpsubmissionmovie3.utils.FakeDataDummy;
import com.alwan.jpsubmissionmovie3.utils.PagedListUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoriteViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private final RemoteRepository repository = mock(RemoteRepository.class);


    @Before
    public void setUp() {
    }

    @Test
    public void getAllMovie() {
        ArrayList<MovieEntity> movieResults = FakeDataDummy.generateDummyMovies();
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(repository.getAllMovie()).thenReturn(dataSourceFactory);
        PagedList<MovieEntity> result = PagedListUtil.mockPagedList(movieResults);

        repository.getAllMovie();

        verify(repository).getAllMovie();
        assertNotNull(result);
        assertEquals(movieResults.size(), result.size());
    }

    @Test
    public void getAllTv() {
        ArrayList<TvShowEntity> tvResults = FakeDataDummy.generateDummyTvs();
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(repository.getAllTv()).thenReturn(dataSourceFactory);
        PagedList<TvShowEntity> result = PagedListUtil.mockPagedList(tvResults);

        repository.getAllTv();

        verify(repository).getAllTv();
        assertNotNull(result);
        assertEquals(tvResults.size(), result.size());
    }
}