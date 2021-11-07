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
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;
import com.bumptech.glide.Glide;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.MyViewHolder> {

    private final List<TvShowEntity> tvList;

    private final Context mContext;

    public TvAdapter(Context context, List<TvShowEntity> tvList) {
        this.tvList = tvList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.MyViewHolder holder, int i) {
        holder.tv_title.setText(tvList.get(i).getName());
        holder.tv_release.setText(tvList.get(i).getFirstAirDate());
        holder.tv_description.setText(tvList.get(i).getOverview());
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500" + tvList.get(i).getPosterPath()).into(holder.poster);
    }
    @Override
    public int getItemCount() {
        return tvList.size();
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
