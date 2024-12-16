package attendance.controller.validator

enum class StepErrorType(
    private val errorMessage: String
) {
    WRONG("잘못된 형식을 입력하였습니다.");

    override fun toString(): String = "$ERROR $errorMessage"

    companion object {
        private const val ERROR = "[ERROR]"
    }
}