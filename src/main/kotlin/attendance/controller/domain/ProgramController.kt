package attendance.controller.domain

import attendance.controller.validator.AttendanceValidator
import attendance.controller.validator.EditDayValidator
import attendance.controller.validator.NickNameValidator
import attendance.controller.validator.StepValidator
import attendance.model.Student
import attendance.util.changeLocalDateTime
import camp.nextstep.edu.missionutils.DateTimes
import java.time.LocalDate
import java.time.LocalDateTime

class ProgramController(
    private val userInteractionController: UserInteractionController = UserInteractionController(),
    private val stepValidator: StepValidator = StepValidator(),
    private val nickNameValidator: NickNameValidator = NickNameValidator(),
    private val attendanceValidator: AttendanceValidator = AttendanceValidator(),
    private val editDayValidator: EditDayValidator = EditDayValidator(),
) {
    operator fun invoke(students: List<Student>) {
        var go = true
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
    }

    private fun getProgramStep(): String {
        val step = userInteractionController.handleStartMsg()
        stepValidator(step)
        return step
    }

    private fun processOne(students: List<Student>) {
        val nickName = getNickname(students)
        val time = getAttendanceTime()
        val student = students.find { it.name == nickName }
        student?.addAttendance(time)
        if (student != null) {
            userInteractionController.handleAttendTime(time, student)
        }
    }

    private fun processTwo(students: List<Student>) {
        val nickname = userInteractionController.handleEditNickName()
        val student = students.find { it.name == nickname }
        nickNameValidator(nickname, students)
        val day = userInteractionController.handleEditDay()
        editDayValidator(day, student!!)
        val time = userInteractionController.handleEditTime()
        attendanceValidator(time)

        val changeTime = changeLocalDateTime(day, time)
        val removeTime = student.attendanceRegister.attendanceList.find { it.day == changeTime.toLocalDate() }!!.time
        val status1 = student.attendanceRegister.attendanceList.find { it.day == changeTime.toLocalDate() }!!.status
        student.addAttendance(changeTime)
        val status2 = student.attendanceRegister.attendanceList.find { it.day == changeTime.toLocalDate() }!!.status
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

    private fun processThree(students: List<Student>) {
        val nickname = userInteractionController.handleNickName()
        val time = DateTimes.now().dayOfMonth - 1
        val date = LocalDate.of(2024, 12, time)
        nickNameValidator(nickname, students)
        val student = students.find { it.name == nickname }
        for (record in student!!.attendanceRegister.getAttendanceFrom1ToDate(date)) {
            if (!record.isHoliday){
                userInteractionController.handleAttendTime(record)
            }
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
}