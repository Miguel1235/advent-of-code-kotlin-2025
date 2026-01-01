import kotlin.math.abs

data class Tile(var r: Long, var c: Long)

private val parseInput = { input: List<String> ->
    input.map {
        val (r, c) = it.split(",")
        Tile(r.toLong(), c.toLong())
    }
}

private fun part1(input: List<Tile>): Long {
    val rectangles = input.combinations(2)

    return rectangles.maxOf {
        val (r1, c1) = it.first()
        val (r2, c2) = it.last()
        val base = abs(r1 - r2) + 1
        val height = abs(c1 - c2) + 1
        base * height
    }
}

fun main() {
    val testInput = parseInput(readInput("Day09_test"))
    check(part1(testInput) == 50L)

    val input = parseInput(readInput("Day09"))
    check(part1(input) == 4761736832L)
}
 