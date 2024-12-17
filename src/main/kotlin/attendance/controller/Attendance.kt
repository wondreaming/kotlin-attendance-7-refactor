package attendance.controller

import attendance.controller.adapter.StudentAdapter
import attendance.controller.domain.ProgramController
import attendance.controller.validator.*
import attendance.model.AttendanceRegister
import camp.nextstep.edu.missionutils.DateTimes

class Attendance(
    private val studentAdapter: StudentAdapter = StudentAdapter(),
    private val programController: ProgramController = ProgramController(),
    private val attendanceRegister: AttendanceRegister = AttendanceRegister(),
) {
    fun run() {
        val students = studentAdapter.loadStudent()
        try {
            checkToday()
            programController(students)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(e.message)
        }
    }

    private fun checkToday() {
        val today = DateTimes.now().toLocalDate()
        require(!attendanceRegister.isHoliday(today)) { AttendanceTimeErrorType.CHECK_ATTENDANCE }
    }
}