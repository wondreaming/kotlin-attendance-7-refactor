package attendance.controller.adapter

import attendance.model.Student
import attendance.util.getLocalDateTime
import java.io.File


class StudentAdapter {
    fun loadStudent(): List<Student> {
        val filePath = "src/main/resources/attendances.csv"
        val students = mutableListOf<Student>()

        File(filePath).useLines { lines ->
            lines.drop(1).map { it.split(",") }.forEach { (name, datetime) ->
                val student = students.find { it.name == name }
                    ?: Student(name).also { students.add(it) }
                student.addAttendance(getLocalDateTime(datetime))
            }
        }
        return students
    }
}