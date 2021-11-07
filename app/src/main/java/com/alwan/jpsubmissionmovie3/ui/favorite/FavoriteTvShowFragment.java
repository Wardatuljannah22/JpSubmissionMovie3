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
import com.alwan.jpsubmissionmovie3.ui.adapter.FavoriteTvAdapter;
import com.alwan.jpsubmissionmovie3.utils.EspressoIdlingResource;
import com.alwan.jpsubmissionmovie3.viewmodel.FavoriteViewModel;
import com.alwan.jpsubmissionmovie3.viewmodel.ViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteTvShowFragment extends Fragment {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_fav_tv)
    public RecyclerView rvTv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private FavoriteTvAdapter tvAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
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
            tvAdapter = new FavoriteTvAdapter(getActivity());
            EspressoIdlingResource.increment();
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getAllTv().observe(this, results -> {
                if (results != null) {
                    progressBar.setVisibility(View.GONE);
                    tvAdapter.submitList(results);
                    rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
                    rvTv.setHasFixedSize(true);
                    rvTv.setAdapter(tvAdapter);
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "List Favorite Tv Null", Toast.LENGTH_SHORT).show();
                }
            });
            EspressoIdlingResource.decrement();
        }
    }

}
