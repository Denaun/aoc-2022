package aoc.day12

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day12Test {
    private val input = readInput(12)
    private val example1 = parse(
        """
        Sabqponm
        abcryxxl
        accszExk
        acctuvwj
        abdefghi

    """.trimIndent()
    )

    @Test
    fun example1_part1() {
        assertThat(shortestPath(example1)).isEqualTo(31)
    }

    @Test
    fun example1_part2() {
        assertThat(mostScenicPath(example1)).isEqualTo(29)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(504)
    }

    @Test
    fun part2() {
        assertThat(part2(input)).isEqualTo(500)
    }
}