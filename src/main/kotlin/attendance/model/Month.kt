package attendance.model

enum class Month(
    val holiday: List<Int>
) {
    DECEMBER(listOf(1, 7, 8, 14, 15, 21, 22, 25, 28, 29))
}