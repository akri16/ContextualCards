package com.akribase.contextualcards.models

data class CardImage(
    val imageType: ImageType,
    val assetType: String?,
    val image_url: String?
)