package com.example.baseArchitecture.presentation.ui.base

import com.example.baseArchitecture.dataLayer.state.RequestState

open class BaseUIState(
    open var isLoading: Boolean = false,
    open var error: RequestState.Error? = null,
    open var message: String? = null,
)