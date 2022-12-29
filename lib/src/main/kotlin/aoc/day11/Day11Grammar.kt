package aoc.day11

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken

object Day11Grammar : AocGrammar<List<Monkey>>() {
    private val colonToken by literalToken(":")
    private val commaSpaceToken by literalToken(", ")
    private val plusToken by literalToken("+")
    private val timesToken by literalToken("*")
    private val oldToken by literalToken("old")

    private val monkeyToken by literalToken("Monkey ")
    private val startingItemsToken by literalToken("Starting items: ")
    private val operationToken by literalToken("Operation: new = ")
    private val divisorToken by literalToken("Test: divisible by ")
    private val ifTrueToken by literalToken("If true: throw to monkey ")
    private val ifFalseToken by literalToken("If false: throw to monkey ")

    private val termParser by (oldToken asJust { x: Long -> x }) or (numberParser map { v -> { _ -> v.toLong() } })
    private val operatorParser by (plusToken asJust { a: Long, b: Long -> a + b }) or (timesToken asJust Long::times)

    private val singleIndentParser by spaceToken and spaceToken
    private val doubleIndentParser by singleIndentParser and singleIndentParser

    private val monkeyHeaderParser by surrounded(monkeyToken, numberParser, colonToken)
    private val startingItemsParser by -startingItemsToken and separatedTerms(
        numberParser map (Int::toLong),
        commaSpaceToken
    )
    private val operationParser by -operationToken and
            termParser * -spaceToken * operatorParser * -spaceToken * termParser map
            { (getLhs, op, getRhs) -> { x: Long -> op(getLhs(x), getRhs(x)) } }
    private val divisorParser by -divisorToken and numberParser
    private val ifTrueParser by -ifTrueToken and numberParser
    private val ifFalseParser by -ifFalseToken and numberParser

    private val monkeyParser by (-(monkeyHeaderParser and eolToken) and
            surrounded(singleIndentParser, startingItemsParser, eolToken) and
            surrounded(singleIndentParser, operationParser, eolToken) and
            surrounded(singleIndentParser, divisorParser, eolToken) and
            surrounded(doubleIndentParser, ifTrueParser, eolToken) and
            surrounded(doubleIndentParser, ifFalseParser, eolToken)) map
            { (startingItems, operation, divisor, ifTrue, ifFalse) ->
                Monkey(startingItems, operation, divisor, ifTrue, ifFalse)
            }

    override val rootParser by separatedTerms(monkeyParser, eolToken)
}

fun parse(data: String): List<Monkey> = Day11Grammar.parseToEnd(data)