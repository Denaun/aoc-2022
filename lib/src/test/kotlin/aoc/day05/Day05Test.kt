package aoc.day05

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day05Test {
    private val input = readInput(5)
    private val example1 = """
            |    [D]    
            |[N] [C]    
            |[Z] [M] [P]
            | 1   2   3 
            |
            |move 1 from 2 to 1
            |move 3 from 1 to 3
            |move 2 from 2 to 1
            |move 1 from 1 to 2
            |
    """.trimMargin()

    @Test
    fun example1_parser() {
        val (stacks, steps) = parse(example1)

        assertThat(stacks).containsExactly(
            listOf(Crate('Z'), Crate('N')),
            listOf(Crate('M'), Crate('C'), Crate('D')),
            listOf(Crate('P')),
        ).inOrder()
        assertThat(steps).containsExactly(
            Step(1, 1, 0),
            Step(3, 0, 2),
            Step(2, 1, 0),
            Step(1, 0, 1),
        ).inOrder()
    }

    @Test
    fun example1_part1() {
        assertThat(
            listOf(
                Step(1, 1, 0),
                Step(3, 0, 2),
                Step(2, 1, 0),
                Step(1, 0, 1),
            ).execute(
                listOf(
                    listOf(Crate('Z'), Crate('N')),
                    listOf(Crate('M'), Crate('C'), Crate('D')),
                    listOf(Crate('P')),
                )
            )
        ).containsExactly(
            listOf(Crate('C')),
            listOf(Crate('M')),
            listOf(Crate('P'), Crate('D'), Crate('N'), Crate('Z')),
        ).inOrder()
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo("DHBJQJCCW")
    }
}