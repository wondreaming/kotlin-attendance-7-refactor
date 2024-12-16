package attendance.controller.validator

enum class AttendanceTimeErrorType (
    private val errorMessage: String
) {
    WRONG("잘못된 형식을 입력하였습니다."),
    NO_SCHOOL_TIME("잘못된 형식을 입력하였습니다."),
    CHECK_ATTENDANCE("12월 14일 토요일은 등교일이 아닙니다.");

    override fun toString(): String = "$ERROR $errorMessage"

    companion object {
        private const val ERROR = "[ERROR]"
    }
}
