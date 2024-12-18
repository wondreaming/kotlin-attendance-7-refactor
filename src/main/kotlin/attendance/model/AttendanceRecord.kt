package attendance.model

enum class AttendanceRecord(
    val record: String,
) {
    ATTENDANCE("출석"),
    TARDINESS("지각"),
    ABSENCE("결석");
}