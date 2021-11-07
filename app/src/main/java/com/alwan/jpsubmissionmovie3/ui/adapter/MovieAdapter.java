package com.alwan.jpsubmissionmovie3.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alwan.jpsubmissionmovie3.R;
import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private final List<MovieEntity> movieList;

    private final Context mContext;

    public MovieAdapter(Context context, List<MovieEntity> movieList) {
        this.movieList = movieList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int i) {
        holder.tv_title.setText(movieList.get(i).getTitle());
        holder.tv_release.setText(movieList.get(i).getReleaseDate());
        holder.tv_description.setText(movieList.get(i).getOverview());
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500" + movieList.get(i).getPosterPath()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView tv_title,tv_release,tv_description;
        final ImageView poster;

        MyViewHolder(View view) {
            super(view);
            tv_title = itemView.findViewById(R.id.tv_item_title);
            tv_release = itemView.findViewById(R.id.tv_item_release_date);
            tv_description = itemView.findViewById(R.id.tv_item_description);
            poster = itemView.findViewById(R.id.img_poster);

        }
    }
}