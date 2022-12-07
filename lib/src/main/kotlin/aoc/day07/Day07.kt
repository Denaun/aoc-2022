package aoc.day07

fun part1(input: String): Int =
    directories(parse(input).toFileSystem()).map { it.second }.filter { it <= 100_000 }.sum()

fun directories(root: DirNode): List<Pair<String, Int>> {
    val result = mutableListOf<Pair<String, Int>>()
    val toVisit = mutableListOf(root)
    while (toVisit.isNotEmpty()) {
        val current = toVisit.removeFirst()
        for ((name, child) in current.children) {
            if (child !is DirNode) {
                continue
            }
            result.add(name to child.size())
            toVisit.add(child)
        }
    }
    return result
}