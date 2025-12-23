import kotlin.math.pow

data class JunctionBox(val x: Double, val y: Double, val z: Double, var circuit: Int) {
    override fun toString(): String {
        return "$x,$y,$z $circuit"
    }

    fun calculateDistanceTo(other: JunctionBox): Double {
        val (x2, y2, z2) = other

        return ((x2 - x).pow(2) + (y2 - y).pow(2) +
                (z2 - z).pow(2)).pow(0.5)
    }
}

private fun parseInput(input: List<String>): List<JunctionBox> {
    return input.map {
        val (x, y, z) = it.split(",").map { it.toInt() }
        JunctionBox(x.toDouble(), y.toDouble(), z.toDouble(), 0)
    }
}

private fun part1(boxes: List<JunctionBox>): Int {

    var min = Double.MAX_VALUE
    var minDistance: Pair<JunctionBox, JunctionBox> = Pair(JunctionBox(1.0, 0.0, 0.0, 0), JunctionBox(1.0, 0.0, 0.0, 0))
    var circuits = boxes.toMutableList()

    for (i in circuits.indices) {
        for (j in circuits.indices) {
            if (i == j) continue
            if (circuits[j].circuit == circuits[i].circuit && circuits[i].circuit != 0) {
                println("${circuits[i]} ${circuits[j]} are on the same circuit...")
                continue
            }
            val d = circuits[i].calculateDistanceTo(circuits[j])

            if (min > d) {
                min = d
                minDistance = Pair(circuits[i], circuits[j])
            }
//                println("comparing ${boxes[i]} to ${boxes[j]} d $d")
        }
    }

    println(minDistance)
    val latestCircuit = circuits.maxOf { it.circuit }

    for (i in 0..<circuits.size) {
        if (circuits[i] == minDistance.first) {
            circuits[i].circuit = latestCircuit + 1
        }
        if (circuits[i] == minDistance.second) {
            circuits[i].circuit = latestCircuit + 1
        }
    }
    println(circuits)


    return 0
}

private fun part2(input: List<String>): Int {
    return 0
}

fun main() {
    val testInput = parseInput(readInput("Day08_test"))
    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)

//    val input = readInput("Day08")
//    check(part1(input) == 0)
//    check(part2(input) == 0)
}
 