package aoc.day11

open class Monkey(
    open val items: List<Long>,
    val operation: (Long) -> Long,
    val divisor: Int,
    val ifTrue: Int,
    val ifFalse: Int,
) {
    fun toMutableRelievingMonkey(): MutableMonkey = MutableRelievingMonkey(
        items.toMutableList(),
        operation,
        divisor,
        ifTrue,
        ifFalse,
    )

    fun toMutableUnrelentingMonkey(): MutableMonkey = MutableUnrelentingMonkey(
        items.toMutableList(),
        operation,
        divisor,
        ifTrue,
        ifFalse,
    )
}

abstract class MutableMonkey(
    override var items: MutableList<Long>,
    operation: (Long) -> Long,
    divisor: Int,
    ifTrue: Int,
    ifFalse: Int,
) : Monkey(items, operation, divisor, ifTrue, ifFalse) {
    fun executeTurn(monkeys: List<MutableMonkey>) {
        while (items.isNotEmpty()) {
            inspectAndThrowFirst(monkeys)
        }
    }

    protected abstract fun inspectAndThrowFirst(monkeys: List<MutableMonkey>)

    protected fun target(worry: Long): Int = if (worry % divisor == 0L) {
        ifTrue
    } else {
        ifFalse
    }
}

private class MutableRelievingMonkey(
    items: MutableList<Long>,
    operation: (Long) -> Long,
    divisor: Int,
    ifTrue: Int,
    ifFalse: Int,
) : MutableMonkey(items, operation, divisor, ifTrue, ifFalse) {
    override fun inspectAndThrowFirst(monkeys: List<MutableMonkey>) {
        val initialWorry = items.removeFirst()
        val inspectedWorry = operation(initialWorry)
        val relievedWorry = inspectedWorry / 3
        monkeys[target(relievedWorry)].items += relievedWorry
    }
}

private class MutableUnrelentingMonkey(
    items: MutableList<Long>,
    operation: (Long) -> Long,
    divisor: Int,
    ifTrue: Int,
    ifFalse: Int,
) : MutableMonkey(items, operation, divisor, ifTrue, ifFalse) {
    override fun inspectAndThrowFirst(monkeys: List<MutableMonkey>) {
        val manageableWorry = items.removeFirst() % monkeys.map(Monkey::divisor).reduce(Int::times)
        val inspectedWorry = operation(manageableWorry)
        monkeys[target(inspectedWorry)].items += inspectedWorry
    }
}