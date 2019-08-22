package com.gipl.track32.app.ui.splash;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gipl.track32.app.R;
import com.gipl.track32.app.databinding.FragmentSplashBinding;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private FragmentSplashBinding binding;

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false);
        return binding.getRoot();
    }

}
