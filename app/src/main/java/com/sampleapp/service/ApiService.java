package com.sampleapp.service;

import com.sampleapp.model.Flowers;
import com.sampleapp.urils.Constant;

import java.util.List;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by OneSnaps on 11/11/2016.
 */

public interface ApiService {

    @POST(Constant.END_POINT)
    Observable<List<Flowers>> getApiData();
}
