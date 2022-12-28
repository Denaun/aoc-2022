package aoc.day09

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.asJust
import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken

object Day09Grammar : AocGrammar<List<Motion>>() {
    private val leftToken by literalToken("L")
    private val rightToken by literalToken("R")
    private val upToken by literalToken("U")
    private val downToken by literalToken("D")

    private val leftParser by leftToken asJust Direction.LEFT
    private val rightParser by rightToken asJust Direction.RIGHT
    private val upParser by upToken asJust Direction.UP
    private val downParser by downToken asJust Direction.DOWN
    private val directionParser by leftParser or rightParser or upParser or downParser

    private val motionParser by separatedPair(
        directionParser,
        spaceToken,
        numberParser
    ) { direction, steps -> Motion(direction, steps) }

    override val rootParser by lineList(motionParser)
}

fun parse(data: String): List<Motion> = Day09Grammar.parseToEnd(data)