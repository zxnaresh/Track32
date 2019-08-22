package com.gipl.track32.app.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.gipl.track32.app.AppExecutors;
import com.gipl.track32.app.api.ApiResponse;
import com.gipl.track32.app.api.Track32WebService;
import com.gipl.track32.app.db.UserDao;
import com.gipl.track32.app.util.RateLimiter;
import com.gipl.track32.app.vo.Resource;
import com.gipl.track32.app.vo.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Created by naresh on 20-Aug-2019.
 */
public class UserRepository {
    private static final String TAG = "UserRepository";
    private AppExecutors appExecutors;
    private UserDao userDao;
    private Track32WebService webService;
    private RateLimiter<String> usersListRateLimiter = new RateLimiter<>(1, TimeUnit.MINUTES);
    private static final String KEY_ALL_USERS = "all_users";

    @Inject
    public UserRepository(AppExecutors appExecutors, UserDao userDao, Track32WebService webService) {
        this.appExecutors = appExecutors;
        this.userDao = userDao;
        this.webService = webService;
    }

    public LiveData<Resource<List<User>>> getUsers() {
        return new NetworkBoundResource<List<User>, List<User>>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull List<User> items) {
                userDao.insert(items.toArray(new User[0]));
                Log.d(TAG, "items inserted");
            }

            @Override
            protected boolean shouldFetch(@Nullable List<User> data) {
                boolean shouldFetch = data == null || data.size() == 0 || usersListRateLimiter.shouldFetch(KEY_ALL_USERS);
                Log.d(TAG, "should fetch: " + shouldFetch);
                return shouldFetch;
            }

            @NonNull
            @Override
            protected LiveData<List<User>> loadFromDb() {
                Log.d(TAG, "loading from db");
                return userDao.getAllUsers();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<User>>> createCall() {
                Log.d(TAG, "creating call");
                return webService.getUsers();
            }

            @Override
            protected void onFetchFailed() {
                usersListRateLimiter.reset(KEY_ALL_USERS);
            }
        }.getAsLiveData();
    }
}
