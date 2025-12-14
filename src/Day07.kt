private fun part1(input: List<String>): Int {
//    println(input)
    val r = input.sumOf { line -> line.count { it == '^' } } - 1
    println(r)
    return 0
}

private fun part2(input: List<String>): Int {
    return 0
}

fun main() {
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)
     
    val input = readInput("Day07")
    check(part1(input) == 0) // less than 1807
//    check(part2(input) == 0)
}
 