package attendance.model

import java.time.LocalDate
import java.time.LocalDateTime

data class Student(
    val name: String,
    val attendance: MutableMap<LocalDate, AttendanceRegister> = mutableMapOf(),
    var attendanceCount: Int = 0,
    var lateCount: Int = 0,
    var absenceCount: Int = 0,
    var status: Status = Status.NORMAL
) {
    fun addAttendance(time: LocalDateTime) {
        val attendanceRegister = AttendanceRegister(time)
        attendance.put(time.toLocalDate(), attendanceRegister)
        calculateAttendanceCount(attendanceRegister)
        status = calculateStatus()
    }

    private fun calculateAttendanceCount(attendanceRegister: AttendanceRegister) {
        when (attendanceRegister.attendanceCheck) {
            "출석" -> attendanceCount++
            "지각" -> lateCount++
            "결석" -> absenceCount++
        }
    }

    private fun calculateStatus(): Status {
        val addAbsenceCount = absenceCount + (lateCount / 3)
        return when {
            addAbsenceCount in 0 until 2 -> Status.NORMAL
            addAbsenceCount in 2 until 3 -> Status.WARNING
            addAbsenceCount in 3 until 5 -> Status.INTERVIEW
            else -> Status.WEEDING
        }
    }
}
