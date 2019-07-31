package com.gipl.track32.app.ui.login;

import com.gipl.track32.app.repository.LoginRepository;
import com.gipl.track32.app.ui.base.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by naresh on 02-Jul-2019.
 */
public class LoginViewModel extends BaseViewModel {
    private LoginRepository repository;

    @Inject
    public LoginViewModel(LoginRepository repository) {
        this.repository = repository;
    }
}
