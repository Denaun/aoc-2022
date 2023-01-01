package aoc.day17

fun allRocks(): List<Rock> = listOf(Dash, Cross, Angle, Bar, Cube)

sealed interface Rock {
    val indices: List<Coordinate>
}

object Dash : Rock {
    override val indices: List<Coordinate> = listOf(
        Coordinate(0, 0),
        Coordinate(0, 1),
        Coordinate(0, 2),
        Coordinate(0, 3),
    )
}

object Cross : Rock {
    override val indices: List<Coordinate> = listOf(
        Coordinate(0, 1),
        Coordinate(1, 0),
        Coordinate(1, 1),
        Coordinate(1, 2),
        Coordinate(2, 1),
    )
}

object Angle : Rock {
    override val indices: List<Coordinate> = listOf(
        Coordinate(0, 0),
        Coordinate(0, 1),
        Coordinate(0, 2),
        Coordinate(1, 2),
        Coordinate(2, 2),
    )
}

object Bar : Rock {
    override val indices: List<Coordinate> = listOf(
        Coordinate(0, 0),
        Coordinate(1, 0),
        Coordinate(2, 0),
        Coordinate(3, 0),
    )
}

object Cube : Rock {
    override val indices: List<Coordinate> = listOf(
        Coordinate(0, 0),
        Coordinate(0, 1),
        Coordinate(1, 0),
        Coordinate(1, 1),
    )
}
