package com.sampleapp.service;


import com.sampleapp.model.Flowers;

import java.util.List;

import rx.Observable;

/**
 * Created by OneSnaps on 11/11/2016.
 */

public interface FlowerApiInterface {

    void onCompleted();

    void onError(String message);

    void onApiData(List<Flowers> androidResponses);

    Observable<List<Flowers>> getData();
}
