package attendance.controller.adapter

import attendance.model.Student
import attendance.util.getLocalDateTime
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
                nicknames.add(nickname)
                val student = Student(nickname)
                students.add(student)
            }
            val student = students.find { it.name == nickname }
            student!!.addAttendance(getLocalDateTime(datetime))
        }
        return students
    }
}