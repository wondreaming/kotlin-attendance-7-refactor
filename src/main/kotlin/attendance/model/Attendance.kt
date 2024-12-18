package attendance.model

import java.time.LocalDate
import java.time.LocalTime

data class Attendance(
    val day: LocalDate,
    val isHoliday: Boolean,
    var time: LocalTime? = null,
    var record: AttendanceRecord = AttendanceRecord.ABSENCE,
) {
    fun updateTime(newTime: LocalTime?) {
        time = newTime
        record = calculateAttendanceRecord()
    }

    private fun calculateAttendanceRecord(): AttendanceRecord {
        return if (day.dayOfWeek == SchoolTime.MONDAY.dayOfWeek) {
            when {
                time!!.isBefore(SchoolTime.MONDAY.attendanceTime) -> AttendanceRecord.ATTENDANCE
                time!!.isAfter(SchoolTime.MONDAY.absenceTime) -> AttendanceRecord.ABSENCE
                else -> AttendanceRecord.TARDINESS
            }
        } else {
            when {
                time!!.isBefore(SchoolTime.TUESDAY.attendanceTime) -> AttendanceRecord.ATTENDANCE
                time!!.isAfter(SchoolTime.TUESDAY.absenceTime) -> AttendanceRecord.ABSENCE
                else -> AttendanceRecord.TARDINESS
            }
        }
    }
}