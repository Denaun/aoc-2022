package aoc.day13

fun part1(input: String): Int =
    parse(input).withIndex().filter { it.value.isRightOrder() }.sumOf { it.index + 1 }

fun part2(input: String): Int = decoderKey(parse(input).flatten())

fun decoderKey(packets: List<PacketData>): Int =
    packets.asSequence().plus(DIVIDER_PAIR).sorted().withIndex()
        .filter { DIVIDER_PAIR.contains(it.value) }.map { it.index + 1 }.reduce(Int::times)

val DIVIDER_PAIR: PacketPair = PacketPair(
    PacketInteger(2),
    PacketInteger(6),
)