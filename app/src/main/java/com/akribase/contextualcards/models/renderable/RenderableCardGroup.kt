package com.akribase.contextualcards.models.renderable

import com.akribase.contextualcards.models.data.Card
import com.akribase.contextualcards.models.data.DesignType

data class RenderableCardGroup (
    val id: Int,
    val name: String,
    val designType: DesignType,
    val cards: List<RenderableCard>,
    val isScrollable: Boolean
)