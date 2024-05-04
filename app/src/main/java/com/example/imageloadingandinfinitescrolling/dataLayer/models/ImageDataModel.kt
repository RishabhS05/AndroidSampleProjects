package com.example.imageloadingandinfinitescrolling.dataLayer.models

import com.example.imageloadingandinfinitescrolling.BuildConfig
import com.google.gson.annotations.SerializedName

//{
//        "id": "10",
//        "author": "Paul Jarvis",
//        "width": 2500,
//        "height": 1667,
//        "url": "https://unsplash.com/photos/6J--NXulQCs",
//        "download_url": "https://picsum.photos/id/10/2500/1667"
//    }
data class ImageDataModel(val id : String,
                          val author : String?,
                          val height :Int,
                          val width : String?,
                           val url: String? ,
                      @SerializedName("download_url") val downloadUrl : String?,
                          val baseUrl : String  = BuildConfig.BASE_URL
)
