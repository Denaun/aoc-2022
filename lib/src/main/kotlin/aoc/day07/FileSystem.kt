package aoc.day07

sealed interface FileSystemNode {
    val parent: DirNode?
    fun size(): Int
}

data class FileNode(override val parent: DirNode,  var size: Int) : FileSystemNode {
    override fun size() = size
}
data class DirNode(
    override val parent: DirNode?,
    var children: MutableMap<String, FileSystemNode> = mutableMapOf(),
) : FileSystemNode {
    override fun size() =  children.values.sumOf { it.size() }
}
