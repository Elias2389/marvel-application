package com.ae.marvelapplication.ui.characterdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ae.marvelappication.common.reponse.Resource
import com.ae.marvelapplication.common.reponse.ResponseHandler
import com.ae.marvelapplication.dto.dto.ResultsItem
import com.ae.marvelapplication.ui.characterdetail.usercase.CharacterDetailUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val useCase: CharacterDetailUserCase,
    private val responseHandler: ResponseHandler
) : ViewModel() {

    fun getCharacterById(
        characterId: Int
    ): LiveData<Resource<List<ResultsItem>>> =
        liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
            try {
                val response = useCase.invoke(characterId)
                emit(responseHandler.handleSuccess(response))
            } catch (e: Exception) {
                emit(responseHandler.handleException<List<ResultsItem>>(e))
            }
        }
}