package com.example.alexlindroos.readit.util

import android.content.Context
import android.text.format.DateUtils
import java.util.*

/**
 * Created by Alex Lindroos on 14/03/2018.
 */

fun Date.format(context: Context): String {
    val flags = DateUtils.FORMAT_SHOW_YEAR
    return DateUtils.formatDateTime(context, time, flags)
}