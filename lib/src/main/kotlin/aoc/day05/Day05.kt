package aoc.day05

data class Crate(val name: Char)
data class Step(val quantity: Int, val from: Int, val to: Int) {
    fun execute(stacks: List<List<Crate>>): List<List<Crate>> {
        return stacks.mapIndexed { index, stack ->
            when (index) {
                from -> stack.dropLast(quantity)
                to -> stack + stacks[from].takeLast(quantity).asReversed()
                else -> stack
            }
        }
    }
}

fun List<Step>.execute(initialStacks: List<List<Crate>>): List<List<Crate>> =
    this.fold(initialStacks) { stacks, step -> step.execute(stacks) }

fun part1(input: String): String {
    val (initialStacks, steps) = parse(input)
    val finalStacks = steps.execute(initialStacks)
    return finalStacks.map { it.last().name }.joinToString("")
}