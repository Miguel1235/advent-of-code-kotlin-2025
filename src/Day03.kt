private val part1 = { banks: List<String> -> banks.sumOf { getMaxBank(it) } }

private fun getMaxBank(bank: String): Int {
    var max = 0
    for(i in bank.indices) {
        for(j in i+1..<bank.length) {
            val bankNumber = "${bank[i]}${bank[j]}".toInt()
            if(bankNumber > max) max = bankNumber
        }
    }
    return max
}

private fun part2(banks: List<String>): Long {
    return banks.sumOf { bank ->
        bank.toList().combinations(12).maxOf { it.joinToString("").toLong() }
    }
}

fun main() {
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357)
    check(part2(testInput) == 3121910778619L)
     
    val input = readInput("Day03")
    check(part1(input) == 17229)
}
 