package com.changersassignment.module.base;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.changersassignment.module.ui.viewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;


public class ChangersProductionViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;

    public ChangersProductionViewModelFactory(Application application) {
        mApplication = application;
    }

    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {

        if (modelClass == MainViewModel.class) {
            return (T) new MainViewModel(mApplication);
        }

        return super.create(modelClass);
    }
}
