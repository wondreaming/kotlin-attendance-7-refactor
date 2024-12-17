package attendance.model

import java.time.LocalDate
import java.time.LocalTime

data class Attendance(
    val day: LocalDate,
    val isHoliday: Boolean,
    var time: LocalTime? = null,
    var status: String? = "결석",
)