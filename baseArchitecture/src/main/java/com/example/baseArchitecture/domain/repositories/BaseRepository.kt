package com.example.baseArchitecture.domain.repositories


import com.example.baseArchitecture.dataLayer.state.RequestState
import com.example.baseArchitecture.uitls.exceptionHandling.BaseError

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


open class BaseRepository {
    fun <T> getResultFlow(
        apiCall: suspend () -> Response<T>?
    ): Flow<RequestState<T>> = flow {
        emit(RequestState.Loading)
        try {
            val response = apiCall()
            response?.let { rawResponse ->
                when (rawResponse.code()) {
                    200 -> emit(RequestState.Success(data = rawResponse.body()!!))
                    in 400..504 ->
                        rawResponse.errorBody()?.let {
                            val error = it.string()
                            it.close()
                                emit(
                                    RequestState.Error(
                                        Gson().fromJson(error,BaseError::class.java)
                                    )
                                )
                        }
                    else -> {
                        emit(RequestState.Error(Exception("Something went wrong !!")))
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(RequestState.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}