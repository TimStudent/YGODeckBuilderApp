package com.example.ygodeckbuilder.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
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
    //var chosenDeckList = mutableStateListOf<CardDomain>()
    var deckList1 = mutableStateListOf<CardDomain>()
    private var deckList2 = mutableStateListOf<CardDomain>()
    private var deckList3 = mutableStateListOf<CardDomain>()
    private var sortedCards = mutableListOf<LiveData<UIState>>()

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

    fun sortByCardName() {
//        listOf(cards).sortedWith()

    }
    fun filterByCardName() {
//        listOf(cards).filter {  }
    }
    fun sortByCardTypeMonster(type: List<CardType>) {

    }
    fun filterByCardTypeMonster() {

    }
    fun sortByCardTypeSpell() {

    }
    fun filterByCardTypeSpell() {

    }
    fun sortByCardTypeTrap() {

    }
    fun filterByCardTypeTrap() {

    }
    fun sortByNormalMonster(list: MutableList<CardDomain>): MutableList<CardDomain> {
        val tempList2 = mutableListOf<CardDomain>()
        for(card in list) {
            if(card.type != CardType.NORMAL_MONSTER.typeName) {
                tempList2.add(card)
            }
        }
        for(card in tempList2) {
            if(card.type != CardType.NORMAL_MONSTER.typeName) {
                list.remove(card)
                list.add(card)
            }
        }
        return list
    }
    fun filterByNormalMonster(list: MutableList<CardDomain>): MutableList<CardDomain> {
        val tempList2 = mutableListOf<CardDomain>()

        for(card in list) {
            if(card.type != CardType.NORMAL_MONSTER.typeName) {
                tempList2.add(card)
            }
        }
        for(card in tempList2) {
            if(card.type != CardType.NORMAL_MONSTER.typeName) {
                list.remove(card)
            }
        }
        return list
    }
    fun sortByEffectMonster() {

    }
    fun filterByEffectMonster() {

    }
    fun sortByRitual() {

    }
    fun filterByRitual() {

    }
//    fun sortByFusion(cards: LiveData<UIState>) : LiveData<UIState> {
//        Log.d(TAG, cards.value.toString())
//        for (i in cards) {
//
//        }
//    }
    fun filterByFusion() {

    }
    fun sortBySynchro() {

    }
    fun filterBySynchro() {

    }
    fun sortByXYZ() {

    }
    fun filterByXYZ() {

    }
    fun sortByPendulum() {

    }
    fun filterByPendulum() {

    }
    fun sortByLink() {

    }
    fun filterByLink() {

    }
}