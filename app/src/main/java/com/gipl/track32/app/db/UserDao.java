package com.gipl.track32.app.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gipl.track32.app.vo.User;

import java.util.List;

/**
 * Created by naresh on 20-Aug-2019.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... users);

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();
}
