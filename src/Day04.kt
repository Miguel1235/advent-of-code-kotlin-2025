import kotlin.collections.getOrNull
import kotlin.collections.indices

private fun parseInput(input: List<String>): List<List<Char>> {
    return input.map { it.toList() }
}

private fun countNeighbors(grid: List<List<Char>>, row: Int, col: Int): Int {
    return Directions.entries.count { dir ->
        grid.getOrNull(row + dir.rowDelta)
            ?.getOrNull(col + dir.colDelta) == '@'
    }
}

private fun findCellsToDelete(grid: List<List<Char>>): List<Pair<Int, Int>> {
    return grid.flatMapIndexed { row, line ->
        line.mapIndexedNotNull { col, char ->
            if (char == '@' && countNeighbors(grid, row, col) < 4) {
                row to col
            } else null
        }
    }
}

private fun part1(grid: List<List<Char>>): Int {
    return grid.indices.sumOf { row ->
        grid[row].indices.count { col ->
            grid[row][col] == '@' && countNeighbors(grid, row, col) < 4
        }
    }
}

private fun part2(grid: List<List<Char>>): Int {
    val mutableGrid = grid.map { it.toMutableList() }
    var totalDeleted = 0

    repeat(50) {
        val cellsToDelete = findCellsToDelete(mutableGrid)
        totalDeleted += cellsToDelete.size
        cellsToDelete.forEach { (row, col) ->
            mutableGrid[row][col] = '.'
        }
    }
    return totalDeleted
}


fun main() {
    val testInput = parseInput(readInput("Day04_test"))
    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    val input = parseInput(readInput("Day04"))
    check(part1(input) == 1464)
    check(part2(input) == 8409)
}
 