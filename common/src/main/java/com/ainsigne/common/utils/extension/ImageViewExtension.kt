package com.ainsigne.common.utils.extension

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.ainsigne.common.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.toRoundImage(
    url: String?,
    @DrawableRes placeholder: Int = R.drawable.image_profile_empty,
    @DrawableRes error: Int = R.drawable.image_profile_empty,
) {
    val requestOptions = RequestOptions()
        .circleCrop()
        .placeholder(ContextCompat.getDrawable(this.context, placeholder))
        .error(ContextCompat.getDrawable(this.context, error))

    Glide.with(this.context)
        .load(url)
        .apply(requestOptions)
        .into(this)
}

fun ImageView.enableControl(@ColorRes disabled: Int, @ColorRes enabled: Int, isEnabled: Boolean) {
    this.setColorFilter(
        ContextCompat.getColor(this.context, if (isEnabled) enabled else disabled),
        android.graphics.PorterDuff.Mode.SRC_IN
    )
    this.setEnable(isEnabled)
}

fun ImageView.loadHex(
    hex: Int
) {
    this.setBackgroundColor(hex)
}

fun ImageView.loadUrl(
    url: String?,
    @DrawableRes placeholder: Int = R.drawable.image_profile_empty,
    @DrawableRes error: Int = R.drawable.image_profile_empty,
    @DimenRes radius: Int? = null
) {
    val requestOptions = RequestOptions()
        .placeholder(ContextCompat.getDrawable(this.context, placeholder))
        .error(ContextCompat.getDrawable(this.context, error))
    radius?.let {
        requestOptions.apply(
            RequestOptions.bitmapTransform(
                RoundedCorners(
                    this.context.resources.getDimension(radius).toInt()
                )
            )
        )
    }
    Glide.with(this.context)
        .load(url)
        .apply(requestOptions)
        .into(this)
}