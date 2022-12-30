package aoc.day13

fun part1(input: String): Int =
    parse(input).withIndex().filter { it.value.isRightOrder() }.sumOf { it.index + 1 }
