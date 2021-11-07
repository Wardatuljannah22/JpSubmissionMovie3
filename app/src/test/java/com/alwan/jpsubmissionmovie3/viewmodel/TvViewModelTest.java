package com.alwan.jpsubmissionmovie3.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.alwan.jpsubmissionmovie3.data.RemoteRepository;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;
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

public class TvViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TvViewModel viewModel;
    private final RemoteRepository repository = mock(RemoteRepository.class);

    @Before
    public void setUp() {
        viewModel = spy(new TvViewModel(repository));
    }

    @Test
    public void getTvs() {
        ArrayList<TvShowEntity> dummyMovies = FakeDataDummy.generateDummyTvs();

        MutableLiveData<List<TvShowEntity>> tvs = new MutableLiveData<>();
        tvs.setValue(dummyMovies);

        Observer<List<TvShowEntity>> observer = mock(Observer.class);
        viewModel.tvResults = tvs;

        viewModel.tvResults.observeForever(observer);

        assertEquals(viewModel.tvResults, viewModel.mLiveTvData());

        verify(observer).onChanged(dummyMovies);
    }

}