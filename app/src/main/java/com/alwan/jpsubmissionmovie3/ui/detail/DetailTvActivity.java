package com.alwan.jpsubmissionmovie3.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alwan.jpsubmissionmovie3.R;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;
import com.alwan.jpsubmissionmovie3.utils.EspressoIdlingResource;
import com.alwan.jpsubmissionmovie3.viewmodel.DetailViewModel;
import com.alwan.jpsubmissionmovie3.viewmodel.ViewModelFactory;
import com.bumptech.glide.Glide;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTvActivity extends AppCompatActivity {
    public static final String EXTRA_TV = "tv_id";
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.content)
    ConstraintLayout content;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_title)
    TextView textTitle;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.vote)
    TextView textVoteCount;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.release_date)
    TextView textReleaseDate;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.original_language)
    TextView textLanguage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ratingS)
    TextView textRating;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.overview)
    TextView textDescription;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.poster)
    ImageView imagePoster;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_fav)
    public ImageButton btnFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        TvShowEntity selectedTv;
        selectedTv = getIntent().getParcelableExtra(EXTRA_TV);
        ViewModelFactory factory = ViewModelFactory.getInstance(this.getApplication());
        DetailViewModel viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel.class);
        viewModel.setTvId(Objects.requireNonNull(selectedTv).getId());
        viewModel.getTvByIdRoom().observe(this, results -> {
            if (results == null) {
                btnFav.setImageResource(R.drawable.ic_favorite_border);
                btnFav.setOnClickListener(v -> {
                    viewModel.insertTv(selectedTv);
                    Toast.makeText(this, "Success Add to Favorite", Toast.LENGTH_SHORT).show();
                });

            } else {
                btnFav.setImageResource(R.drawable.ic_favorite);
                btnFav.setOnClickListener(v -> {
                    viewModel.deleteTv(selectedTv);
                    Toast.makeText(this, "Success Remove from Favorite", Toast.LENGTH_SHORT).show();
                });
            }
        });

        if (selectedTv.getId() != 0) {
            EspressoIdlingResource.increment();
            viewModel.getTvById().observe(this, results -> {
                progressBar.setVisibility(View.GONE);
                Glide.with(this).load("https://image.tmdb.org/t/p/w780" + results.getBackdropPath()).into(imagePoster);
                textTitle.setText(results.getName());
                textRating.setText(results.getVoteAverage());
                textVoteCount.setText(results.getVoteCount());
                textReleaseDate.setText(results.getFirstAirDate());
                textLanguage.setText(results.getOriginalLanguage());
                textDescription.setText(results.getOverview());
                EspressoIdlingResource.decrement();
            });
        } else {
            Log.d("DetailMovie", "movie id = 0");
        }
    }

}

