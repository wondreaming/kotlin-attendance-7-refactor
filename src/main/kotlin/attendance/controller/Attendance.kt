package attendance.controller

import attendance.controller.adapter.StudentAdapter
import attendance.controller.domain.ProgramController
import attendance.controller.validator.*
import attendance.model.Month
import attendance.model.SchoolTime
import camp.nextstep.edu.missionutils.DateTimes

class Attendance(
    private val studentAdapter: StudentAdapter = StudentAdapter(),
    private val programController: ProgramController = ProgramController()
) {
    fun run() {
        val students = studentAdapter.loadStudent()
        println(students.joinToString("\n"))
        checkToday()
        try {
            programController(students)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(e.message)
        }
    }

    private fun checkToday() {
        val today = DateTimes.now().dayOfMonth
        require(today !in Month.DECEMBER.holiday) { AttendanceTimeErrorType.CHECK_ATTENDANCE }
    }
}