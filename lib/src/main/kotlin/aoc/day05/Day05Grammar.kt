package aoc.day05

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken

object Day05Grammar : AocGrammar<Pair<List<List<Crate>>, List<Step>>>() {
    private val crateToken by regexToken("\\[[A-Z]]")
    private val moveToken by literalToken("move")
    private val fromToken by literalToken("from")
    private val toToken by literalToken("to")

    private val optionalCrateParser by (crateToken use { Crate(text[1]) }) or (3.times(spaceToken) asJust null)
    private val stackRowParser by separatedTerms(optionalCrateParser, spaceToken).eolTerminated()
    private val stackParser by oneOrMore(stackRowParser) map (::transpose) and skip(
        separatedTerms(-spaceToken and numberToken and -spaceToken, spaceToken).eolTerminated()
    )

    private val moveParser = skip(moveToken and spaceToken)
    private val fromParser = skip(spaceToken and fromToken and spaceToken)
    private val toParser = skip(spaceToken and toToken and spaceToken)
    private val stepParser by (moveParser and numberParser and fromParser and numberParser and toParser and numberParser) map {
        Step(it.t1, it.t2 - 1, it.t3 - 1)
    }
    private val stepsParser by lineList(stepParser)

    override val rootParser by separatedPair(stackParser, eolToken, stepsParser)

    /** Note: does not check that there are no holes. */
    private fun transpose(crates: List<List<Crate?>>): List<List<Crate>> {
        require(crates.isNotEmpty())
        val stackCount = crates[0].size
        require(crates.all { row -> row.size == stackCount })
        return (0 until stackCount).map { rowIndex ->
            crates.asReversed().mapNotNull { crate -> crate[rowIndex] }
        }
    }
}

fun parse(data: String): Pair<List<List<Crate>>, List<Step>> = Day05Grammar.parseToEnd(data)