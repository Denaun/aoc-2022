package aoc.day11

open class Monkey(
    open val items: List<Int>,
    val operation: (Int) -> Int,
    val test: (Int) -> Boolean,
    val ifTrue: Int,
    val ifFalse: Int,
) {
    fun toMutableMonkey(): MutableMonkey = MutableMonkey(
        items.toMutableList(),
        operation,
        test,
        ifTrue,
        ifFalse,
    )
}

class MutableMonkey(
    override val items: MutableList<Int>,
    operation: (Int) -> Int,
    test: (Int) -> Boolean,
    ifTrue: Int,
    ifFalse: Int,
) : Monkey(items, operation, test, ifTrue, ifFalse) {
    fun executeTurn(monkeys: List<MutableMonkey>) {
        while (items.isNotEmpty()) {
            inspectAndThrowFirst(monkeys)
        }
    }

    private fun inspectAndThrowFirst(monkeys: List<MutableMonkey>) {
        val initialWorry = items.removeFirst()
        val inspectedWorry = operation(initialWorry)
        val relievedWorry = inspectedWorry / 3
        monkeys[target(relievedWorry)].items += relievedWorry
    }

    private fun target(worry: Int): Int = if (test(worry)) {
        ifTrue
    } else {
        ifFalse
    }
}