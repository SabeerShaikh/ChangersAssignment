package com.changersassignment.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.changersassignment.domain.model.BreedData;

import java.util.ArrayList;

public interface MainRepository {
    MutableLiveData<RepositoryResponse<ArrayList<BreedData>>> getBreedData(int page, int limit);

    MutableLiveData<RepositoryResponse<ArrayList<BreedData>>> getSpecificBreed(String name);

}
