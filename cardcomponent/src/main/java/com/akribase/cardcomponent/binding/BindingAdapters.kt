package com.akribase.cardcomponent.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.text.Editable
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.akribase.cardcomponent.models.data.CTA
import com.akribase.cardcomponent.models.renderable.RenderableBG
import com.akribase.cardcomponent.utils.SimpleTextWatcher
import com.bumptech.glide.Glide


@BindingAdapter(value = ["deeplink"])
fun setDeepLink(view: View, url: String?) {
    if (url == null) return
    view.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(it.context, intent, null)
    }
}

@BindingAdapter(value = ["cta"])
fun setCTA(view: Button, cta: CTA?) {
    cta?.let {
        it.url?.let { setDeepLink(view, it) }
        view.apply {
            backgroundTintList = ColorStateList.valueOf(Color.parseColor(it.bgColor))
            setTextColor(Color.parseColor(it.textColor))
            text = it.text
        }
    }
}

@BindingAdapter(value = ["bg"])
fun setDrawable(view: ImageView, src: RenderableBG) {
    src.img?.let {
        view.setImageDrawable(it)
        return
    }
    setImage(view, src.url)
}

@BindingAdapter(value = ["android:src"])
fun setImage(view: ImageView, src: String?) {
    src?.let{ Glide.with(view).load(src).into(view) }
}


@BindingAdapter(value = ["goneIfEmpty"])
fun setGoneIfEmpty(view: TextView, isGoneIfEmpty: Boolean) {
    if (isGoneIfEmpty) {
        view.visibility = if (view.text.isNullOrBlank()) View.GONE else View.VISIBLE
        view.addTextChangedListener(object: SimpleTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                view.visibility = if (p0.isNullOrBlank()) View.GONE else View.VISIBLE
            }
        })
    }
}

@BindingAdapter(value = ["clickable"])
fun setIsClickable(view: TextView, isClickable: Boolean) {
    if (isClickable) {
        view.movementMethod = LinkMovementMethod.getInstance()
    }
}