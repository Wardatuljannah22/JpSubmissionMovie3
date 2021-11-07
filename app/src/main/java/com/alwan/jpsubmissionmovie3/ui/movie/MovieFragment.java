package com.alwan.jpsubmissionmovie3.ui.movie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.alwan.jpsubmissionmovie3.R;
import com.alwan.jpsubmissionmovie3.ui.adapter.MovieAdapter;
import com.alwan.jpsubmissionmovie3.ui.detail.DetailMovieActivity;
import com.alwan.jpsubmissionmovie3.utils.EspressoIdlingResource;
import com.alwan.jpsubmissionmovie3.utils.ItemClickSupport;
import com.alwan.jpsubmissionmovie3.viewmodel.MovieViewModel;
import com.alwan.jpsubmissionmovie3.viewmodel.ViewModelFactory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_movie)
    RecyclerView recyclerView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.container)
    public FrameLayout container;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @NonNull
    private MovieViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            MovieViewModel viewModel = obtainViewModel(getActivity());
            EspressoIdlingResource.increment();
            progressBar.setVisibility(View.VISIBLE);
            viewModel.mLiveMovieData().observe(this, results -> {
                if (results != null) {
                    MovieAdapter movieAdapter = new MovieAdapter(getActivity(), results);
                    progressBar.setVisibility(View.GONE);
                    ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v) -> {
                        Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
                        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, results.get(position));
                        startActivity(intent);
                    });
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(movieAdapter);
                    movieAdapter.notifyDataSetChanged();
                    EspressoIdlingResource.decrement();
                }
            });
        }
    }
}
