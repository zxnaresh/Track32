package com.gipl.track32.app.ui.employee;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gipl.track32.app.R;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends Fragment {
    private static final String TAG = "UsersFragment";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private UsersViewModel viewModel;

    public UsersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UsersViewModel.class);
        viewModel.getUsersLiveData().observe(this, resource -> {

            Log.d(TAG, "status : " + resource.status);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        view.findViewById(R.id.button).setOnClickListener(v -> {
            Log.d(TAG, " ------------------------- \n getUsers() called...");
            viewModel.getUsers();
        });
        return view;
    }

}
