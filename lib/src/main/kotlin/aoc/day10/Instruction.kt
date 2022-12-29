package aoc.day10

sealed class Instruction(val cycles: Int)

object Noop : Instruction(1)
class AddX(val v: Int): Instruction(2)