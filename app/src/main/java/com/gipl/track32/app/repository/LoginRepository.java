package com.gipl.track32.app.repository;

import com.gipl.track32.app.AppExecutors;
import com.gipl.track32.app.api.Track32WebService;

import javax.inject.Inject;

/**
 * Created by naresh on 23-Jul-2019.
 */
public class LoginRepository {

    @Inject
    public LoginRepository(AppExecutors appExecutors, Track32WebService webService) {
    }
}
