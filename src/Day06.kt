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
fun transposeDigits(numbers: List<Long>): List<Long> {
    // Sort by number of digits (descending)
    val sorted = numbers.sortedByDescending { it.toString().length }

    // Convert each number to a string
    val numberStrings = sorted.map { it.toString() }

    // Find the maximum length
    val maxLength = numberStrings.maxOfOrNull { it.length } ?: 0

    val result = mutableListOf<Long>()

    // Iterate through each digit position from right to left
    for (i in 0 until maxLength) {
        val digits = StringBuilder()

        // For each number, get the digit at position i from the right
        for (numStr in numberStrings) {
            val positionFromRight = numStr.length - 1 - i
            if (positionFromRight >= 0) {
                digits.append(numStr[positionFromRight])
            }
        }

        result.add(digits.toString().toLong())
    }

    return result
}


private fun part2(input: List<List<String>>): Int {
    val r = input.sumOf {
        val op = it.takeLast(1).first()
        val nums = it.dropLast(1).map { it.toLong() }
//        println(nums)
        val numbers = transposeDigits(nums)
        println(numbers)
        val r: Long = when(op) {
            "+" -> numbers.sum()
            else -> numbers.fold(1) { acc, number -> acc * number }
        }
//        println(r)
        r
    }
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
    val testInput = parseInput(readInput("Day06_test"))
    check(part1(testInput) == 4277556L)
    check(part2(testInput) == 0)
     
    val input = parseInput(readInput("Day06"))
    check(part1(input) == 5782351442566L)
//    check(part2(input) == 0)
}
 