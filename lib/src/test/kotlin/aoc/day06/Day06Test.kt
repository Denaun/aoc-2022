package aoc.day06

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day06Test {
    private val input = readInput(6)

    @Test
    fun example1_part1() {
        assertThat(findMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb")).isEqualTo(7)
    }

    @Test
    fun example2_part1() {
        assertThat(findMarker("bvwbjplbgvbhsrlpgdmjqwftvncz")).isEqualTo(5)
    }

    @Test
    fun example3_part1() {
        assertThat(findMarker("nppdvjthqldpwncqszvftbrmjlhg")).isEqualTo(6)
    }

    @Test
    fun example4_part1() {
        assertThat(findMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")).isEqualTo(10)
    }

    @Test
    fun example5_part1() {
        assertThat(findMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")).isEqualTo(11)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(1892)
    }
}