private fun part1(ingredients: Pair<List<LongRange>, List<Long>>): Int {
    val (ranges, ids) = ingredients
    return ids.count { id ->
        ranges.any { id in it }
    }
}

private fun part2(ingredients: Pair<List<LongRange>, List<Long>>): Long {
    val ranges = ingredients.first

    val validRanges = ranges.filter { range ->
        ingredients.second.any { it in range }
    }

    if (validRanges.isEmpty()) return 0

    val sorted = validRanges.sortedBy { it.first }
    var current = sorted.first()
    var count = 0L

    for (next in sorted.drop(1)) {
        if (next.first <= current.last + 1) {
            current = current.first..maxOf(current.last, next.last)
        } else {
            count += current.last - current.first + 1
            current = next
        }
    }

    count += current.last - current.first + 1
    return count
}



private fun parseInput(input: List<String>): Pair<List<LongRange>, List<Long>> {
    val ingredientRanges = mutableListOf<LongRange>()
    val ingredientIds = mutableListOf<Long>()

    for(r in input) {
        when {
            r.isEmpty() -> continue
            r.contains("-") -> {
                val (start, end) = r.split("-").map { it.toLong() }
                ingredientRanges.add(start..end)
            }
            else -> ingredientIds.add(r.toLong())
        }
    }
    return Pair(ingredientRanges, ingredientIds)
}

fun main() {
    val testInput = parseInput(readInput("Day05_test"))
    check(part1(testInput) == 3)
    check(part2(testInput) == 14L)

    val input = parseInput(readInput("Day05"))
    check(part1(input) == 868)
    check(part2(input) == 350669762943151L)
}
 