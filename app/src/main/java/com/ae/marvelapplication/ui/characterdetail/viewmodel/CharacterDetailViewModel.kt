package com.ae.marvelapplication.ui.characterdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ae.marvelapplication.data.response.Resource
import com.ae.marvelapplication.dto.dto.ResultsItem
import com.ae.marvelapplication.ui.characterdetail.usercase.CharacterDetailUserCase
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
        liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
            try {
                val response = useCase.invoke(characterId)
                emit(response)
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
}