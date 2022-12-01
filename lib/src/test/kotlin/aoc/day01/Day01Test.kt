package aoc.day01

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day01Test {
    private val input = readInput(1)
    private val example1 = listOf(
        listOf(1_000, 2_000, 3_000),
        listOf(4_000),
        listOf(5_000, 6_000),
        listOf(7_000, 8_000, 9_000),
        listOf(10_000),
    )

    @Test
    fun example1() {
        assertThat(largestGroupSum(example1)).isEqualTo(24_000)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(75_501)
    }
}
