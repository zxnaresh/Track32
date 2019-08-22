package com.gipl.track32.app.api;

import androidx.lifecycle.LiveData;

import com.gipl.track32.app.vo.User;

import java.util.List;

import retrofit2.http.GET;

/**
 * Created by naresh on 19-Jul-2019.
 */
public interface Track32WebService {
    @GET("users")
    LiveData<ApiResponse<List<User>>> getUsers();
}
