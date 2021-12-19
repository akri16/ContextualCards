package com.akribase.contextualcards.models.data

import com.akribase.contextualcards.R

enum class DesignType (val layout: Int) {
    HC3(R.layout.card_hc3),
    HC6(R.layout.card_hc6),
    HC5(R.layout.card_hc5),
    HC1(R.layout.card_hc1),
    HC9(R.layout.card_hc9);

    fun getTypeFromLayout(layout: Int) = values().find { it.layout == layout }
}