package com.akribase.contextualcards.models.data

import com.google.gson.annotations.SerializedName

enum class ImageType {
    @SerializedName("asset") ASSET,
    @SerializedName("external") EXTERNAL
}