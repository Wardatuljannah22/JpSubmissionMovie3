package com.alwan.jpsubmissionmovie3.ui.favorite;

import android.annotation.SuppressLint;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alwan.jpsubmissionmovie3.R;
import com.alwan.jpsubmissionmovie3.ui.adapter.FavoriteMovieAdapter;
import com.alwan.jpsubmissionmovie3.utils.EspressoIdlingResource;
import com.alwan.jpsubmissionmovie3.viewmodel.FavoriteViewModel;
import com.alwan.jpsubmissionmovie3.viewmodel.ViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteMovieFragment extends Fragment {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_fav_movie)
    public RecyclerView rvMovie;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private FavoriteMovieAdapter movieAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @NonNull
    private FavoriteViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavoriteViewModel.class);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            FavoriteViewModel viewModel = obtainViewModel(getActivity());
            movieAdapter = new FavoriteMovieAdapter(getActivity());
            EspressoIdlingResource.increment();
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getAllMovie().observe(this, results -> {
                if (results != null) {
                    progressBar.setVisibility(View.GONE);
                    movieAdapter.submitList(results);
                    rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
                    rvMovie.setHasFixedSize(true);
                    rvMovie.setAdapter(movieAdapter);

                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "List Favorite Movie Null", Toast.LENGTH_SHORT).show();
                }
            });
            EspressoIdlingResource.decrement();
        }
    }

}
