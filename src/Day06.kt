import kotlin.collections.first
import kotlin.collections.takeLast

private fun part1(input: List<List<String>>): Long {
    return input.sumOf {
        val op = it.takeLast(1).first()
        val numbers = it.dropLast(1).map { it.toLong() }
        when(op) {
            "+" -> numbers.sum()
            else -> numbers.fold(1) { acc, number -> acc * number }
        }
    }
}

fun normalize(input: List<String>): List<String> {
    val rows = input.map { it.split(Regex("\\s+"), -1) }

    // Compute max width per column
    val colWidths = rows
        .flatten()
        .indices
        .groupBy { it }
        .map { (col, _) ->
            rows.maxOf { it.getOrNull(col)?.length ?: 0 }
        }

    // Pad each cell and replace padding with $
    return rows.map { row ->
        row.mapIndexed { i, cell ->
            val padded = cell.padStart(colWidths[i], ' ')
            padded.replace(' ', '$')
        }.joinToString(" ")
    }
}

private fun <T> List<List<T>>.transpose(): List<List<T>> =
    first().indices.map { i -> map { it[i] } }



private fun part2(input: List<String>): Int {
    val normal = normalize(input)
    println(normal)
//    val r = normal.map { it.split(" ") }.transpose()
//
//    r.sumOf {
//        val op = it.takeLast(1).first().filter { it != '$' }
//        val numbers = it.dropLast(1)
//
//        println(op)
//        println(numbers)
//        1
//    }

    return 0
}

private fun parseInput(input: List<String>): List<List<String>> {
    val r = input.map { it.split(" ").filter { it.isNotEmpty()} }
    return buildList {
        for(i in 0..<r.first().size) {
            val op = buildList {
                for(line in r) {
                    add(line[i])
                }
            }
            add(op)
        }
    }
}



fun main() {
    val testInput = readInput("Day06_test")
    check(part1(parseInput(testInput)) == 4277556L)
    check(part2(testInput) == 0)
     
    val input = parseInput(readInput("Day06"))
    check(part1(input) == 5782351442566L)
//    check(part2(input) == 0)
}
 