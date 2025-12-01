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


     fun countCrossings(start: Int, end: Int, amount: Int, direction: Char): Int {
        if (amount == 0) return 0

        val completeRotations = amount / 100
        val remainder = amount % 100

        val partialCrossing = when (direction) {
            'R' -> if (start + remainder >= 100) 1 else 0
            'L' -> if (start - remainder < 0) 1 else 0
            else -> 0
        }

        return completeRotations + partialCrossing
    }

    fun part2(rotations:  List<Pair<Char, Int>>): Int {
        var dial = 50

        return rotations.sumOf { (direction, amount) ->
            val newPosition = when (direction) {
                'R' -> (dial + amount).mod(100)
                else -> (dial - amount).mod(100)
            }

            val crossings = countCrossings(dial, newPosition, amount, direction)
            dial = newPosition
            crossings
        }
    }


    fun parseInput(input: List<String>): List<Pair<Char, Int>> {
        return input.map { Pair(it[0], it.takeLast(it.length -1).toInt()) }
    }

    val testInput = parseInput(readInput("Day01_test"))
    check(part1(testInput) == 3)
    part2(testInput).println()

    val input = parseInput(readInput("Day01"))
    check(part1(input) == 1040)
    part2(input).println() // more than 2583 -- less than 6038
}
