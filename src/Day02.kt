fun String.isRepeatedTwice(): Boolean {
    if (this.length % 2 != 0) return false

    val half = this.length / 2
    return this.take(half) == this.substring(half)
}

private fun part1(input: List<LongRange>): Long {
    var count = 0L
    for(range in input) {
        for(i in range) {
            if(i.toString().isRepeatedTwice()) {
                count += i
            }
        }
    }
    return count
}

private fun part2(input: List<LongRange>): Int {
    return 0
}

private val parseInput = { input: List<String> ->
    input.first().split(",").map { range ->
        val (start, end) = range.split("-")
        start.toLong()..end.toLong()
    }
}

fun main() {
    val testInput = parseInput(readInput("Day02_test"))
    check(part1(testInput) == 1227775554L)
//    check(part2(testInput) == 0)
     
    val input = parseInput(readInput("Day02"))
    check(part1(input) == 40214376723)
//    check(part1(input) == 0)
//    check(part2(input) == 0)
}
 