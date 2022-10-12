package com.example.ygodeckbuilder.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ygodeckbuilder.data.domain.CardDomain
import com.example.ygodeckbuilder.rest.YGODeckBuilderRepository
import com.example.ygodeckbuilder.utils.CardType
import com.example.ygodeckbuilder.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardViewModel(
    private val repository: YGODeckBuilderRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    init {
        getAllCards()
    }

    private val _cards : MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val cards: LiveData<UIState> get() = _cards

    fun getAllCards() {
        Log.d(TAG, "made it to the start of get all cards function")
        viewModelScope.launch(ioDispatcher) {
            repository.getAllCards().collect {
                withContext(Dispatchers.Main) {
                    _cards.postValue(it)
                }
            }
        }
        Log.d(TAG, "made it to the end of get all cards function")
    }



    fun getSkillCards() {
        viewModelScope.launch(ioDispatcher) {
            repository.getCardsByType(cardType = CardType.SKILL_CARD).collect {
                withContext(Dispatchers.Main) {

                }
                _cards.postValue(it)
            }
        }
    }

    fun getSpellCards(){
        viewModelScope.launch(ioDispatcher) {
            repository.getCardsByType(cardType = CardType.SPELL_CARD).collect {
                withContext(Dispatchers.Main) {

                }
                _cards.postValue(it)
            }
        }
    }

    fun getCardByName(cardName: String) {
        viewModelScope.launch {
            repository.getCardsByName(cardName).collect {
                _cards.postValue(it)
                withContext(Dispatchers.Main) {

                }
                _cards.postValue(it)
            }
        }
    }
}