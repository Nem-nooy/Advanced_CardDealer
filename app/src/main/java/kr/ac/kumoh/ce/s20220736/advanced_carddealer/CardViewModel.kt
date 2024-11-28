package kr.ac.kumoh.ce.s20220736.advanced_carddealer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// CardViewModel.kt
class CardViewModel : ViewModel() {
    private val cardModel = CardModel()
    private val _cards = MutableLiveData<List<String>>()
    val cards: LiveData<List<String>>
        get() = _cards

    private val _handRank = MutableLiveData<String>()
    val handRank: LiveData<String>
        get() = _handRank

    init {
        val initialCards = listOf(
            "c_10_of_spades",
            "c_jack_of_spades2",
            "c_queen_of_spades2",
            "c_king_of_spades2",
            "c_ace_of_spades"
        )
        _cards.value = initialCards
        evaluateHandRank(listOf(9, 10, 11, 12, 0)) // 초기 카드 번호 리스트
    }

    fun shuffle(count: Int = CardModel.NUMBER_OF_CARDS) {
        val newCards = cardModel.dealCards()
        val sortedCards = newCards.sorted()
        val cardNames = sortedCards.map { getCardName(it) }
        _cards.value = cardNames
        evaluateHandRank(sortedCards)
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

    private fun evaluateHandRank(cards: List<Int>) {
        // 족보 판별 로직
        val numbers = cards.map { it % 13 }
        val suits = cards.map { it / 13 }

        val numberCounts = numbers.groupingBy { it }.eachCount()
        val suitCounts = suits.groupingBy { it }.eachCount()

        val isFlush = suitCounts.size == 1
        val sortedNumbers = numbers.sorted()
        val isStraight = sortedNumbers.zipWithNext().all { (a, b) -> b == a + 1 } ||
                (sortedNumbers == listOf(0, 9, 10, 11, 12)) // A,10,J,Q,K

        val rank = when {
            isFlush && isStraight && sortedNumbers.contains(12) -> "로열 스트레이트 플러시"
            isFlush && isStraight -> "스트레이트 플러시"
            4 in numberCounts.values -> "포카드"
            numberCounts.values.containsAll(listOf(3, 2)) -> "풀하우스"
            isFlush -> "플러시"
            isStraight -> "스트레이트"
            3 in numberCounts.values -> "쓰리카드"
            numberCounts.values.count { it == 2 } == 2 -> "투페어"
            2 in numberCounts.values -> "원페어"
            else -> "하이카드"
        }

        _handRank.value = rank
    }
}
