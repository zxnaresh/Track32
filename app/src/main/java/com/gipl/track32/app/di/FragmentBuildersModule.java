package com.gipl.track32.app.di;

import com.gipl.track32.app.ui.splash.SplashFragment;
import com.gipl.track32.app.ui.users.UsersFragment;
import com.gipl.track32.app.ui.home.HomeFragment;
import com.gipl.track32.app.ui.login.LoginFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by naresh on 23-Jul-2019.
 */
@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract SplashFragment contributeSplashFragment();

    @ContributesAndroidInjector
    abstract LoginFragment contributeLoginFragment();

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract UsersFragment contributeEmployeeFragment();
}
