package aoc.day11

import aoc.readInput
import com.google.common.truth.Truth.assertThat

import kotlin.test.Test

class Day11Test {
    private val input = readInput(11)
    private val example1 = listOf(
        Monkey(
            listOf(79, 98),
            { x -> x * 19 },
            23,
            2,
            3,
        ),
        Monkey(
            listOf(54, 65, 75, 74),
            { x -> x + 6 },
            19,
            2,
            0,
        ),
        Monkey(
            listOf(79, 60, 97),
            { x -> x * x },
            13,
            1,
            3,
        ),
        Monkey(
            listOf(74),
            { x -> x + 3 },
            17,
            0,
            1,
        ),
    )

    @Test
    fun example1_part1() {
        assertThat(simulateCalmly(example1, 20))
            .containsExactly(101, 95, 7, 105).inOrder()
    }

    @Test
    fun example1_part2() {
        assertThat(simulateStressfully(example1, 1))
            .containsExactly(2, 4, 3, 6).inOrder()
        assertThat(simulateStressfully(example1, 20))
            .containsExactly(99, 97, 8, 103).inOrder()
        assertThat(simulateStressfully(example1, 10_000))
            .containsExactly(52_166, 47_830, 1_938, 52_013).inOrder()
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(58_056)
    }

    @Test
    fun part2() {
        assertThat(part2(input)).isEqualTo(15_048_718_170L)
    }
}