package aoc.day15

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.combinators.times
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken

object Day15Grammar : AocGrammar<List<Reading>>() {
    private val sensorToken by literalToken("Sensor at ")
    private val beaconToken by literalToken(": closest beacon is at ")
    private val xToken by literalToken("x=")
    private val yToken by literalToken(", y=")
    private val minusToken by literalToken("-")

    private val signedParser by (-minusToken * numberParser map { -it } or numberParser).map(Int::toLong)
    private val positionParser by -xToken * signedParser * -yToken * signedParser map { (x, y) ->
        Position(x, y)
    }
    private val readingParser by -sensorToken * positionParser * -beaconToken * positionParser map { (sensor, beacon) ->
        Reading(sensor, beacon)
    }

    override val rootParser by lineList(readingParser)
}

fun parse(data: String): List<Reading> = Day15Grammar.parseToEnd(data)