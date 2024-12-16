package attendance.controller.validator

import attendance.util.changeLocalDateTime
import attendance.util.getDay
import attendance.util.getLocalDateTime
import java.time.LocalDateTime

class AttendanceValidator {
    operator fun invoke(input: String) {
        checkEmpty(input)
        val (hour, min) = input.split(":")
        checkHour(hour)
        checkSchoolTime(hour)
        checkToday()
    }

    private fun checkEmpty(input: String) {
        require(input.isNotEmpty()) { AttendanceTimeErrorType.WRONG }
    }

    private fun checkHour(input: String) {
        require(input.toInt() in 0 until 24) { AttendanceTimeErrorType.NO_SCHOOL_TIME }
    }

    private fun checkSchoolTime(input: String) {
        require(input.toInt() in 8..23) { AttendanceTimeErrorType.NO_SCHOOL_TIME }
    }

    private fun checkToday() {
        val today = getDay()
        println(today)
        val holiday = listOf<Int>(1, 7, 8, 14, 15, 21, 22, 25, 28, 29)
        require(today !in holiday) { AttendanceTimeErrorType.CHECK_ATTENDANCE }
    }
}
