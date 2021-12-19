package com.akribase.contextualcards.models.data

data class Card(
    val name: String,
    val formattedTitle: FormattedText? = null,
    val title: String? = null,
    val formattedDescription: FormattedText? = null,
    val description: String? = null,
    val icon: CardImage? = null,
    val bgImage: CardImage? = null,
    val gradient: Gradient? = null,
    val url: String? = null,
    val bgColor: String? = null,
    val cta: List<CTA>? = null
)
