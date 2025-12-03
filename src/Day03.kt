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

private fun part2(input: List<String>): Int {
    println(input)
    return 0
}

fun main() {
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357)
//    check(part2(testInput) == 0)
     
    val input = readInput("Day03")
    check(part1(input) == 17229)
//    check(part2(input) == 0)
}
 