package attendance.model

import camp.nextstep.edu.missionutils.DateTimes
import java.time.LocalDate
import java.time.LocalDateTime

data class Student(
    val name: String,
    val attendanceRegister: AttendanceRegister = AttendanceRegister(),
    var attendanceCount: Int = 0,
    var lateCount: Int = 0,
    var absenceCount: Int = 0,
    var status: Status = Status.NORMAL
) {
    fun addAttendance(time: LocalDateTime) {
        val attendanceDay = time.toLocalDate()
        val attendanceTime = time.toLocalTime()
        attendanceRegister.updateAttendance(attendanceDay, attendanceTime)
        calculateAttendanceCount()
        calculateStatus()
    }

    private fun calculateAttendanceCount() {
        attendanceCount = 0
        lateCount = 0
        absenceCount = 0
        val today = DateTimes.now().dayOfMonth - 1
        val date = LocalDate.of(2024, 12, today)

        val attendances = attendanceRegister.getAttendanceFrom1ToDate(date)
        for (attendance in attendances) {
            if (!attendance.isHoliday) {
                when (attendance.record) {
                    AttendanceRecord.ATTENDANCE -> attendanceCount++
                    AttendanceRecord.TARDINESS -> lateCount++
                    AttendanceRecord.ABSENCE -> absenceCount++
                }
            }
        }
    }

    private fun calculateStatus() {
        val addAbsenceCount = absenceCount + (lateCount / 3)
        status = when (addAbsenceCount) {
            in 0 until 2 -> Status.NORMAL
            in 2 until 3 -> Status.WARNING
            in 3 until 5 -> Status.INTERVIEW
            else -> Status.WEEDING
        }
    }
}
