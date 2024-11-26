package kr.ac.kumoh.ce.s20220736.advanced_carddealer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.ac.kumoh.ce.s20220736.advanced_carddealer.CardModel.Companion.NUMBER_OF_CARDS
import kotlin.random.Random

class CardViewModel : ViewModel() {
//    companion object {
//        const val NUMBER_OF_CARDS = 5
//    }

    private val cardModel = CardModel()     // 말씀드리는건, 이렇게 만드는 건 좋은 방법이 아니에요.

    private val _cards = MutableLiveData<List<String>>()
    val cards: LiveData<List<String>>
        get() = _cards

    init {
        val newCards = listOf(
            "c_10_of_spades",
            "c_jack_of_spades2",
            "c_queen_of_spades2",
            "c_king_of_spades2",
            "c_ace_of_spades"
        )
        _cards.value = newCards
    }

    fun shuffle(count: Int = CardModel.NUMBER_OF_CARDS) {
        val newCards = cardModel.dealCards()

        _cards.value = newCards.sorted().map { getCardName(it) }
    }

    private fun getCardName(c: Int): String {
        val shape = when (c / 13) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }

        val number = when (c % 13) {
            0 -> "ace"
            in 1..9 -> (c % 13 + 1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> "error"
        }

        return if (c % 13 in 10..12)
            "c_${number}_of_${shape}2"
        else
            "c_${number}_of_${shape}"
    }
}