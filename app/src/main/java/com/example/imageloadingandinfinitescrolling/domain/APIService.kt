package com.example.imageloadingandinfinitescrolling.domain

import com.example.imageloadingandinfinitescrolling.domain.APIConstants.LIST_LIMIT
import retrofit2.http.GET

interface APIService {
    @GET(APIConstants.GET_IMAGES_LIST)
    suspend fun getImageList( page : Int, limit : Int = LIST_LIMIT )
}