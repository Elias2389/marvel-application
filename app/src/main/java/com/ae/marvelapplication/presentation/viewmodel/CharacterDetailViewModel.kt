package com.ae.marvelapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ae.domain.model.Resource
import com.ae.domain.model.ResultsItem
import com.ae.usecase.CharacterDetailUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val useCase: CharacterDetailUserCase
) : ViewModel() {

    fun getCharacterById(
        characterId: Int
    ): LiveData<Resource<List<ResultsItem>>> =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            try {
                val response = useCase.invoke(characterId)
                emit(response)
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
}