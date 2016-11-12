package com.sampleapp.presenters;

import com.sampleapp.model.Flowers;
import com.sampleapp.service.FlowerApiInterface;

import java.util.List;

import rx.Observer;

/**
 * Created by OneSnaps on 11/11/2016.
 */

public class FlowerPresenter extends BasePresenter implements Observer<List<Flowers>> {

    FlowerApiInterface mApiService;

    public FlowerPresenter(FlowerApiInterface service) {
        mApiService = service;
    }

    @Override
    public void onCompleted() {
        mApiService.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mApiService.onError(e.getMessage());
    }

    @Override
    public void onNext(List<Flowers> androidModels) {
        mApiService.onApiData(androidModels);
    }

    public void fetchData() {
        unSubscribe();
        subscribe(mApiService.getData(), FlowerPresenter.this);
    }
}
