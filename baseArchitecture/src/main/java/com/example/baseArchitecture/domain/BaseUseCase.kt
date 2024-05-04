package com.example.baseArchitecture.domain

import com.example.baseArchitecture.dataLayer.state.RequestState
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import timber.log.Timber

abstract class BaseUseCase {
    fun <T> getResultFlow(
        apiCall: suspend () -> Response<T>?
    ): Flow<RequestState<T>> = flow {
        var response: Response<T>? = null
        emit(RequestState.Loading)
        try {
            response = apiCall()
            Timber.d("Response: $response" )
          if(response?.isSuccessful == true){
              response.body()
          }
            response?.let { rawResponse ->
                when (rawResponse.code()) {
                    200 -> emit(RequestState.Success(data = rawResponse.body()!!))
                    in 400..504 ->
                        rawResponse.errorBody()?.let {
                            val error = it.string()
                            it.close()
                            emit(RequestState.Error(Exception(error)))
                        }
                    else -> {
                        emit(RequestState.Error(Exception("Something went wrong !!")))
                    }
                }
            }

        }
        catch (e: Exception) {

            e.printStackTrace()
            emit(RequestState.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}