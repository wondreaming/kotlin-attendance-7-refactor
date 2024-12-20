package attendance.model

import attendance.controller.Attendance
import java.time.DayOfWeek
import java.time.LocalTime

enum class SchoolTime(
    val dayOfWeek: DayOfWeek,
    val attendanceTime: LocalTime,
    val tardinessTime: LocalTime,
    val absenceTime: LocalTime,
    val endTime: LocalTime,
) {
    MONDAY(DayOfWeek.MONDAY, LocalTime.of(13, 1), LocalTime.of(13, 6), LocalTime.of(13, 30), LocalTime.of(18, 0)),
    TUESDAY(DayOfWeek.TUESDAY, LocalTime.of(10, 1), LocalTime.of(10, 6), LocalTime.of(10, 30), LocalTime.of(18, 0));
}