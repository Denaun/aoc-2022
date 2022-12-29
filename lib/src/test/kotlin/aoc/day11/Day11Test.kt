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
            { x -> (x % 23) == 0 },
            2,
            3,
        ),
        Monkey(
            listOf(54, 65, 75, 74),
            { x -> x + 6 },
            { x -> (x % 19) == 0 },
            2,
            0,
        ),
        Monkey(
            listOf(79, 60, 97),
            { x -> x * x },
            { x -> (x % 13) == 0 },
            1,
            3,
        ),
        Monkey(
            listOf(74),
            { x -> x + 3 },
            { x -> (x % 17) == 0 },
            0,
            1,
        ),
    )

    @Test
    fun example1_part1() {
        assertThat(monkeyBusinesses(example1, 20))
            .containsExactly(101, 95, 7, 105).inOrder()
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(58_056)
    }
}