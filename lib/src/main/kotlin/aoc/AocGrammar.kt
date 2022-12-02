package aoc

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.skip
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken
import com.github.h0tk3y.betterParse.parser.Parser

abstract class AocGrammar<out T> : Grammar<T>() {
    protected val eolToken by literalToken("\n")
    private val numberToken by regexToken("\\d+")
    protected val spaceToken by literalToken(" ")

    protected val numberParser by numberToken use { text.toInt() }

    protected inline fun <reified U> Parser<U>.eolTerminated(): Parser<U> = this and skip(eolToken)
    protected inline fun <reified U, reified V> separatedPair(
        lhs: Parser<U>,
        sep: Parser<*>,
        rhs: Parser<V>
    ): Parser<Pair<U, V>> =
        (lhs and skip(sep) and rhs) map { Pair(it.t1, it.t2) }
}
