package aoc.day09

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day09Test {
    private val input = readInput(9)
    private val example1 = listOf(
        Motion(Direction.RIGHT, 4),
        Motion(Direction.UP, 4),
        Motion(Direction.LEFT, 3),
        Motion(Direction.DOWN, 1),
        Motion(Direction.RIGHT, 4),
        Motion(Direction.DOWN, 1),
        Motion(Direction.LEFT, 5),
        Motion(Direction.RIGHT, 2),
    )
    private val example2 = listOf(
        Motion(Direction.RIGHT, 5),
        Motion(Direction.UP, 8),
        Motion(Direction.LEFT, 8),
        Motion(Direction.DOWN, 3),
        Motion(Direction.RIGHT, 17),
        Motion(Direction.DOWN, 10),
        Motion(Direction.LEFT, 25),
        Motion(Direction.UP, 20),
    )

    @Test
    fun example1_part1() {
        assertThat(simulate(example1, 2)).hasSize(13)
    }

    @Test
    fun example1_part2() {
        assertThat(simulate(example1, 10)).hasSize(1)
    }

    @Test
    fun example2_part2() {
        assertThat(simulate(example2, 10)).hasSize(36)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(6_269)
    }

    @Test
    fun part2() {
        assertThat(part2(input)).isEqualTo(2_557)
    }
}