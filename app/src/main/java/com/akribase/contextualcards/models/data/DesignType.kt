package com.akribase.contextualcards.models.data

import android.view.ViewGroup
import com.akribase.contextualcards.R

enum class DesignType (val layout: Int, val height: Int) {
    HC3(R.layout.card_hc3, 300),
    HC6(R.layout.card_hc6, 100),
    HC5(R.layout.card_hc5, ViewGroup.LayoutParams.WRAP_CONTENT),
    HC1(R.layout.card_hc1, 60),
    HC9(R.layout.card_hc9, ViewGroup.LayoutParams.WRAP_CONTENT);

    companion object {
        fun getTypeFromLayout(layout: Int) = values().find { it.layout == layout } ?: HC3
    }
}