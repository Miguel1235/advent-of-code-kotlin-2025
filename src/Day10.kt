data class Machine(val goal: List<Char>, val buttons: List<List<Int>>, val joltage: List<Int>) {}

private fun parseInput(input: List<String>): List<Machine> {
    val machineRegex = Regex("""^\[([.#]+)]((?:\s\([0-9,]+\))+)\s\{([0-9,]+)}$""")
    return input.map {
        val (goal, buttons, joltage) = machineRegex.find(it)!!.groupValues.drop(1)

        val goalList = goal.toList()
        val buttonsList = buttons
            .split(Regex("\\s+"))
            .mapNotNull {
                it.removeSurrounding("(", ")")
                    .filter(Char::isDigit)
                    .takeIf { it.isNotEmpty() }
                    ?.toList()
                    ?.map { it.digitToInt() }
            }

        val joltageList = joltage.toList()
            .filter(Char::isDigit)
            .map { it.digitToInt() }

        Machine(goalList, buttonsList, joltageList)
    }
}

private fun part1(machines: List<Machine>): Int {
    for(machine in machines) {
        println(machine)
    }
    return 0
}

private fun part2(input: List<Machine>): Int {
    return 0
}

fun main() {
    val testInput = parseInput(readInput("Day10_test"))

    part1(testInput)
    check(part2(testInput) == 0)
     
    val input = parseInput(readInput("Day10"))
    check(part1(input) == 0)
    check(part2(input) == 0)
}
 