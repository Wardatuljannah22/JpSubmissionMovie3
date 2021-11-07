package com.alwan.jpsubmissionmovie3.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.alwan.jpsubmissionmovie3.data.database.dao.MovieDao;
import com.alwan.jpsubmissionmovie3.data.database.dao.TvShowDao;
import com.alwan.jpsubmissionmovie3.data.entity.MovieEntity;
import com.alwan.jpsubmissionmovie3.data.entity.TvShowEntity;
import com.alwan.jpsubmissionmovie3.utils.Converter;

@Database(entities = {MovieEntity.class, TvShowEntity.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class MovieDatabase extends RoomDatabase {
    private static volatile MovieDatabase INSTANCE;

    public static MovieDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDatabase.class, "movie")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MovieDao movieDao();

    public abstract TvShowDao tvDao();

}
