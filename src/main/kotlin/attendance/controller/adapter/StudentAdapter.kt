package attendance.controller.adapter

import attendance.model.Student
import attendance.util.getLocalDateTime
import attendance.util.getStudent
import java.io.File
import java.nio.file.Files

class StudentAdapter {
    fun loadStudent(): List<Student> {
        val filePath = "src/main/resources/attendances.csv"
        val nicknames = mutableListOf<String>()
        val students = mutableListOf<Student>()
        val records = File(filePath).readLines().filterNot { it.startsWith("nickname") }
        for (record in records) {
            val (nickname, datetime) = record.split(",")
            if (nickname !in nicknames) {
                val student = Student(nickname)
                nicknames.add(nickname)
                students.add(student)
            }
            val student = getStudent(students, nickname)
            student?.addAttendance(getLocalDateTime(datetime))
        }
        return students
    }
}