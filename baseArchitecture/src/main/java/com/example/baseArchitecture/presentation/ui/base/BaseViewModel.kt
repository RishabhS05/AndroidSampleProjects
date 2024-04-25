package com.example.baseArchitecture.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseArchitecture.dataLayer.IBaseResponse
import com.example.baseArchitecture.dataLayer.state.RequestState
import com.example.baseArchitecture.uitls.BaseTaskCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
     abstract class BaseViewModel : ViewModel() {
         open fun onSuccess(taskCode: BaseTaskCode, response: IBaseResponse) {}
         open fun onFailure(taskCode: BaseTaskCode, exception: RequestState.Error) {}

         open fun onLoading(taskCode: BaseTaskCode) {}
//         var _errorAndMessageSharedFlow = MutableSharedFlow<ErrorAndMessage>()
//         var errorAndMessageSharedFlow: SharedFlow<ErrorAndMessage> =
//             _errorAndMessageSharedFlow.asSharedFlow()
//
//         fun setErrorMessage(message: ErrorAndMessage) {
//             viewModelScope.launch {
//                 _errorAndMessageSharedFlow.emit(message)
//             }
//         }

         fun <T> makeApiRequest(
             taskCode: BaseTaskCode,
             isLoading: Boolean = true,
             call: suspend () -> Flow<RequestState<T>>,
         ) {
             viewModelScope.launch {
                 call().collect {
                     when (it) {
                         is RequestState.Success -> onSuccess(taskCode, it.data as IBaseResponse)
                         is RequestState.Error -> onFailure(taskCode, it)
                         is RequestState.Loading -> if (isLoading) onLoading(taskCode)
                     }
                 }
             }
         }
     }
}