package com.akribase.contextualcards.models.data

data class CardGroup(
    val id: Int,
    val name: String,
    val designType: DesignType,
    val cards: List<Card>,
    val isScrollable: Boolean,
    val height: Int? = null
)
