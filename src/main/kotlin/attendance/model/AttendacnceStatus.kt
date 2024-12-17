package attendance.model

import camp.nextstep.edu.missionutils.DateTimes
import java.time.LocalDate
import java.time.LocalDateTime

data class AttendanceStatus(
    val time: LocalDateTime,
    var attendanceCheck: String = "",
) {
    init {
        attendanceCheck = calculateAttendanceCheck()
    }

    private fun calculateAttendanceCheck(): String {
        return if (time.dayOfWeek == SchoolTime.MONDAY.dayOfWeek) {
            when {
                time.toLocalTime().isBefore(SchoolTime.MONDAY.attendanceTime) -> "출석"
                time.toLocalTime().isAfter(SchoolTime.MONDAY.absenceTime) -> "결석"
                else -> "지각"
            }
        } else {
            when {
                time.toLocalTime().isBefore(SchoolTime.TUESDAY.attendanceTime) -> "출석"
                time.toLocalTime().isAfter(SchoolTime.TUESDAY.absenceTime) -> "결석"
                else -> "지각"
            }
        }
    }
}