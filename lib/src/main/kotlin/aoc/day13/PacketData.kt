package aoc.day13

sealed interface PacketData: Comparable<PacketData>

class PacketInteger(val value: Int) : PacketData {
    override operator fun compareTo(other: PacketData): Int = when (other) {
        is PacketInteger -> value.compareTo(other.value)
        is PacketList -> PacketList(listOf(this)).compareTo(other)
    }
}

class PacketList(val values: List<PacketData>) : PacketData {
    override operator fun compareTo(other: PacketData): Int = when (other) {
        is PacketInteger -> compareTo(PacketList(listOf(other)))
        is PacketList -> values.zip(other.values) { left, right -> left.compareTo(right) }
            .find { it != 0 } ?: values.size.compareTo(other.values.size)
    }
}
