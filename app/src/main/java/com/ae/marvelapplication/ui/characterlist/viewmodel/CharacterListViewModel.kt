package com.ae.marvelapplication.ui.characterlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.dto.dto.ResultsItem
import com.ae.marvelapplication.ui.characterlist.usecase.CharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val useCase: CharacterListUseCase
) : ViewModel() {

    private var limit = PAGE_SIZE
    private var isLastPage = false
    private var page = 0
    var offset = INITIAL_VALUE
    var isLoading = false

    private val items = MutableLiveData<Resource<List<ResultsItem>>>()
    fun getItems(): LiveData<Resource<List<ResultsItem>>> = items

    init {
        getAllCharactersByPaging()
    }

    fun getAllCharactersByPaging() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                items.value = useCase.invoke(offset, limit)
            } catch (e: Exception) {
                items.value = Resource.Error(e)
            }
        }
    }

    fun onLoadMoreItems(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ) {
        if (isLoading ||
            isLastPage ||
            !isInFooter(visibleItemCount, firstVisibleItemPosition, totalItemCount)
        ) {
            return
        }
        setPagination()
        getAllCharactersByPaging()
    }

    private fun setPagination() {
        if (page == 0) {
            page = 1
        }
        page += 1
        offset = (page - 1) * PAGE_SIZE + 1
    }

    private fun isInFooter(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ): Boolean {
        return visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
            firstVisibleItemPosition >= INITIAL_VALUE &&
            totalItemCount >= PAGE_SIZE
    }

    companion object {
        private const val PAGE_SIZE: Int = 15
        private const val INITIAL_VALUE: Int = 0
    }
}