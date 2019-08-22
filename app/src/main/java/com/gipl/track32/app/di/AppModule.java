package com.gipl.track32.app.di;

import com.gipl.track32.app.api.Track32WebService;
import com.gipl.track32.app.util.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by naresh on 16-Jul-2019.
 */
@Module(includes = {ViewModelModule.class, DBModule.class})
class AppModule {
    @Singleton
    @Provides
    Track32WebService provideTrack32WebService() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(Track32WebService.class);
    }

}
