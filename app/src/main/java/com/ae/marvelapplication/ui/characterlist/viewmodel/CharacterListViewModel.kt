package com.ae.marvelapplication.ui.characterlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae.marvelappication.common.reponse.Resource
import com.ae.marvelapplication.common.reponse.ResponseHandler
import com.ae.marvelapplication.ui.characterlist.usecase.CharacterListUseCase
import com.ae.marvelapplication.dto.dto.ResultsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val useCase: CharacterListUseCase,
    private val responseHandler: ResponseHandler
) : ViewModel() {

    private var offset = INITIAL_VALUE
    private var limit = PAGE_SIZE
    private var isLastPage = false
    var isLoading = false
    private var page = 0

    private val _events = MutableLiveData<Resource<List<ResultsItem>>>()
    val getEvents: LiveData<Resource<List<ResultsItem>>> get() = _events

    fun getAllCharactersByPaging() {
        viewModelScope.launch {
            try {
                _events.value = responseHandler.handleSuccess(useCase.invoke(offset, limit))
            } catch (e: Exception) {
                _events.value = responseHandler.handleException(e)
            }
        }
    }

    fun onLoadMoreItems(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ) {
        if (isLoading || isLastPage || !isInFooter(
                visibleItemCount,
                firstVisibleItemPosition,
                totalItemCount
            )
        ) {
            return
        }
        page += 1
        offset = (page - 1) * PAGE_SIZE + 1
        getAllCharactersByPaging()
    }

    private fun isInFooter(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ): Boolean {
        return visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= INITIAL_VALUE
                && totalItemCount >= PAGE_SIZE
    }

    companion object {
        private const val PAGE_SIZE: Int = 10
        private const val MAX_SIZE: Int = 100
        private const val INITIAL_VALUE: Int = 0
    }

}