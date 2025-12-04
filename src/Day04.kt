import kotlin.collections.getOrNull
import kotlin.collections.indices

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

private fun part2(grid: List<List<Char>>): Int {
    val newGrid = grid.toMutableList().map { it.toMutableList() }
    var rolls2Delete = 0

    repeat(50) {
        val items2Delete = mutableListOf<Pair<Int, Int>>()
        for(rowI in newGrid.indices) {
            for(colI in newGrid[rowI].indices) {
                if(newGrid[rowI][colI] != '@') continue
                val sumResult = Directions.entries.sumOf { dir ->
                    val r = newGrid.getOrNull(rowI + dir.rowDelta)?.getOrNull(colI + dir.colDelta) ?: '.'
                    if(r == '@') 1 else 0
                }
                if(sumResult < 4) {
                    items2Delete.add(rowI to colI)
                }
            }
        }
        rolls2Delete += items2Delete.size
        items2Delete.forEach {
            newGrid[it.first][it.second] = '.'
        }
    }
    return rolls2Delete
}

fun main() {
    val testInput = parseInput(readInput("Day04_test"))
    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    val input = parseInput(readInput("Day04"))
    check(part1(input) == 1464)
    check(part2(input) == 8409)
}
 