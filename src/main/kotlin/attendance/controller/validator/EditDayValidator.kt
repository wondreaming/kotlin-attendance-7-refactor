package attendance.controller.validator

import attendance.model.Student
import attendance.util.getDay

class EditDayValidator {
    operator fun invoke(input: String, student: Student) {
        checkEmpty(input)
        checkInteger(input)
        checkBetween(input)
        checkHoliday(input)
        checkFuture(input)
        checkAttendanceDay(input, student)
    }

    private fun checkEmpty(input: String) {
        require(input.isNotEmpty()) { EditDayErrorType.WRONG }
    }

    private fun checkInteger(input: String) {
        requireNotNull(input.toIntOrNull()) { EditDayErrorType.NO_INTEGER }
    }

    private fun checkBetween(input: String) {
        val day = input.toInt()
        require(day in 1..31) { EditDayErrorType.BETWEEN }
    }

    private fun checkHoliday(input: String) {
        val day = input.toInt()
        val holiday = listOf<Int>(1, 7, 8, 14, 15, 21, 22, 25, 28, 29)
        require(day !in holiday) { EditDayErrorType.HOLIDAY }
    }

    private fun checkFuture(input: String) {
        val today = getDay()
        require(input.toInt() < today) { EditDayErrorType.NOW }
    }

    private fun checkAttendanceDay(day: String, student: Student) {
//        require(student.checkAttendanceDay(day)) { EditDayErrorType.WRONG }
    }

}