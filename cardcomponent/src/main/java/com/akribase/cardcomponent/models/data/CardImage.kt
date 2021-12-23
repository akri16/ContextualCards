package com.akribase.cardcomponent.models.data

import com.akribase.cardcomponent.models.data.ImageType

data class CardImage(
    val imageType: ImageType,
    val assetType: String?,
    val image_url: String?,
    val aspectRatio: Float?
)