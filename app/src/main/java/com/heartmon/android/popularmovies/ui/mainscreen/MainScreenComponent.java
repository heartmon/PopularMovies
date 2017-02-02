package com.heartmon.android.popularmovies.ui.mainscreen;

import com.heartmon.android.popularmovies.util.ActivityScope;
import com.heartmon.android.popularmovies.data.MovieRepositoryComponent;

import dagger.Component;

/**
 * Created by heartmon on 1/22/2017.
 */

@ActivityScope
@Component(dependencies = {MovieRepositoryComponent.class}, modules = {MainActivityModule.class})
public interface MainScreenComponent {
    //We have to refer explicitly in which classes we want to inject dependencies from this component
    //Where
    void inject(MainActivity activity);

    //WHAT do we want to inject? We need to expose our objects from the module
//    MainScreenContract.Presenter presenter();
}