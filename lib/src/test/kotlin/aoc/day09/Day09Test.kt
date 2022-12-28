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

    @Test
    fun example1_part1() {
        assertThat(simulate(example1).toSet()).hasSize(13)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(6_269)
    }
}