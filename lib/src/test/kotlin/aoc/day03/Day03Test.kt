package aoc.day03

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day03Test {
    private val input = readInput(3)
    private val example1 = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw",
    )

    @Test
    fun example1_part1() {
        assertThat(example1.map { misplacedItem(it) }).containsExactly('p', 'L', 'P', 'v', 't', 's')
            .inOrder()
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(8_109)
    }
}

