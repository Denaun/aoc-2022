package aoc.day02

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.asJust
import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.combinators.separatedTerms
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken

object Day02Grammar : AocGrammar<List<Pair<Shape, Shape>>>() {
    private val aToken by literalToken("A")
    private val bToken by literalToken("B")
    private val cToken by literalToken("C")
    private val xToken by literalToken("X")
    private val yToken by literalToken("Y")
    private val zToken by literalToken("Z")

    private val lhs by ((aToken asJust Shape.ROCK) or (bToken asJust Shape.PAPER) or (cToken asJust Shape.SCISSORS))
    private val rhs by ((xToken asJust Shape.ROCK) or (yToken asJust Shape.PAPER) or (zToken asJust Shape.SCISSORS))
    private val match by separatedPair(lhs, spaceToken, rhs)

    override val rootParser by separatedTerms(match, eolToken).eolTerminated()
}

fun parse(data: String): List<Pair<Shape, Shape>> {
    return Day02Grammar.parseToEnd(data)
}