package attendance.controller

import attendance.controller.adapter.StudentAdapter
import attendance.controller.domain.UserInteractionController
import attendance.controller.validator.*
import attendance.model.Student
import attendance.util.changeLocalDateTime
import attendance.util.getDay
import camp.nextstep.edu.missionutils.DateTimes
import java.time.LocalDate
import java.time.LocalDateTime

class Attendance(
    private val userInteractionController: UserInteractionController = UserInteractionController(),
    private val stepValidator: StepValidator = StepValidator(),
    private val studentAdapter: StudentAdapter = StudentAdapter(),
    private val nickNameValidator: NickNameValidator = NickNameValidator(),
    private val attendanceValidator: AttendanceValidator = AttendanceValidator(),
    private val editDayValidator: EditDayValidator = EditDayValidator(),
) {
    fun run() {

        val students = studentAdapter.loadStudent()
        var go = true
        checkToday()
        try {
            while (go) {
                val step = getProgramStep()
                when (step) {
                    "1" -> processOne(students)
                    "2" -> processTwo(students)
                    "3" -> processThree(students)
                    "4" -> processFour(students)
                    "Q" -> go = false
                }
            }
        } catch (e: IllegalArgumentException) {
            println(e.message)
            throw IllegalArgumentException(e.message)
        }


    }

    private fun getProgramStep(): String {
        val step = userInteractionController.handleStartMsg()
        stepValidator(step)
        return step
    }

    private fun processOne(students: List<Student>) {
        val nickName = getNickname(students)
        val attendanceTime = getAttendanceTime()
        val student = students.find { it.name == nickName }
        student!!.addAttendance(attendanceTime)
        val status = checkStatus(attendanceTime)
        userInteractionController.handleAttendTime(attendanceTime, status)
    }

    private fun processTwo(students: List<Student>) {
        val nickname = userInteractionController.handleEditNickName()
        val student = students.find { it.name == nickname }
        nickNameValidator(nickname, students)
        val day = userInteractionController.handleEditDay()
        editDayValidator(day, student!!)
        val time = userInteractionController.handleEditTime()
        attendanceValidator(time)
        val (removeTime, changeTime) = student.changeRecord(day, time)
        val status1 = "지각"
        val status2 = "출석"
        userInteractionController.handleEditAttendance(removeTime, status1, changeTime, status2)
    }

    private fun getNickname(students: List<Student>): String {
        val nickname = userInteractionController.handleNickName()
        nickNameValidator(nickname, students)
//        nickNameValidator.checkAttendance(nickname, students)
        return nickname
    }

    private fun getAttendanceTime(): LocalDateTime {
        val attendanceTime = userInteractionController.handleAttendanceTime()
        attendanceValidator(attendanceTime)
        val studentAttendanceTime = changeLocalDateTime(attendanceTime)
        return studentAttendanceTime
    }

    private fun checkStatus(attendanceTime: LocalDateTime): String {
        return "출석"
    }

    private fun processThree(students: List<Student>) {
        val nickname = userInteractionController.handleNickName()
        nickNameValidator(nickname, students)
        val student = students.find { it.name == nickname }
        for (record in student!!.attendance) {
            val status = checkStatus(record)
            userInteractionController.handleAttendTime(record, status)
        }
        userInteractionController.handleStatus(student)
    }

    private fun processFour(students: List<Student>) {
        val dangerStudents = mutableListOf<Student>()
        for (student in students) {
            if (student.status.status != "정상") dangerStudents.add(student)
        }
        userInteractionController.handleDangerPeople(dangerStudents)
    }

    private fun checkToday() {
        val today = DateTimes.now().dayOfMonth
        val holiday = listOf<Int>(1, 7, 8, 14, 15, 21, 22, 25, 28, 29)
        require(today !in holiday) { AttendanceTimeErrorType.CHECK_ATTENDANCE }
    }
}