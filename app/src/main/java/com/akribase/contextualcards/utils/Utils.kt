package com.akribase.contextualcards.utils

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun Context.dpToPx(dp: Int) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics
)

val ViewGroup.inflater
    get() = LayoutInflater.from(context)

fun ViewGroup.inflate(@LayoutRes res:Int) =
    inflater.inflate(res, this, false)

fun String.parseColor() = Color.parseColor(this)