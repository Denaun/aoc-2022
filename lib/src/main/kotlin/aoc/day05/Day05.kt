package aoc.day05

data class Crate(val name: Char)
data class Step(val quantity: Int, val from: Int, val to: Int)

abstract class CrateMover {
    fun execute(steps: List<Step>, initialStacks: List<List<Crate>>): List<List<Crate>> =
        steps.fold(initialStacks) { stacks, step -> execute(step, stacks) }

    abstract fun execute(step: Step, stacks: List<List<Crate>>): List<List<Crate>>
}

class CrateMover9000: CrateMover() {
    override fun execute(step: Step, stacks: List<List<Crate>>): List<List<Crate>> {
        return stacks.mapIndexed { index, stack ->
            when (index) {
                step.from -> stack.dropLast(step.quantity)
                step.to -> stack + stacks[step.from].takeLast(step.quantity).asReversed()
                else -> stack
            }
        }
    }
}

class CrateMover9001: CrateMover() {
    override fun execute(step: Step, stacks: List<List<Crate>>): List<List<Crate>> {
        return stacks.mapIndexed { index, stack ->
            when (index) {
                step.from -> stack.dropLast(step.quantity)
                step.to -> stack + stacks[step.from].takeLast(step.quantity)
                else -> stack
            }
        }
    }
}

fun part1(input: String): String {
    val (initialStacks, steps) = parse(input)
    val finalStacks = CrateMover9000().execute(steps, initialStacks)
    return finalStacks.map { it.last().name }.joinToString("")
}

fun part2(input: String): String {
    val (initialStacks, steps) = parse(input)
    val finalStacks = CrateMover9001().execute(steps, initialStacks)
    return finalStacks.map { it.last().name }.joinToString("")
}