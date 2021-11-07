package com.alwan.jpsubmissionmovie3.ui.tvShow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.alwan.jpsubmissionmovie3.R;
import com.alwan.jpsubmissionmovie3.ui.adapter.TvAdapter;
import com.alwan.jpsubmissionmovie3.ui.detail.DetailTvActivity;
import com.alwan.jpsubmissionmovie3.utils.EspressoIdlingResource;
import com.alwan.jpsubmissionmovie3.utils.ItemClickSupport;
import com.alwan.jpsubmissionmovie3.viewmodel.TvViewModel;
import com.alwan.jpsubmissionmovie3.viewmodel.ViewModelFactory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowFragment extends Fragment {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_tvShow)
    RecyclerView recyclerView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());
            TvViewModel viewModel = ViewModelProviders.of(this, factory).get(TvViewModel.class);
            EspressoIdlingResource.increment();
            progressBar.setVisibility(View.VISIBLE);
            viewModel.mLiveTvData().observe(this, results -> {
                if (results != null) {
                    progressBar.setVisibility(View.GONE);
                    TvAdapter tvAdapter = new TvAdapter(getActivity(), results);
                    ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v) -> {
                        Intent intent = new Intent(getActivity(), DetailTvActivity.class);
                        intent.putExtra(DetailTvActivity.EXTRA_TV, results.get(position));
                        startActivity(intent);
                    });
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(tvAdapter);
                    tvAdapter.notifyDataSetChanged();
                    EspressoIdlingResource.decrement();
                }
            });
        }
    }
}
