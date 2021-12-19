package com.akribase.contextualcards.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.akribase.contextualcards.models.data.CTA
import com.akribase.contextualcards.models.renderable.RenderableBG
import com.akribase.contextualcards.utils.dpToPx
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


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

@BindingAdapter(value = ["bg"])
fun setDrawable(view: ImageView, src: RenderableBG) {
    src.img?.let {
        view.setImageDrawable(it)
        return
    }

    src.let{
        if (it.width != null && it.height != null){
            Glide.with(view)
                .load(it.url)
                .apply(RequestOptions()
                    .override(
                        view.context.dpToPx(it.width).toInt(),
                        view.context.dpToPx(it.height).toInt()
                    ))
                .into(view)
        } else {
            setImage(view, it.url)
        }
    }
}

@BindingAdapter(value = ["android:src"])
fun setImage(view: ImageView, src: String?) {
    src?.let{ Glide.with(view).load(src).into(view) }
}