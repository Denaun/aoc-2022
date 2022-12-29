package aoc.day10

fun part1(input: String): Int = execute(parse(input))
    .withIndex()
    .drop(20)
    .windowed(1, 40)
    .sumOf { (x) -> x.value * x.index }

fun part2(input: String): String = execute(parse(input)).draw()

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

fun Sequence<Int>.draw(): String {
    val litPixels = List(6) { MutableList(40) { false } }
    for ((cycle, offset) in drop(1).withIndex()) {
        val column = cycle % 40
        val row = (cycle / 40) % 6
        if (offset - 1 <= column && column <= offset + 1) {
            litPixels[row][column] = true
        }
    }
    return litPixels.joinToString("\n") {
        it.joinToString("") { isLit ->
            if (isLit) {
                "#"
            } else {
                "."
            }
        }
    }
}