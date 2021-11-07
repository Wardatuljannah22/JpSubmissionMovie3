package com.alwan.jpsubmissionmovie3.utils;

import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;

import java.util.ArrayList;

public class FakeDataDummy {
    public static ArrayList<MovieEntity> generateDummyMovies() {

        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(new MovieEntity(
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                "en",
                "Joker",
                false,
                "Joker",
                "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
                "2019-10-02",
                410.601,
                "8.2",
                475557,
                false,
                "15921"));
        return movies;
    }

    public static ArrayList<TvShowEntity> generateDummyTvs() {

        ArrayList<TvShowEntity> movies = new ArrayList<>();

        movies.add(new TvShowEntity(
                "2012-10-10",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "en",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "/dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg",
                "Arrow",
                129.723,
                "6.5",
                "Arrow",
                1412,
                "4172"
        ));

        return movies;
    }

    public static ArrayList<MovieEntity> generateDummyMoviesById(Integer movieId) {

        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(new MovieEntity(
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                "en",
                "Joker",
                false,
                "Joker",
                "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
                "2019-10-02",
                410.601,
                "8.5",
                475557,
                false,
                "5386"));
        return movies;
    }

}
