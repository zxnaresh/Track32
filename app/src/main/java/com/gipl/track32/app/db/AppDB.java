package com.gipl.track32.app.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gipl.track32.app.vo.User;

/**
 * Created by naresh on 20-Aug-2019.
 */
@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    public abstract UserDao userDao();
}
