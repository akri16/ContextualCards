package com.akribase.contextualcards.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.constraintlayout.motion.widget.MotionLayout
import com.akribase.contextualcards.R
import android.util.DisplayMetrics




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

fun MotionLayout.transition() = run {
    if (currentState == R.id.start)transitionToEnd() else transitionToStart()
    true
}

/* no-op */
fun noop(){}

fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels
