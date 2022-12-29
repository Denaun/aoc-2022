package aoc.day11

fun part1(input: String): Long =
    monkeyBusiness(simulateCalmly(parse(input), 20))

fun part2(input: String): Long =
    monkeyBusiness(simulateStressfully(parse(input), 10_000))


fun monkeyBusiness(numInspections: List<Int>): Long = numInspections
    .sortedDescending()
    .take(2)
    .map(Int::toLong)
    .reduce(Long::times)

fun simulateCalmly(monkeys: List<Monkey>, turns: Int): List<Int> =
    simulate(monkeys.map(Monkey::toMutableRelievingMonkey), turns)


fun simulateStressfully(monkeys: List<Monkey>, turns: Int): List<Int> =
    simulate(monkeys.map(Monkey::toMutableUnrelentingMonkey), turns)


fun simulate(mutableMonkeys: List<MutableMonkey>, turns: Int): List<Int> {
    val businesses = MutableList(mutableMonkeys.size) { 0 }
    repeat(turns) {
        for ((index, monkey) in mutableMonkeys.withIndex()) {
            businesses[index] += monkey.items.size
            monkey.executeTurn(mutableMonkeys)
        }
    }
    return businesses.toList()
}