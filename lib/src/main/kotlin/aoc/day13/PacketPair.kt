package aoc.day13

data class PacketPair(val left: PacketData, val right: PacketData): Iterable<PacketData> {
    fun isRightOrder(): Boolean = left < right

    override fun iterator(): Iterator<PacketData> = sequenceOf(left, right).iterator()
}
