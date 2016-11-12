package com.sampleapp.presenters;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by OneSnaps on 11/11/2016.
 */

public class BasePresenter implements Presenter {

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onCreate() {
        initSubscriber();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        unSubscribe();
    }

    public CompositeSubscription initSubscriber() {
        if (mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription = new CompositeSubscription();
        }

        return mCompositeSubscription;
    }

    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.isUnsubscribed();
            mCompositeSubscription.clear();
        }
    }

    protected <R> void subscribe(Observable<R> observable, Observer<R> observer) {
        Subscription subscription = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);
        initSubscriber().add(subscription);
    }
}
