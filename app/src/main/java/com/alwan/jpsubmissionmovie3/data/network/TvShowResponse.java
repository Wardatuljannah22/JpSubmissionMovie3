package com.alwan.jpsubmissionmovie3.data.network;

import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<TvShowEntity> results;

    @SerializedName("total_results")
    private int totalResults;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<TvShowEntity> getResults() {
        return results;
    }

    public void setResults(List<TvShowEntity> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}