fun String.isRepeatedTwice(): Boolean {
    if (this.length % 2 != 0) return false
    val half = this.length / 2
    return this.take(half) == this.substring(half)
}

fun String.isInvalid(): Boolean {
    val doubled = this + this
    return doubled.drop(1).dropLast(1).contains(this)
}

private val part = { input: List<LongRange>, isPart1: Boolean ->
    input
        .asSequence()
        .flatMap { it.asSequence() }
        .filter { if (isPart1) it.toString().isRepeatedTwice() else it.toString().isInvalid() }
        .sum()
}


private val parseInput = { input: List<String> ->
    input.first().split(",").map { range ->
        val (start, end) = range.split("-")
        start.toLong()..end.toLong()
    }
}

fun main() {
    val testInput = parseInput(readInput("Day02_test"))
    check(part(testInput, true) == 1227775554L)
    check(part(testInput, false) == 4174379265)

    val input = parseInput(readInput("Day02"))
    check(part(input, true) == 40214376723)
    check(part(input, false) == 50793864718)
}
 