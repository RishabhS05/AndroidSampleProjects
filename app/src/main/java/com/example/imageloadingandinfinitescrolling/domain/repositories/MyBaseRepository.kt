package com.example.imageloadingandinfinitescrolling.domain.repositories

import com.example.baseArchitecture.dataLayer.state.RequestState
import com.example.baseArchitecture.domain.repositories.BaseRepository
import com.example.imageloadingandinfinitescrolling.dataLayer.models.ImageDataModel
import com.example.imageloadingandinfinitescrolling.dataLayer.response.ImageListResponse
import com.example.imageloadingandinfinitescrolling.domain.APIService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class MyBaseRepository  @Inject constructor (private val apiService: APIService ) : BaseRepository() , IBaseRepository {
    override suspend fun getImagesList(page: Int, limit: Int): Response<ArrayList<ImageDataModel>> {
        var obj : Response<ArrayList<ImageDataModel>>?
        try {
          obj = apiService.getImageList(page = page, limit = limit)
            Timber.d("obj $obj")
        } catch (e:Exception){
            Timber.d("My Base Repro ${e.printStackTrace()}")
            throw e
        }
        return obj
    }
}
