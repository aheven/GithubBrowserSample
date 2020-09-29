package heven.holt.jetpack.github.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import heven.holt.jetpack.github.api.ApiEmptyResponse
import heven.holt.jetpack.github.api.ApiErrorResponse
import heven.holt.jetpack.github.api.ApiResponse
import heven.holt.jetpack.github.api.ApiSuccessResponse
import heven.holt.jetpack.github.vo.Resource

/**
 *Time:2020/9/28
 *Author:HevenHolt
 *Description:
 */
abstract class NetworkResource<RequestType> {
    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        result.value = Resource.loading(null)
        requestNetWork()
    }

    private fun requestNetWork() {
        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> setValue(processSuccessResponse(response))
                is ApiEmptyResponse -> setValue(Resource.success(null))
                is ApiErrorResponse -> {
                    onFetchFailed()
                    setValue(Resource.error(response.errorMessage, null))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<RequestType>>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    @WorkerThread
    protected open fun processSuccessResponse(response: ApiSuccessResponse<RequestType>) =
        Resource.success(response.body)
}