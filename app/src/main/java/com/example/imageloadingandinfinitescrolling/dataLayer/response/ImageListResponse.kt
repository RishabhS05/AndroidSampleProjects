package com.example.imageloadingandinfinitescrolling.dataLayer.response

import com.example.baseArchitecture.dataLayer.BaseResponse
import com.example.imageloadingandinfinitescrolling.dataLayer.models.ImageDataModel

data class ImageListResponse(val list : ArrayList<ImageDataModel>) : BaseResponse()