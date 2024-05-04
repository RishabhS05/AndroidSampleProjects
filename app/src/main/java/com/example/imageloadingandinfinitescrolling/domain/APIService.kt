package com.example.imageloadingandinfinitescrolling.domain
import com.example.imageloadingandinfinitescrolling.dataLayer.models.ImageDataModel
import com.example.imageloadingandinfinitescrolling.dataLayer.response.ImageListResponse
import com.example.imageloadingandinfinitescrolling.domain.APIConstants.LIST_LIMIT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET(APIConstants.GET_IMAGES_LIST)
    suspend fun getImageList(
        @Query("page")
        page : Int, @Query("limit")  limit : Int = LIST_LIMIT ) : Response<ArrayList<ImageDataModel>>
}