package attendance.util

fun <T> tryWhenNoException(action: () -> T) {
    try {

    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}