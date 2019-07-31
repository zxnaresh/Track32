package com.gipl.track32.app.di;

import android.app.Application;

import com.gipl.track32.app.Track32App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by naresh on 16-Jul-2019.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class,AppModule.class, MainActivityModule.class})
public interface AppComponent {
    void inject(Track32App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application app);

        AppComponent build();
    }
}
