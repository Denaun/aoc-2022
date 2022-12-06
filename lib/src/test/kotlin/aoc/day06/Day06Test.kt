package aoc.day06

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day06Test {
    private val input = readInput(6)

    @Test
    fun example1_part1() {
        assertThat(findMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 4)).isEqualTo(7)
    }

    @Test
    fun example2_part1() {
        assertThat(findMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", 4)).isEqualTo(5)
    }

    @Test
    fun example3_part1() {
        assertThat(findMarker("nppdvjthqldpwncqszvftbrmjlhg", 4)).isEqualTo(6)
    }

    @Test
    fun example4_part1() {
        assertThat(findMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4)).isEqualTo(10)
    }

    @Test
    fun example5_part1() {
        assertThat(findMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4)).isEqualTo(11)
    }

    @Test
    fun example1_part2() {
        assertThat(findMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14)).isEqualTo(19)
    }

    @Test
    fun example2_part2() {
        assertThat(findMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", 14)).isEqualTo(23)
    }

    @Test
    fun example3_part2() {
        assertThat(findMarker("nppdvjthqldpwncqszvftbrmjlhg", 14)).isEqualTo(23)
    }

    @Test
    fun example4_part2() {
        assertThat(findMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14)).isEqualTo(29)
    }

    @Test
    fun example5_part2() {
        assertThat(findMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14)).isEqualTo(26)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(1892)
    }

    @Test
    fun part2() {
        assertThat(part2(input)).isEqualTo(2_313)
    }
}