package attendance.model

import java.time.LocalDate
import java.time.LocalTime

class AttendanceRegister {
    val attendanceList = listOf(
        Attendance(LocalDate.of(2024, 12, 1), true, time = null),
        Attendance(LocalDate.of(2024, 12, 2), false),
        Attendance(LocalDate.of(2024, 12, 3), false),
        Attendance(LocalDate.of(2024, 12, 4), false),
        Attendance(LocalDate.of(2024, 12, 5), false),
        Attendance(LocalDate.of(2024, 12, 6), false),
        Attendance(LocalDate.of(2024, 12, 7), true, time = null),
        Attendance(LocalDate.of(2024, 12, 8), true, time = null),
        Attendance(LocalDate.of(2024, 12, 9), false),
        Attendance(LocalDate.of(2024, 12, 10), false),
        Attendance(LocalDate.of(2024, 12, 11), false),
        Attendance(LocalDate.of(2024, 12, 12), false),
        Attendance(LocalDate.of(2024, 12, 13), false),
        Attendance(LocalDate.of(2024, 12, 14), true, time = null),
        Attendance(LocalDate.of(2024, 12, 15), true, time = null),
        Attendance(LocalDate.of(2024, 12, 16), false),
        Attendance(LocalDate.of(2024, 12, 17), false),
        Attendance(LocalDate.of(2024, 12, 18), false),
        Attendance(LocalDate.of(2024, 12, 19), false),
        Attendance(LocalDate.of(2024, 12, 20), false),
        Attendance(LocalDate.of(2024, 12, 21), true, time = null),
        Attendance(LocalDate.of(2024, 12, 22), true, time = null),
        Attendance(LocalDate.of(2024, 12, 23), false),
        Attendance(LocalDate.of(2024, 12, 24), false),
        Attendance(LocalDate.of(2024, 12, 25), true, time = null),
        Attendance(LocalDate.of(2024, 12, 26), false),
        Attendance(LocalDate.of(2024, 12, 27), false),
        Attendance(LocalDate.of(2024, 12, 28), true, time = null),
        Attendance(LocalDate.of(2024, 12, 29), true, time = null),
        Attendance(LocalDate.of(2024, 12, 30), false),
        Attendance(LocalDate.of(2024, 12, 31), false),
    )

    private fun findAttendanceByDate(date: LocalDate): Attendance? {
        return attendanceList.find { it.day == date }
    }

    fun updateAttendance(day: LocalDate, time: LocalTime) {
        val attendanceRecord = findAttendanceByDate(day)
        attendanceRecord?.updateTime(time)
    }

    fun getAttendanceFrom1ToDate(date: LocalDate): List<Attendance> {
        return attendanceList.filter { it.day.dayOfMonth in 1..date.dayOfMonth }
    }

    fun isHoliday(date: LocalDate): Boolean {
        return attendanceList.find { it.day.dayOfMonth == date.dayOfMonth }?.isHoliday ?: false
    }
}