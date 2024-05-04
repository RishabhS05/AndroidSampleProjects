package com.example.imageloadingandinfinitescrolling.domain.repositories

import com.example.baseArchitecture.dataLayer.state.RequestState
import com.example.imageloadingandinfinitescrolling.dataLayer.models.ImageDataModel
import com.example.imageloadingandinfinitescrolling.dataLayer.response.ImageListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface INetworkRepository {
    suspend fun getImagesList(page : Int , limit : Int): Response<ArrayList<ImageDataModel>>
}