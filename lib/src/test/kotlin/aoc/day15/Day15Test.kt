@file:Suppress("UnstableApiUsage")

package aoc.day15

import aoc.readInput
import com.google.common.collect.Range
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test


class Day15Test {
    private val input = readInput(15)
    private val example1 = parse(
        """
    Sensor at x=2, y=18: closest beacon is at x=-2, y=15
    Sensor at x=9, y=16: closest beacon is at x=10, y=16
    Sensor at x=13, y=2: closest beacon is at x=15, y=3
    Sensor at x=12, y=14: closest beacon is at x=10, y=16
    Sensor at x=10, y=20: closest beacon is at x=10, y=16
    Sensor at x=14, y=17: closest beacon is at x=10, y=16
    Sensor at x=8, y=7: closest beacon is at x=2, y=10
    Sensor at x=2, y=0: closest beacon is at x=2, y=10
    Sensor at x=0, y=11: closest beacon is at x=2, y=10
    Sensor at x=20, y=14: closest beacon is at x=25, y=17
    Sensor at x=17, y=20: closest beacon is at x=21, y=22
    Sensor at x=16, y=7: closest beacon is at x=15, y=3
    Sensor at x=14, y=3: closest beacon is at x=15, y=3
    Sensor at x=20, y=1: closest beacon is at x=15, y=3

""".trimIndent()
    )

    @Test
    fun example1_part1() {
        assertThat(surelyEmptyPositions(example1, 10L).asRanges()).containsExactly(
            Range.closedOpen(-2L, 2L), Range.openClosed(2L, 24L)
        )
    }

    @Test
    fun example1_part2() {
        assertThat(findDistressBeacon(example1, 20L)).isEqualTo(Position(14L, 11L))
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(4_717_631)
    }

    @Test
    fun part2() {
        assertThat(part2(input)).isEqualTo(13_197_439_355_220L)
    }
}