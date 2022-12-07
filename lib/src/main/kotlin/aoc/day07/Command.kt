package aoc.day07

sealed interface Command
data class CdIn(val path: String) : Command
object CdOut : Command
data class Ls(val contents: List<Element>) : Command

sealed interface Element {
    val name: String
}

data class File(override val name: String, val size: Int) : Element
data class Dir(override val name: String) : Element

fun List<Command>.toFileSystem(): DirNode {
    require(this.first() == CdIn("/"))
    val root = DirNode(parent = null)
    var current = root
    for (command in this) {
        when (command) {
            is CdOut -> current = current.parent!!

            is CdIn -> {
                var child = current.children[command.path]
                if (child == null) {
                    child = DirNode(parent = root)
                    current.children[command.path] = child
                }
                require(child is DirNode) { "Can only cd into directories." }
                current = child
            }

            is Ls -> {
                require(current.children.isEmpty()) { "Directory already visited." }
                current.children = command.contents.associateBy({ it.name }, {
                    when (it) {
                        is File -> FileNode(current, it.size)
                        is Dir -> DirNode(current)
                    }
                }).toMutableMap()
            }
        }
    }
    return root
}