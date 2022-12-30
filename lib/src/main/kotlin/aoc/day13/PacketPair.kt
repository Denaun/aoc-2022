package aoc.day13

data class PacketPair(val left: PacketData, val right: PacketData) {
    fun isRightOrder(): Boolean = left < right
}
