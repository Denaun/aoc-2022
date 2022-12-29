package aoc.day11

fun part1(input: String): Int =
    monkeyBusinesses(parse(input), 20).sortedDescending().take(2).reduce(Int::times)

fun monkeyBusinesses(monkeys: List<Monkey>, turns: Int): List<Int> {
    val mutableMonkeys = monkeys.map(Monkey::toMutableMonkey)
    val businesses = MutableList(monkeys.size) { 0 }
    repeat(turns) {
        for ((index, monkey) in mutableMonkeys.withIndex()) {
            businesses[index] += monkey.items.size
            monkey.executeTurn(mutableMonkeys)
        }
    }
    return businesses.toList()
}
