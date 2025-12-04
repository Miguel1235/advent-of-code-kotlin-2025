import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)


fun <T> List<T>.combinations(k: Int): List<List<T>> {
    val results = mutableListOf<List<T>>()

    fun backtrack(start: Int, current: MutableList<T>) {
        if (current.size == k) {
            results.add(current.toList())
            return
        }
        for (i in start..this.lastIndex) {
            current.add(this[i])
            backtrack(i + 1, current)
            current.removeLast()
        }
    }
    backtrack(0, mutableListOf())
    return results
}

fun <T> List<T>.permutations(): List<List<T>> {
    if (size <= 1) return listOf(this)

    val result = mutableListOf<List<T>>()
    for (i in indices) {
        val element = this[i]
        val remaining = this.toMutableList().apply { removeAt(i) }
        val perms = remaining.permutations()
        result.addAll(perms.map { listOf(element) + it })
    }
    return result
}


enum class Directions(val rowDelta: Int, val colDelta: Int) {
    LEFT_UP(rowDelta = -1, colDelta = -1),
    UP(-1, 0),
    RIGHT_UP(-1, colDelta = 1),
    LEFT_DOWN(1, colDelta = -1),
    DOWN(1, 0),
    RIGHT_DOWN(1, colDelta = 1),
    LEFT(0, -1),
    RIGHT(0, 1)
}