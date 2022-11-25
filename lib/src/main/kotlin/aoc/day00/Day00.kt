package aoc.day00

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.asJust
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken

object Day00Grammar : AocGrammar<Boolean>() {
    private val trueToken by literalToken("true")

    override val rootParser by (trueToken asJust true).eolTerminated()
}

fun parse(data: String): Boolean {
    return Day00Grammar.parseToEnd(data)
}