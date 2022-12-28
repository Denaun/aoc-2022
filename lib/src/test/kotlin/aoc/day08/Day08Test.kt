package aoc.day08

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day08Test {
    private val input = readInput(8)
    private val example1 = parse(
        """
        30373
        25512
        65332
        33549
        35390

    """.trimIndent()
    )

    @Test
    fun example1_part1() {
        assertThat(countAllVisible(example1)).isEqualTo(21)
    }

    @Test
    fun example1_scenicScore() {
        assertThat(scenicScore(1, 2, example1)).isEqualTo(4)
    }

    @Test
    fun example1_part2() {
        assertThat(bestScenicScore(example1)).isEqualTo(8)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(1_851)
    }

    @Test
    fun part2() {
        assertThat(part2(input)).isEqualTo(574_080)
    }
}