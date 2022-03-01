package com.ainsigne.common.utils.extension

import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE

fun View.gone() {
    this.visibility = GONE
}

fun View.visible() {
    this.visibility = VISIBLE
}

fun View.invisible() {
    this.visibility = INVISIBLE
}

fun View.setVisible(visible: Boolean, resize: Boolean) {
    this.visibility = if (visible) VISIBLE else if (resize) GONE else INVISIBLE
}

fun View.setEnable(enable: Boolean) {
    this.isEnabled = enable
}

fun Array<View>.invisibleViews() {
    this.map {
        it.invisible()
    }
}
