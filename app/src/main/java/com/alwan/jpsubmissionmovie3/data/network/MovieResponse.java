package com.alwan.jpsubmissionmovie3.data.network;

import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<MovieEntity> results;

    @SerializedName("total_results")
    private int totalResults;

    public void setPage(int page){
        this.page = page;
    }

    public int getPage(){
        return page;
    }

    public void setTotalPages(int totalPages){
        this.totalPages = totalPages;
    }

    public int getTotalPages(){
        return totalPages;
    }

    public void setResults(List<MovieEntity> results){
        this.results = results;
    }

    public List<MovieEntity> getResults(){
        return results;
    }

    public void setTotalResults(int totalResults){
        this.totalResults = totalResults;
    }

    public int getTotalResults(){
        return totalResults;
    }
}
