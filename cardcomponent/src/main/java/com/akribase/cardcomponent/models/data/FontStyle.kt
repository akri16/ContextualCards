package com.akribase.cardcomponent.models.data

import com.google.gson.annotations.SerializedName

enum class FontStyle {
    @SerializedName("bold") BOLD,
    @SerializedName("italics") ITALICS,
    @SerializedName("underline") UNDERLINE
}