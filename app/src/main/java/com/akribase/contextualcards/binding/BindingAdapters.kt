package com.akribase.contextualcards.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.widget.Button
import androidx.core.content.ContextCompat
import com.akribase.contextualcards.models.data.CTA


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
fun setCTA(view: Button, cta: CTA) {
    cta.url?.let { setDeepLink(view, it) }
    view.apply {
        backgroundTintList = ColorStateList.valueOf(Color.parseColor(cta.bgColor))
        setTextColor(Color.parseColor(cta.textColor))
        text = cta.text
    }
}