package aoc.day04

import aoc.readInput
import com.google.common.collect.Range
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day04Test {
    private val input = readInput(4)
    private val example1 = listOf(
        Range.closed(2, 4) to Range.closed(6, 8),
        Range.closed(2, 3) to Range.closed(4, 5),
        Range.closed(5, 7) to Range.closed(7, 9),
        Range.closed(2, 8) to Range.closed(3, 7),
        Range.closed(6, 6) to Range.closed(4, 6),
        Range.closed(2, 6) to Range.closed(4, 8),
    )

    @Test
    fun example1_part1() {
        assertThat(example1.count { (lhs, rhs) -> isFullOverlap(lhs, rhs) }).isEqualTo(2)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(556)
    }
}