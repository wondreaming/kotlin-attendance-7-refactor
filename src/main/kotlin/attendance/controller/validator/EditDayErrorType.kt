package attendance.controller.validator

enum class EditDayErrorType(
    private val errorMessage: String
) {
    WRONG("잘못된 형식을 입력하였습니다."),
    NO_INTEGER("숫자가 아닙니다."),
    BETWEEN("1에서 31일 사이에 있어야합니다."),
    HOLIDAY("12월 14일 토요일은 등교일이 아닙니다."),
    NOW("아직 수정할 수 없습니다.");

    override fun toString(): String = "$ERROR $errorMessage"

    companion object {
        private const val ERROR = "[ERROR]"
    }
}