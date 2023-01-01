package aoc.day16

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day16Test {
    private val input = readInput(16)
    private val example1 = parse(
        """
        Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
        Valve BB has flow rate=13; tunnels lead to valves CC, AA
        Valve CC has flow rate=2; tunnels lead to valves DD, BB
        Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
        Valve EE has flow rate=3; tunnels lead to valves FF, DD
        Valve FF has flow rate=0; tunnels lead to valves EE, GG
        Valve GG has flow rate=0; tunnels lead to valves FF, HH
        Valve HH has flow rate=22; tunnel leads to valve GG
        Valve II has flow rate=0; tunnels lead to valves AA, JJ
        Valve JJ has flow rate=21; tunnel leads to valve II

    """.trimIndent()
    )

    @Test
    fun example1_part1() {
        assertThat(maximizeSingleReleasedPressure(example1, Valve("AA"), 30)).isEqualTo(1_651)
    }

    @Test
    fun example1_part2() {
        assertThat(maximizeDoubleReleasedPressure(example1, Valve("AA"), 26)).isEqualTo(1_707)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(1_986)
    }

    @Test
    fun part2() {
        assertThat(part2(input)).isEqualTo(2_464)
    }
}