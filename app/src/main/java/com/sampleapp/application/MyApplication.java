package com.sampleapp.application;

import android.app.Application;

import com.sampleapp.dependencies.ApiComponent;
import com.sampleapp.dependencies.DaggerApiComponent;
import com.sampleapp.dependencies.DaggerNetworkComponent;
import com.sampleapp.dependencies.NetworkModule;
import com.sampleapp.urils.Constant;

/**
 * Created by OneSnaps on 11/11/2016.
 */

public class MyApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        defineDependency();
        super.onCreate();
    }

    private void defineDependency() {
        mApiComponent = DaggerApiComponent.builder()
                .networkComponent(DaggerNetworkComponent.builder()
                        .networkModule(new NetworkModule(Constant.BASE_URL)).build()).build();
    }

    public ApiComponent getmApiComponent() {
        return mApiComponent;
    }
}
