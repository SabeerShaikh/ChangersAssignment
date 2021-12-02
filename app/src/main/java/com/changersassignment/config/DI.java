package com.changersassignment.config;

import android.app.Application;

import com.changersassignment.domain.repository.MainRepository;
import com.changersassignment.module.base.ChangersProductionViewModelFactory;
import com.enfecassignment.domain.repository.remote.api.MainService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public interface DI {
    /**
     * Repositories
     */
    MainRepository provideMainRepository();
    /** ENDS Repositories Providers */

    /**
     * API Service Providers
     **/

    MainService provideMainService();

    /**
     * ENDS API Service Providers
     **/

    /**
     * ENDS API Service Providers
     **/


    OkHttpClient provideOkHttpClient();

    Application provideApplication();

    Retrofit provideRetrofit();


    ChangersProductionViewModelFactory provideViewModelFactory();


}
