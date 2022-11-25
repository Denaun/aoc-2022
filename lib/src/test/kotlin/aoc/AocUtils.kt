package aoc

import com.google.common.io.Resources.getResource

fun readInput(day: Int): String {
    val fileName = "day%02d.in".format(day)
    @Suppress("UnstableApiUsage")
    return getResource(fileName).readText()
}