package aoc.day17

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day17Test {
    private val input = readInput(17)
    private val example1 = parse(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>\n")

    @Test
    fun example1_part1() {
        val underTest = Tetris(7, example1)
        underTest.simulate(2022)
        assertThat(underTest.towerHeight()).isEqualTo(3_068)
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(3_179)
    }
}