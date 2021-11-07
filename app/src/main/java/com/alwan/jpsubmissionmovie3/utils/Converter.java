package com.alwan.jpsubmissionmovie3.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Converter {
    @TypeConverter()
    public String genretoJson(List<Integer> data) {
        return new Gson().toJson(data);
    }

    @TypeConverter()
    public String generateJsontoData(String data) {
        return new Gson().fromJson(data, new TypeToken<List<Integer>>() {
        }.getType());
    }
}
