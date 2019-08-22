package com.gipl.track32.app.ui.users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.gipl.track32.app.repository.UserRepository;
import com.gipl.track32.app.ui.base.BaseViewModel;
import com.gipl.track32.app.vo.Resource;
import com.gipl.track32.app.vo.User;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by naresh on 02-Aug-2019.
 */
public class UsersViewModel extends BaseViewModel {
    private UserRepository repository;

    private MutableLiveData<String> loadUsers = new MutableLiveData<>();
    private LiveData<Resource<List<User>>> users = Transformations.switchMap(loadUsers, input -> repository.getUsers());

    @Inject
    public UsersViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public void getUsers() {
        loadUsers.setValue("");
    }

    public LiveData<Resource<List<User>>> getUsersLiveData() {
        return users;
    }
}
