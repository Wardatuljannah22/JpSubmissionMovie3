package com.alwan.jpsubmissionmovie3.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.alwan.jpsubmissionmovie3.R;
import com.alwan.jpsubmissionmovie3.ui.favorite.FavoriteFragment;
import com.alwan.jpsubmissionmovie3.ui.movie.MovieFragment;
import com.alwan.jpsubmissionmovie3.ui.tvShow.TvShowFragment;
import com.alwan.jpsubmissionmovie3.viewmodel.NavigationViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {
    final Fragment fragmentMovie = new MovieFragment();
    final Fragment fragmentTv = new TvShowFragment();
    final Fragment fragmentFavorite = new FavoriteFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentMovie;
    private NavigationViewModel viewModel;
    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.nav_bottom_1:
                fm.beginTransaction().hide(active).show(fragmentMovie).commit();
                viewModel.currentPage = 0;
                active = fragmentMovie;
                return true;

            case R.id.nav_bottom_2:
                fm.beginTransaction().hide(active).show(fragmentTv).commit();
                viewModel.currentPage = 1;
                active = fragmentTv;
                return true;

            case R.id.nav_bottom_3:
                fm.beginTransaction().hide(active).show(fragmentFavorite).commit();
                viewModel.currentPage = 2;
                active = fragmentFavorite;
                return true;

        }
        return false;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        viewModel = ViewModelProviders.of(this).get(NavigationViewModel.class);
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fm.beginTransaction().add(R.id.nav_container, fragmentFavorite, "Tv").hide(fragmentFavorite).commit();
        fm.beginTransaction().add(R.id.nav_container, fragmentTv, "Tv").hide(fragmentTv).commit();
        fm.beginTransaction().add(R.id.nav_container, fragmentMovie, "Movie").commit();
        if (viewModel.currentPage == 0) {
            fm.beginTransaction().hide(active).show(fragmentMovie).commit();
            active = fragmentMovie;
        } else if(viewModel.currentPage == 1) {
            fm.beginTransaction().hide(active).show(fragmentTv).commit();
            active = fragmentTv;
        }else {
            fm.beginTransaction().hide(active).show(fragmentFavorite).commit();
            active = fragmentFavorite;
        }
    }

}
