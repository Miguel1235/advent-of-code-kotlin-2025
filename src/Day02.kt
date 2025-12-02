fun String.isRepeatedTwice(): Boolean {
    if (this.length % 2 != 0) return false

    val half = this.length / 2
    return this.take(half) == this.substring(half)
}

fun String.isInvalid(): Boolean {
    val doubled = this + this
    return doubled.drop(1).dropLast(1).contains(this)
}

private fun part1(input: List<LongRange>, isPart1: Boolean = true): Long {
    var firstPart = 0L
    var secondPart = 0L
    for(range in input) {
        for(i in range) {
            if(i.toString().isRepeatedTwice()) {
                firstPart += i
            }
            if(i.toString().isInvalid()) {
                secondPart += i
            }
        }
    }
    return if(isPart1) firstPart else secondPart
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
    check(part1(testInput, false) == 4174379265)

    val input = parseInput(readInput("Day02"))
    check(part1(input) == 40214376723)
    check(part1(input, false) == 50793864718)
}
 