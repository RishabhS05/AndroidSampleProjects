package com.example.imageloadingandinfinitescrolling.domain.repositories

import com.example.baseArchitecture.dataLayer.state.RequestState
import com.example.imageloadingandinfinitescrolling.dataLayer.models.ImageDataModel
import kotlinx.coroutines.flow.Flow

interface INetworkRepository {
    suspend fun getImagesList(): Flow<RequestState<List<ImageDataModel>>>
}