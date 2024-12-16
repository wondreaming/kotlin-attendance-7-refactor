package attendance.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getToday(): LocalDate {
    val today = LocalDate.now()
    return today
}

fun getDay(): Int {
    val localDate = LocalDate.of(2024, 12, 13)
    val startOfDay = localDate.atStartOfDay()
    val today = startOfDay
    val day: Int = today.dayOfMonth
    return day
}

fun getLocalDateTime(time: String): LocalDateTime {
    val dateTimeString = "%s:00".format(time)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.parse(dateTimeString, formatter)
}

fun changeLocalDateTime(time: String): LocalDateTime {
    val localDate = LocalDate.of(2024, 12, 13)
    val startOfDay = localDate.atStartOfDay()
    val today = startOfDay
    val dateTimeString = "${localDate} %s:00".format(time)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.parse(dateTimeString, formatter)
}

fun LocalDateTime.standardFormatter(): String {
    return this.format(DateTimeFormatter.ofPattern("M월 d일 E요일 HH:mm"))
}
