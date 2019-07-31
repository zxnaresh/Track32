package com.gipl.track32.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Created by naresh on 16-Jul-2019.
 */
@Singleton
public class Track32ViewModelFactory implements ViewModelProvider.Factory {
    private Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap;

    @Inject
    public Track32ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        this.providerMap = providerMap;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<ViewModel> provider = null;
        for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : providerMap.entrySet()) {
            if (modelClass.isAssignableFrom(entry.getKey())) {
                provider = entry.getValue();
                break;
            }
        }

        if (provider == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass.getName());
        }

        try {
            return (T) provider.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
