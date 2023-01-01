package aoc.day17

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.asJust
import com.github.h0tk3y.betterParse.combinators.oneOrMore
import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken

object Day17Grammar : AocGrammar<List<Direction>>() {
    private val leftToken by literalToken("<")
    private val rightToken by literalToken(">")

    private val leftParser by leftToken asJust Direction.LEFT
    private val rightParser by rightToken asJust Direction.RIGHT
    private val directionParser by leftParser or rightParser

    override val rootParser by oneOrMore(directionParser).eolTerminated()
}

fun parse(data: String): List<Direction> = Day17Grammar.parseToEnd(data)