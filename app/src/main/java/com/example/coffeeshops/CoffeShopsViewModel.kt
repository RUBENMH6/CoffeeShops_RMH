package com.example.coffeeshops

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CoffeeShopsViewModel : ViewModel() {
    var selectedEstablishment by mutableStateOf<String?>(null)

    fun selectEstablishment(cardInfo: CardInfo) {
        selectedEstablishment = cardInfo.name
    }
}