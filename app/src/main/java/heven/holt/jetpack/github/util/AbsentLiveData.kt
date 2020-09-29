package heven.holt.jetpack.github.util

import androidx.lifecycle.LiveData

/**
 *Time:2020/9/27
 *Author:HevenHolt
 *Description:
 */
class AbsentLiveData<T> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> = AbsentLiveData()
    }
}