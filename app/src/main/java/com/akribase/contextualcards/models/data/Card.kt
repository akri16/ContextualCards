package com.akribase.contextualcards.models.data

data class Card(
    val name: String,
    val formattedTitle: String?,
    val title: String?,
    val formattedDescription: String?,
    val description: String?,
    val icon: CardImage?,
    val bgImage: CardImage?,
    val gradient: Gradient?,
    val url: String?,
    val bgColor: String?,
    val cta: List<CTA>
)
