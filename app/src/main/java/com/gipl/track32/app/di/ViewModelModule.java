package com.gipl.track32.app.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gipl.track32.app.ui.employee.UsersViewModel;
import com.gipl.track32.app.ui.home.HomeViewodel;
import com.gipl.track32.app.ui.login.LoginViewModel;
import com.gipl.track32.app.ui.splash.SplashViewModel;
import com.gipl.track32.app.viewmodel.Track32ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by naresh on 16-Jul-2019.
 */
@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelMapKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelMapKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewodel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelMapKey(HomeViewodel.class)
    abstract ViewModel bindHomeViewModel(HomeViewodel viewodel);

    @Binds
    @IntoMap
    @ViewModelMapKey(UsersViewModel.class)
    abstract ViewModel bindEmployeeViewModel(UsersViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(Track32ViewModelFactory viewModelFactory);
}
