package attendance.controller.validator

class StepValidator {
    operator fun invoke(input: String) {
        checkEmpty(input)
        checkStep(input)
    }

    private fun checkEmpty(input: String) {
        require(input.isNotEmpty()) { StepErrorType.WRONG }
    }

    private fun checkStep(input: String) {
        require(input == "1" || input == "2" || input == "3" || input == "4" || input == "Q") { StepErrorType.WRONG }
    }
}