package com.gipl.track32.app.di;

import android.app.Application;

import androidx.room.Room;

import com.gipl.track32.app.db.AppDB;
import com.gipl.track32.app.db.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by naresh on 21-Aug-2019.
 */
@Module
public class DBModule {
    @Singleton
    @Provides
    public AppDB provideAppDB(Application application) {
        return Room.databaseBuilder(application, AppDB.class, "app_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public UserDao provideUserDao(AppDB appDB) {
        return appDB.userDao();
    }
}
