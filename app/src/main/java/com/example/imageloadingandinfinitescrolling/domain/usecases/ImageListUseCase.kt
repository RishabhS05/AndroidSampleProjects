package com.example.imageloadingandinfinitescrolling.domain.usecases

import com.example.baseArchitecture.dataLayer.state.RequestState
import com.example.baseArchitecture.domain.BaseUseCase
import com.example.imageloadingandinfinitescrolling.dataLayer.models.ImageDataModel
import com.example.imageloadingandinfinitescrolling.dataLayer.response.ImageListResponse
import com.example.imageloadingandinfinitescrolling.domain.repositories.MyBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageListUseCase @Inject constructor(val repository: MyBaseRepository) : BaseUseCase() {
    operator fun invoke(page : Int , limit : Int): Flow<RequestState<ArrayList<ImageDataModel>>> =
        getResultFlow {
            repository.getImagesList(page, limit)
        }
}