package attendance.controller.validator

import attendance.model.Student

class NickNameValidator {
    operator fun invoke(input: String, students: List<Student>) {
        checkEmpty(input)
        checkStudents(input, students)
    }

    private fun checkEmpty(input: String) {
        require(input.isNotEmpty()) { NickNameErrorType.WRONG }
    }

    private fun checkStudents(input: String, students: List<Student>) {
        requireNotNull(students.find { it.name == input }) { NickNameErrorType.NO_STUDENT }
    }

//    fun checkAttendance(input: String, students: List<Student>) {
//        val student = students.find { it.name == input }
//        println(student)
//        require(student!!.checkTodayAttendance()) { NickNameErrorType.WRONG }
//    }
}