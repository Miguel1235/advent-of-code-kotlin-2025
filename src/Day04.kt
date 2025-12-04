private fun parseInput(input: List<String>): List<List<Char>> {
    return input.map { it.toList() }
}

private fun part1(grid: List<List<Char>>): Int {
    var total = 0
    for(rowI in grid.indices) {
        for(colI in grid[rowI].indices) {
            if(grid[rowI][colI] != '@') continue
            val sumResult = Directions.entries.sumOf { dir ->
                val r = grid.getOrNull(rowI + dir.rowDelta)?.getOrNull(colI + dir.colDelta) ?: '.'
                if(r == '@') 1 else 0
            }
            if(sumResult < 4) total++
        }
    }
    return total
}

private fun part2(input: List<String>): Int {
    return 0
}

fun main() {
    val testInput = parseInput(readInput("Day04_test"))
    check(part1(testInput) == 13)

//    val input = readInput("Day04")
//    check(part1(input) == 1464)
}
 