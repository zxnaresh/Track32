package com.gipl.track32.app.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gipl.track32.app.R;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by naresh on 23-Jul-2019.
 */
public class HomeFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public HomeFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
