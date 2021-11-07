package com.alwan.jpsubmissionmovie3.di;

import android.app.Application;

import com.alwan.jpsubmissionmovie3.data.RemoteRepository;
import com.alwan.jpsubmissionmovie3.service.NetworkCall;

public class Injection {
    public static RemoteRepository provideRepository(Application application) {
        NetworkCall networkCall = NetworkCall.getInstance();
        return RemoteRepository.getInstance(networkCall,application);
    }
}
