package com.itheamc.bouncingball.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.toTime(): String {
    return SimpleDateFormat.getTimeInstance().format(Date(this))
}

fun Long.toDate(): String {
    return SimpleDateFormat.getDateInstance().format(Date(this))
}