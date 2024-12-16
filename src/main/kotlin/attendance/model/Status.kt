package attendance.model

enum class Status(
    val status: String,
    val count: Int,
) {
    NORMAL("정상", 0),
    WARNING("경고", 2),
    INTERVIEW("면담", 3),
    WEEDING("제적", 5)
}