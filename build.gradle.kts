plugins {
    kotlin("jvm") version "2.2.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
    task("generateNextDay") {
        doLast {
            val prevDayNum = fileTree("$projectDir/src").matching {
                include("Day*.kt")
            }.maxOf {
                val (prevDayNum) = Regex("Day(\\d\\d)").find(it.name)!!.destructured
                prevDayNum.toInt()
            }
            val newDayNum = String.format("%02d", prevDayNum + 1)
            File("$projectDir/src", "Day$newDayNum.kt").writeText(
                """private fun part1(input: List<String>): Int {
    return 0
}

private fun part2(input: List<String>): Int {
    return 0
}

fun main() {
    val testInput = readInput("Day${newDayNum}_test")
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)
     
    val input = readInput("Day$newDayNum")
    check(part1(input) == 0)
    check(part2(input) == 0)
}
 """
            )
            val year = "2025"

            ProcessBuilder("aocd", newDayNum, year)
                .redirectOutput(ProcessBuilder.Redirect.to(File("$projectDir/src", "Day$newDayNum.txt")))
                .start()
                .waitFor()

            ProcessBuilder("aocd", newDayNum, year, "--example")
                .redirectOutput(ProcessBuilder.Redirect.to(File("$projectDir/src", "Day${newDayNum}_test.txt")))
                .start()
                .waitFor()
        }
    }
}
