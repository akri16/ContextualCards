package com.akribase.contextualcards.models.renderable

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.recyclerview.widget.DiffUtil
import com.akribase.contextualcards.models.data.*
import com.akribase.contextualcards.utils.parseColor

data class RenderableCard(
    val name: String,
    val title: CharSequence,
    val desp: CharSequence,
    val icon: String?,
    val bg: RenderableBG,
    val cta: CTA?,
    val url: String?
) {
    companion object {
        val DIFFCALLBACK = object: DiffUtil.ItemCallback<RenderableCard>() {
            override fun areItemsTheSame(
                oldItem: RenderableCard,
                newItem: RenderableCard
            ) = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: RenderableCard,
                newItem: RenderableCard
            ) = oldItem == newItem
        }

        private fun formatSpans(formattedText: FormattedText?, fallbackText: String): CharSequence {
            if (formattedText == null) return fallbackText

            val text = " ${formattedText.text} "
            val splitStrings = text.split("{}")
            val spannableBuilder = SpannableStringBuilder()
            var spanStart = -1

            splitStrings.forEachIndexed {i, s ->
                if (spanStart == -1) {
                    spanStart = 0
                } else {
                    val entity = formattedText.entities[i-1]
                    spannableBuilder.append(entity.text)
                    val start = spanStart
                    val end = spanStart + entity.text.length

                    entity.color?.let {
                        spannableBuilder.setSpan(
                            ForegroundColorSpan(Color.parseColor(it)),
                            start,
                            end,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }?: entity.fontStyle?.let {
                        val span = when(it) {
                            FontStyle.UNDERLINE -> UnderlineSpan()
                            FontStyle.ITALICS -> StyleSpan(Typeface.ITALIC)
                            FontStyle.BOLD -> StyleSpan(Typeface.BOLD)
                        }

                        spannableBuilder.setSpan(
                            span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }?: entity.url?.let {
                        spannableBuilder.setSpan(
                            URLSpan(it), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                    spanStart += entity.text.length
                }
                spannableBuilder.append(s)

                spanStart += s.length
            }

            return spannableBuilder.trim()
        }

        private fun getDrawableBG(bgColor: String?, gradient: Gradient?): Drawable {
            return when {
                bgColor != null -> {
                    ColorDrawable(bgColor.parseColor())
                }
                gradient != null -> {
                    val orientation = when (gradient.angle) {
                        0 or 360 -> GradientDrawable.Orientation.RIGHT_LEFT
                        45 -> GradientDrawable.Orientation.TR_BL
                        90 -> GradientDrawable.Orientation.TOP_BOTTOM
                        135 -> GradientDrawable.Orientation.TL_BR
                        180 -> GradientDrawable.Orientation.LEFT_RIGHT
                        225 -> GradientDrawable.Orientation.BL_TR
                        270 -> GradientDrawable.Orientation.BOTTOM_TOP
                        315 -> GradientDrawable.Orientation.BR_TL
                        else -> GradientDrawable.Orientation.RIGHT_LEFT
                    }
                    GradientDrawable(orientation, gradient.colors.map { it.parseColor() }.toIntArray())
                }
                else -> {
                    ColorDrawable(Color.WHITE)
                }
            }
        }

        fun createFromCard(card: Card): RenderableCard {
            val title = formatSpans(card.formattedTitle, card.title ?: "")
            val desp = formatSpans(card.formattedDescription, card.description ?: "")

            val bg = if (card.bgImage?.image_url != null) {
                with(card.bgImage) {
                    RenderableBG(url = image_url, aspectRatio = aspectRatio)
                }
            } else {
                RenderableBG(
                    img = getDrawableBG(card.bgColor, card.gradient)
                )
            }

            return with(card) {
                RenderableCard(name, title, desp, icon?.image_url, bg, cta?.get(0), url)
            }
        }
    }


}
