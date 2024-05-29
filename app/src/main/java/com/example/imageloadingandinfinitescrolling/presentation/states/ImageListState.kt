package com.example.imageloadingandinfinitescrolling.presentation.states

import com.example.baseArchitecture.presentation.ui.base.BaseUIState
import com.example.imageloadingandinfinitescrolling.dataLayer.models.ImageDataModel

data class ImageListState(override var isLoading: Boolean,
                          val data: ArrayList<ImageDataModel> = arrayListOf(),
                          val loadNextPage: Boolean = false
) : BaseUIState() {
}