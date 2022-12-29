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
    private val testToken by literalToken("Test: divisible by ")
    private val ifTrueToken by literalToken("If true: throw to monkey ")
    private val ifFalseToken by literalToken("If false: throw to monkey ")

    private val termParser by (oldToken asJust { x: Int -> x }) or (numberParser map { v -> { _ -> v } })
    private val operatorParser by (plusToken asJust { a: Int, b: Int -> a + b }) or (timesToken asJust Int::times)

    private val singleIndentParser by spaceToken and spaceToken
    private val doubleIndentParser by singleIndentParser and singleIndentParser

    private val monkeyHeaderParser by surrounded(monkeyToken, numberParser, colonToken)
    private val startingItemsParser by -startingItemsToken and separatedTerms(
        numberParser,
        commaSpaceToken
    )
    private val operationParser by -operationToken and
            termParser * -spaceToken * operatorParser * -spaceToken * termParser map
            { (getLhs, op, getRhs) -> { x: Int -> op(getLhs(x), getRhs(x)) } }
    private val testParser by -testToken and numberParser map { n -> { x: Int -> (x % n) == 0 } }
    private val ifTrueParser by -ifTrueToken and numberParser
    private val ifFalseParser by -ifFalseToken and numberParser

    private val monkeyParser by (-(monkeyHeaderParser and eolToken) and
            surrounded(singleIndentParser, startingItemsParser, eolToken) and
            surrounded(singleIndentParser, operationParser, eolToken) and
            surrounded(singleIndentParser, testParser, eolToken) and
            surrounded(doubleIndentParser, ifTrueParser, eolToken) and
            surrounded(doubleIndentParser, ifFalseParser, eolToken)) map
            { (startingItems, operation, test, ifTrue, ifFalse) ->
                Monkey(startingItems, operation, test, ifTrue, ifFalse)
            }

    override val rootParser by separatedTerms(monkeyParser, eolToken)
}

fun parse(data: String): List<Monkey> = Day11Grammar.parseToEnd(data)