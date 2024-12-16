package attendance.util

import attendance.model.Student

fun getStudent(students: List<Student>, nickname: String): Student? {
    return students.find { it.name == nickname }
}