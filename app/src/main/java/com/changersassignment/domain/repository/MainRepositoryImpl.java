package com.changersassignment.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.changersassignment.domain.model.BreedData;
import com.enfecassignment.domain.repository.remote.api.MainService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainRepositoryImpl implements MainRepository {

    private MainService service;

    public MainRepositoryImpl(MainService fileService) {
        this.service = fileService;
    }

    @Override
    public MutableLiveData<RepositoryResponse<ArrayList<BreedData>>> getBreedData(int page, int limit) {
        MutableLiveData<RepositoryResponse<ArrayList<BreedData>>> result = new MutableLiveData<>();

        service.getBreeds(page, limit).enqueue(new Callback<ArrayList<BreedData>>() {

            @Override
            public void onResponse(Call<ArrayList<BreedData>> call, Response<ArrayList<BreedData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(new RepositoryResponse<>(response.body()));

                }
            }

            @Override
            public void onFailure(Call<ArrayList<BreedData>> call, Throwable t) {
                result.setValue(new RepositoryResponse<ArrayList<BreedData>>(t.getMessage(),
                        400));

            }

        });
        return result;
    }

    @Override
    public MutableLiveData<RepositoryResponse<ArrayList<BreedData>>> getSpecificBreed(String breedName) {
        MutableLiveData<RepositoryResponse<ArrayList<BreedData>>> result = new MutableLiveData<>();

        service.getSpecificBreed(breedName).enqueue(new Callback<ArrayList<BreedData>>() {

            @Override
            public void onResponse(Call<ArrayList<BreedData>> call, Response<ArrayList<BreedData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(new RepositoryResponse<>(response.body()));

                }
            }

            @Override
            public void onFailure(Call<ArrayList<BreedData>> call, Throwable t) {

                result.setValue(new RepositoryResponse<ArrayList<BreedData>>(t.getMessage(),
                        400));

            }

        });
        return result;
    }

}
