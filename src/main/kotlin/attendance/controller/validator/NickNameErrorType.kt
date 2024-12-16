package attendance.controller.validator

enum class NickNameErrorType(
    private val errorMessage: String
) {
    WRONG("잘못된 형식을 입력하였습니다."),
    NO_STUDENT("등록되지 않은 닉네임입니다.");

    override fun toString(): String = "$ERROR $errorMessage"

    companion object {
        private const val ERROR = "[ERROR]"
    }
}