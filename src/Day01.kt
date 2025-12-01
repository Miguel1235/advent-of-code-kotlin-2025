import kotlin.math.abs

fun main() {
    fun part1(rotations:  List<Pair<Char, Int>>): Int {
        var dial = 50

        return rotations.count { (dir, amount) ->
            dial = when (dir) {
                'R' -> (dial + amount) % 100
                else -> (dial - amount).mod(100)
            }
            dial == 0
        }
    }

    fun part2(rotations:  List<Pair<Char, Int>>): Int {
        var dial = 50

        return rotations.sumOf { (dir, amount) ->
            var loops = 0
            repeat(amount) {
                dial = when (dir) {
                    'R' -> (dial + 1) % 100
                    else -> (dial - 1).mod(100)
                }
                if(dial == 0) loops++
            }
            loops
        }
    }


    fun parseInput(input: List<String>): List<Pair<Char, Int>> {
        return input.map { Pair(it[0], it.takeLast(it.length -1).toInt()) }
    }

    val testInput = parseInput(readInput("Day01_test"))
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    val input = parseInput(readInput("Day01"))
    check(part1(input) == 1040)
    check(part2(input) == 6027)
}
