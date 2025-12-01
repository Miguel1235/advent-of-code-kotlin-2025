fun main() {
    val part1 = { rotations: List<Pair<Char, Int>> ->
        rotations
            .runningFold(50) { dial, (dir, amount) ->
                when (dir) {
                    'R' -> (dial + amount) % 100
                    else -> (dial - amount).mod(100)
                }
            }
            .count { it == 0 }
    }

    val part2 = { rotations: List<Pair<Char, Int>> ->
        rotations
            .asSequence()
            .flatMap { (dir, amount) ->
                generateSequence(if (dir == 'R') 1 else -1) { it }
                    .take(amount)
            }
            .runningFold(50) { dial, step -> (dial + step).mod(100) }
            .count { it == 0 }
    }

    val parseInput = { input: List<String> ->
        input.map { Pair(it[0], it.takeLast(it.length - 1).toInt()) }
    }

    val testInput = parseInput(readInput("Day01_test"))
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    val input = parseInput(readInput("Day01"))
    check(part1(input) == 1040)
    check(part2(input) == 6027)
}
