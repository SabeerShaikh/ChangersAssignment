package com.changersassignment.module.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.changersassignment.config.ChangersProducationDI;
import com.changersassignment.config.DI;


public class ChangersProductionViewModel extends AndroidViewModel {

    protected static DI di;
    protected static ChangersProductionViewModelFactory vmCommonFactory;
    protected Application application;

    public ChangersProductionViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        if (di == null) {
            di = new ChangersProducationDI(application);
            vmCommonFactory = di.provideViewModelFactory();
        }
    }
}
