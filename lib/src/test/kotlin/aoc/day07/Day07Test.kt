package aoc.day07

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class Day07Test {
    private val input = readInput(7)
    private val example1 = listOf(
        CdIn("/"),
        Ls(
            listOf(
                Dir("a"),
                File("b.txt", 14_848_514),
                File("c.txt", 8_504_156),
                Dir("d"),
            )
        ),
        CdIn("a"),
        Ls(
            listOf(
                Dir("e"),
                File("f", 29_116),
                File("g", 2_557),
                File("h.lst", 62_596),
            )
        ),
        CdIn("e"),
        Ls(
            listOf(
                File("i", 584),
            )
        ),
        CdOut,
        CdOut,
        CdIn("d"),
        Ls(
            listOf(
                File("j", 40_60_174),
                File("d.log", 80_33_020),
                File("d.ext", 5_626_152),
                File("k", 72_14_296),
            )
        ),
    )

    @Test
    fun example1_part1() {
        val fileSystem = example1.toFileSystem()
        assertThat(directories(fileSystem).filter { it.second <= 100_000 }.toMap()).containsExactly(
            "e", 584, "a", 94_853
        )
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(1_783_610)
    }
}