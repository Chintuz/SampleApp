package com.sampleapp.dependencies;

import com.sampleapp.MainActivity;

import dagger.Component;

/**
 * Created by OneSnaps on 11/11/2016.
 */

@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    void bind(MainActivity activity);
}
