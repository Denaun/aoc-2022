package aoc.day10

import aoc.readInput
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class Day10Test {
    private val input = readInput(10)
    private val example1 = listOf(Noop, AddX(3), AddX(-5))
    private val example2 = parse(
        """
        addx 15
        addx -11
        addx 6
        addx -3
        addx 5
        addx -1
        addx -8
        addx 13
        addx 4
        noop
        addx -1
        addx 5
        addx -1
        addx 5
        addx -1
        addx 5
        addx -1
        addx 5
        addx -1
        addx -35
        addx 1
        addx 24
        addx -19
        addx 1
        addx 16
        addx -11
        noop
        noop
        addx 21
        addx -15
        noop
        noop
        addx -3
        addx 9
        addx 1
        addx -3
        addx 8
        addx 1
        addx 5
        noop
        noop
        noop
        noop
        noop
        addx -36
        noop
        addx 1
        addx 7
        noop
        noop
        noop
        addx 2
        addx 6
        noop
        noop
        noop
        noop
        noop
        addx 1
        noop
        noop
        addx 7
        addx 1
        noop
        addx -13
        addx 13
        addx 7
        noop
        addx 1
        addx -33
        noop
        noop
        noop
        addx 2
        noop
        noop
        noop
        addx 8
        noop
        addx -1
        addx 2
        addx 1
        noop
        addx 17
        addx -9
        addx 1
        addx 1
        addx -3
        addx 11
        noop
        noop
        addx 1
        noop
        addx 1
        noop
        noop
        addx -13
        addx -19
        addx 1
        addx 3
        addx 26
        addx -30
        addx 12
        addx -1
        addx 3
        addx 1
        noop
        noop
        noop
        addx -9
        addx 18
        addx 1
        addx 2
        noop
        noop
        addx 9
        noop
        noop
        noop
        addx -1
        addx 2
        addx -37
        addx 1
        addx 3
        noop
        addx 15
        addx -21
        addx 22
        addx -6
        addx 1
        noop
        addx 2
        addx 1
        noop
        addx -10
        noop
        noop
        addx 20
        addx 1
        addx 2
        addx 2
        addx -6
        addx -11
        noop
        noop
        noop

    """.trimIndent()
    )

    @Test
    fun example1_part1() {
        assertThat(execute(example1).toList())
            .containsExactly(1, 1, 1, 1, 4, 4, -1).inOrder()
    }

    @Test
    fun example2_part1() {
        assertThat(execute(example2).withIndex()
            .drop(20)
            .windowed(1, 40)
            .map { (v) -> v }
            .toList())
            .containsAtLeast(
                IndexedValue(20, 21),
                IndexedValue(60, 19),
                IndexedValue(100, 18),
                IndexedValue(140, 21),
                IndexedValue(180, 16),
                IndexedValue(220, 18),
            )
    }

    @Test
    fun example2_part2() {
        assertThat(execute(example2).draw()).isEqualTo(
            """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....
        """.trimIndent()
        )
    }

    @Test
    fun part1() {
        assertThat(part1(input)).isEqualTo(16_880)
    }

    @Test
    fun part2() {
        assertThat(part2(input)).isEqualTo(
            """
            ###..#..#..##..####..##....##.###..###..
            #..#.#.#..#..#....#.#..#....#.#..#.#..#.
            #..#.##...#..#...#..#..#....#.###..#..#.
            ###..#.#..####..#...####....#.#..#.###..
            #.#..#.#..#..#.#....#..#.#..#.#..#.#.#..
            #..#.#..#.#..#.####.#..#..##..###..#..#.
        """.trimIndent()
        )
    }
}