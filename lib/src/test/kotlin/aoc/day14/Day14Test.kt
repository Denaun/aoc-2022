package aoc.day14

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day14Test {
    private val input = readInput(14)
    private val example1 = listOf(
        Path(listOf(Point(498, 4), Point(498, 6), Point(496, 6))),
        Path(listOf(Point(503, 4), Point(502, 4), Point(502, 9), Point(494, 9))),
    )

    @Test
    fun example1_part1() {
        assertThat(simulateCave(example1, ::simulateFloorless)).isEqualTo(24)
    }

    @Test
    fun example1_part2() {
        assertThat(simulateCave(example1, ::simulateFloorful)).isEqualTo(93)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(618)
    }

    @Test
    fun part2() {
        assertThat(part2(input)).isEqualTo(26_358)
    }
}