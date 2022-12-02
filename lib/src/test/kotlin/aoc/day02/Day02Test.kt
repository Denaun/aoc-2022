package aoc.day02

import aoc.readInput
import com.google.common.truth.Truth.assertThat

import org.junit.Test

class Day02Test {
    private val input = readInput(2)
    private val example1 = listOf(
        Shape.ROCK to Unknown.Y,
        Shape.PAPER to Unknown.X,
        Shape.SCISSORS to Unknown.Z,
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
        assertThat(example1.map { (lhs, rhs) -> score(lhs, toShape(rhs)) }).containsExactly(8, 1, 6)
            .inOrder()
    }

    @Test
    fun example1_part2() {
        assertThat(example1.map { (lhs, rhs) -> score(lhs, toResult(rhs)) }).containsExactly(
            4, 1, 7
        ).inOrder()
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(11_666)
    }

    @Test
    fun part2() {
        assertThat(part2(input)).isEqualTo(12_767)
    }
}
