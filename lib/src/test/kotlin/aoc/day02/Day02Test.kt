package aoc.day02

import aoc.readInput
import com.google.common.truth.Truth.assertThat

import org.junit.Test

class Day02Test {
    private val input = readInput(2)
    private val example1 = listOf(
        Shape.ROCK to Shape.PAPER,
        Shape.PAPER to Shape.ROCK,
        Shape.SCISSORS to Shape.SCISSORS,
    )

    @Test
    fun example1_parser() {
        assertThat(
            parse(
                """
                A Y
                B X
                C Z
                
                """.trimIndent()
            )
        ).isEqualTo(example1)
    }

    @Test
    fun example1_part1() {
        assertThat(example1.map { (lhs, rhs) -> score(lhs, rhs) }).containsExactly(8, 1, 6)
            .inOrder()
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(11_666)
    }
}
