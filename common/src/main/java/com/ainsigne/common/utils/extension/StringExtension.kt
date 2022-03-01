package com.ainsigne.common.utils.extension

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.BulletSpan
import android.widget.TextView
import java.util.Locale
import java.util.StringTokenizer


fun Int.imageResUri(): String = "res:///$this"

fun Float.formatBill(): String = String.format("₱%.2f", this)

fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Pair<Int, Int>> {
    val list = mutableListOf<Pair<Int, Int>>()
    if (this == null || substr.isBlank()) return list

    var i = -1
    var subEnd = -1
    while (true) {
        i = indexOf(substr, i + 1, ignoreCase)
        subEnd = i + substr.length
        when (i) {
            -1 -> return list
            else -> list.add(Pair(i, subEnd))
        }
    }
}

fun Long.getDisplayedBill(): String {
    return String.format("₱%,.2f", this / 100f)
}

fun String.maskRange(start: Int, end: Int, replaceWith: String): String {
    val replaceString: String = replaceWith.repeat(end - start)

    if (length > 0) {
        return replaceRange(start, end, replaceString)
    }

    return this
}

fun String.getDecimalFormattedString(): String? {
    val lst = StringTokenizer(this, ".")
    var amountInputted = this
    var amountHolder = ""
    if (lst.countTokens() > 1) {
        amountInputted = lst.nextToken()
        amountHolder = lst.nextToken()
    }
    var amountSeparator = StringBuilder()
    var i = 0
    var j = -1 + amountInputted.length
    if (amountInputted[-1 + amountInputted.length] == '.') {
        j--
        amountSeparator = StringBuilder(".")
    }
    var k = j
    while (true) {
        if (k < 0) {
            if (amountHolder.isNotEmpty()) amountSeparator.append(".").append(amountHolder)
            return amountSeparator.toString()
        }
        if (i == 3) {
            amountSeparator.insert(0, ",")
            i = 0
        }
        amountSeparator.insert(0, amountInputted[k])
        i++
        k--
    }
}

fun List<String>.addCommaToString(): String {
    val ids: String = this.toString().replace("[", "").replace("]", "")
    return ids.replace(" ", "")
}

fun String.trimCommaOfString(): String {
    return if (this.contains(",")) {
        this.replace(",", "")
    } else {
        this
    }
}

fun String.trimDecimalOfString(): String? {
    return if (this.contains(".")) {
        this.replace(".", "")
    } else {
        this
    }
}

fun List<String>.bulleted(): CharSequence {
    var currentBulleted: CharSequence = ""
    for (publisher in this) {
        val spannable = SpannableString(publisher)
        spannable.setSpan(BulletSpan(15), 0, publisher.length, 0)
        currentBulleted = TextUtils.concat(spannable, currentBulleted)
    }
    return currentBulleted
}

const val THROW_LIST_EXCEPTION = "Not a valid Array!"
const val THROW_STRING_EXCEPTION = "Parameter must not be empty!"
const val EMPTY = ""
const val DEFAULT_ZERO = "0"
