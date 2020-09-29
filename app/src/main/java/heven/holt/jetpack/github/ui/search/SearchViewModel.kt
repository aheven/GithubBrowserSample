package heven.holt.jetpack.github.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import heven.holt.jetpack.github.api.RepoSearchResponse
import heven.holt.jetpack.github.repository.RepoRepository
import heven.holt.jetpack.github.vo.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

/**
 *Time:2020/9/25
 *Author:HevenHolt
 *Description:
 */
class SearchViewModel @ViewModelInject constructor(
    private val repoRepository: RepoRepository
) : ViewModel() {

    private val _query = MutableLiveData<String>()

    val query: LiveData<String> = _query

    private val _results: MutableLiveData<Resource<RepoSearchResponse>> = MutableLiveData()

    val results: LiveData<Resource<RepoSearchResponse>>
        get() = _results

//    val results: LiveData<Resource<RepoSearchResponse>> = _query.switchMap { search ->
//        if (search.isEmpty()) {
//            AbsentLiveData.create()
//        } else {
//            repoRepository.search(search)
//        }
//    }

    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == _query.value || input.isEmpty()) return
        _query.value = originalInput
        launchDataLoad({
            repoRepository.search(originalInput)
        }, _results)
    }

    private fun <T> launchDataLoad(
        block: suspend () -> T,
        _result: MutableLiveData<Resource<T>>
    ): Job {
        return viewModelScope.launch {
            try {
                _result.value = Resource.loading(null)
                val data = block()
                _result.value = Resource.success(data)
            } catch (exception: Exception) {
                _result.value = Resource.error(exception.message!!, null)
            }
        }
    }
}