package com.alwan.jpsubmissionmovie3.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alwan.jpsubmissionmovie3.R;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;
import com.alwan.jpsubmissionmovie3.ui.detail.DetailTvActivity;
import com.bumptech.glide.Glide;

public class FavoriteTvAdapter extends PagedListAdapter<TvShowEntity, FavoriteTvAdapter.NoteViewHolder> {
    private static final DiffUtil.ItemCallback<TvShowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvShowEntity>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(TvShowEntity oldNote, TvShowEntity newNote) {
                    return oldNote.getName().equals(newNote.getName());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(TvShowEntity oldNote, @NonNull TvShowEntity newNote) {
                    return oldNote.equals(newNote);
                }
            };
    private final Activity activity;

    public FavoriteTvAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, int position) {
        final TvShowEntity movieResults = getItem(position);
        if (movieResults != null) {
            holder.tv_title.setText(movieResults.getName());
            holder.tv_release.setText(movieResults.getFirstAirDate());
            holder.tv_description.setText(movieResults.getOverview());
            Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w500" + movieResults.getPosterPath()).into(holder.poster);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, DetailTvActivity.class);
                intent.putExtra(DetailTvActivity.EXTRA_TV, movieResults);
                activity.startActivity(intent);
            });
        }
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView tv_title,tv_release,tv_description;
        final ImageView poster;
        NoteViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_item_title);
            tv_release = itemView.findViewById(R.id.tv_item_release_date);
            tv_description = itemView.findViewById(R.id.tv_item_description);
            poster = itemView.findViewById(R.id.img_poster);

        }
    }
}

