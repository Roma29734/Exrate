

package com.example.exrate.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun getDecryptedWeek(week: String): String {
//        MONDAY TUESDAY WEDNESDAY THURSDAY FRIDAY SATURDAY SUNDAY
    return when (week) {
        "MONDAY" -> "Понедельник"
        "TUESDAY" -> "Вторник"
        "WEDNESDAY" -> "Среда"
        "THURSDAY" -> "Четверг"
        "FRIDAY" -> "Пятница"
        "SATURDAY" -> "Суббота"
        "SUNDAY" -> "Воскресенье"
        else -> "НИЧЕГО"
    }
}

fun getDate(): LocalDate {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val data = dateFormat.format(Date())

    val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    return LocalDate.parse(data, firstApiFormat)
}