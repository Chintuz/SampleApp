package com.sampleapp.dependencies;

import com.sampleapp.service.ApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by OneSnaps on 11/11/2016.
 */
@Module
public class ApiModule {

    @Provides
    @CustomScope
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
