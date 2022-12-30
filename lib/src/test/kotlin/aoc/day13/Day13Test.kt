package aoc.day13

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day13Test {
    private val input = readInput(13)
    private val example1 = parse(
        """
        [1,1,3,1,1]
        [1,1,5,1,1]

        [[1],[2,3,4]]
        [[1],4]

        [9]
        [[8,7,6]]

        [[4,4],4,4]
        [[4,4],4,4,4]

        [7,7,7,7]
        [7,7,7]

        []
        [3]

        [[[]]]
        [[]]

        [1,[2,[3,[4,[5,6,7]]]],8,9]
        [1,[2,[3,[4,[5,6,0]]]],8,9]

    """.trimIndent()
    )

    @Test
    fun example1_part1() {
        assertThat(example1.withIndex().filter { it.value.isRightOrder() }
            .map { it.index }).containsExactly(0, 1, 3, 5)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(5_330)
    }
}