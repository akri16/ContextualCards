package com.akribase.contextualcards.models.renderable

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.Spannable
import com.akribase.contextualcards.models.data.CTA
import com.akribase.contextualcards.models.data.Card

data class RenderableCard(
    val name: String,
    val title: Spannable,
    val desp: Spannable,
    val icon: Drawable,
    val bg: Drawable,
    val cta: CTA?,
    val url: String?
) {
    
}
