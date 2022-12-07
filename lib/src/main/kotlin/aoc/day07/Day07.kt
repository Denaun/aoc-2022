package aoc.day07

fun part1(input: String): Int =
    directories(parse(input).toFileSystem()).map { it.second }.filter { it <= 100_000 }.sum()

fun part2(input: String): Int {
    val totalSpace = 70_000_000
    val neededSpace = 30_000_000
    val fileSystem = parse(input).toFileSystem()
    val freeSpace = totalSpace - fileSystem.size()
    val minSize = neededSpace - freeSpace
    return directories(fileSystem).map { it.second }.filter { it >= minSize }.minOf { it }
}

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