package com.example.imageloadingandinfinitescrolling.domain.repositories

import com.example.baseArchitecture.dataLayer.state.RequestState
import com.example.baseArchitecture.domain.repositories.BaseRepository
import com.example.imageloadingandinfinitescrolling.dataLayer.models.ImageDataModel
import com.example.imageloadingandinfinitescrolling.domain.APIService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyBaseRepository  @Inject constructor (private val apiService: APIService ) : BaseRepository() , IBaseRepository {
    override suspend fun getImagesList(): Flow<RequestState<List<ImageDataModel>>> {
        TODO("Not yet implemented")
    }

}