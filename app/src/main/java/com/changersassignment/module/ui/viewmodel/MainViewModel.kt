package com.changersassignment.module.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.changersassignment.domain.model.BreedData
import com.changersassignment.domain.repository.MainRepository
import com.changersassignment.domain.repository.RepositoryResponse
import com.changersassignment.module.base.ChangersProductionViewModel
import com.changersassignment.module.base.SPViewModelResponse

import java.util.*

class MainViewModel(application: Application) :
    ChangersProductionViewModel(application) {
    var repository: MainRepository

    fun getSpecificBreed(breedName: String): LiveData<SPViewModelResponse<java.util.ArrayList<BreedData>?>> {
        return Transformations.map<RepositoryResponse<java.util.ArrayList<BreedData>?>,
                SPViewModelResponse<java.util.ArrayList<BreedData>?>>(
            repository!!
                .getSpecificBreed(breedName)
        ) { repoResponse ->

            if (repoResponse.success) {
                return@map SPViewModelResponse(repoResponse.repositoryResponse)
            }
            SPViewModelResponse(repoResponse.failureMessage, repoResponse.code)
        }
    }

    fun postsData(
        page: Int,
        limit: Int
    ): LiveData<SPViewModelResponse<java.util.ArrayList<BreedData>?>> {
        return Transformations.map<RepositoryResponse<java.util.ArrayList<BreedData>?>,
                SPViewModelResponse<java.util.ArrayList<BreedData>?>>(
            repository!!
                .getBreedData(page, limit)
        ) { repoResponse ->

            if (repoResponse.success) {
                return@map SPViewModelResponse(repoResponse.repositoryResponse)
            }
            SPViewModelResponse(repoResponse.failureMessage, repoResponse.code)
        }
    }


    init {
        repository = di.provideMainRepository()
    }
}
