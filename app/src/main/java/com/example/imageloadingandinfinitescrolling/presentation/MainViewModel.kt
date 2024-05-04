package com.example.imageloadingandinfinitescrolling.presentation

import com.example.baseArchitecture.dataLayer.IBaseResponse
import com.example.baseArchitecture.dataLayer.state.RequestState
import com.example.baseArchitecture.presentation.ui.base.BaseViewModel
import com.example.baseArchitecture.uitls.BaseTaskCode
import com.example.imageloadingandinfinitescrolling.dataLayer.models.ImageDataModel
import com.example.imageloadingandinfinitescrolling.dataLayer.response.ImageListResponse
import com.example.imageloadingandinfinitescrolling.domain.APIConstants.LIST_LIMIT
import com.example.imageloadingandinfinitescrolling.domain.TaskCode
import com.example.imageloadingandinfinitescrolling.domain.usecases.ImageListUseCase
import com.example.imageloadingandinfinitescrolling.presentation.states.ImageListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val imageListUseCase : ImageListUseCase) : BaseViewModel() {
    private val _uiState = MutableStateFlow(ImageListState(isLoading = true))
    val uiState: StateFlow<ImageListState> = _uiState.asStateFlow()
 var page = 1
     fun getImageList(){
         makeApiRequest(taskCode = TaskCode.GET_IMAGE_LIST){
             imageListUseCase.invoke(page =page, limit =  LIST_LIMIT)}
     }

    override fun onSuccess(taskCode: BaseTaskCode, response: Any) {
        super.onSuccess(taskCode, response)
        when(taskCode){
            TaskCode.GET_IMAGE_LIST ->{
                _uiState.update {
                    it.copy(
                        isLoading = false, data = (response as ArrayList<ImageDataModel>)
                    )

                }
                page++
            }

        }
    }

    override fun onFailure(taskCode: BaseTaskCode, exception: RequestState.Error) {
        super.onFailure(taskCode, exception)
    }

    override fun onLoading(taskCode: BaseTaskCode) {
        super.onLoading(taskCode)
    }
}