package com.alwan.jpsubmissionmovie3.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieEntity movie);

    @Delete()
    void delete(MovieEntity movie);

    @Query("SELECT * from movieTable WHERE id =:id")
    LiveData<MovieEntity> getMovieById(int id);

    @Query("SELECT * from movieTable ORDER BY title ASC")
    DataSource.Factory<Integer, MovieEntity> getAllmovie();
}
