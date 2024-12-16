package attendance.model

import attendance.controller.Attendance
import attendance.util.getToday
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Student(
    val name: String,
    val attendance: MutableList<LocalDateTime> = mutableListOf<LocalDateTime>(),
    var attendanceCount: Int = 0,
    var lateCount: Int = 0,
    var absenceCount: Int = 0,
    var status: Status = Status.NORMAL
) {
    fun addAttendance(time: LocalDateTime) {
        attendance.add(time)
    }

    fun checkTodayAttendance(): Boolean {
        val today = LocalDate.now()
        if (attendance.find { today.dayOfMonth == it.dayOfMonth } != null) return true
        return false
    }

    fun checkAttendanceDay(day: String): Boolean {
        val days = mutableListOf<Int>()
        for (one in attendance) {
            val oneDay = one.dayOfMonth
            days.add(oneDay)
        }
        if (day.toInt() in days) return true
        return false
    }

    fun changeRecord(day: String, time: String): List<LocalDateTime> {
        val dateTimeString = "2024년 12월 ${day}일 %s:00".format(time)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val changeTime = LocalDateTime.parse(dateTimeString, formatter)

        val removeTime = attendance.find { it.dayOfMonth == changeTime.dayOfMonth }
        attendance.remove(removeTime)
        attendance.add(changeTime)
        val times = listOf<LocalDateTime>(removeTime!!, changeTime)
        return times
    }
}
