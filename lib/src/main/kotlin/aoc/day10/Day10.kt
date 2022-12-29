package aoc.day10

fun part1(input: String): Int = execute(parse(input))
    .withIndex()
    .drop(20)
    .windowed(1, 40)
    .sumOf { (x) -> x.value * x.index }


fun execute(instructions: List<Instruction>): Sequence<Int> = sequence {
    var x = 1
    yield(x)
    for (instruction in instructions) {
        repeat(instruction.cycles) {
            yield(x)
        }
        if (instruction is AddX) {
            x += instruction.v
        }
    }
    yield(x)
}