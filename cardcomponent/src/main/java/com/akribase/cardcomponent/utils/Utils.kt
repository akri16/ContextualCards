package com.akribase.cardcomponent.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.constraintlayout.motion.widget.MotionLayout
import com.akribase.cardcomponent.R


fun Context.dpToPx(dp: Int) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics
)

val ViewGroup.inflater
    get() = LayoutInflater.from(context)

fun ViewGroup.inflate(@LayoutRes res: Int) =
    inflater.inflate(res, this, false)

fun String.parseColor() = try {
    Color.parseColor(this.trim())
} catch (e: IllegalArgumentException) {
    Log.e("PARSE COLOR", this, e)
    Color.TRANSPARENT
}

fun MotionLayout.transition() = run {
    if (currentState == R.id.start) transitionToEnd() else transitionToStart()
    true
}

/* no-op */
fun noop() {}

fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels

fun <T> checkEqual(obj1: T, obj2: T, vararg att: T.() -> Any): Boolean {
    att.forEach {
        if (obj1.it() != obj2.it()) {
            return false
        }
    }
    return true
}
