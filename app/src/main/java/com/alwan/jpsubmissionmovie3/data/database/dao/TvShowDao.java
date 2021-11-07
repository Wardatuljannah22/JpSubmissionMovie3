package com.alwan.jpsubmissionmovie3.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;

@Dao
public interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TvShowEntity tv);

    @Delete()
    void delete(TvShowEntity tv);

    @Query("SELECT * from tvTable ORDER BY name ASC")
    DataSource.Factory<Integer, TvShowEntity> getAllTv();

    @Query("SELECT * from tvTable WHERE id =:id")
    LiveData<TvShowEntity> getTvById(int id);
}
