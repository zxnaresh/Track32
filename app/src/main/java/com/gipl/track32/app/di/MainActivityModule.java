package com.gipl.track32.app.di;

import com.gipl.track32.app.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by naresh on 23-Jul-2019.
 */
@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    public abstract MainActivity contributeMainActivity();
}
