@file:Suppress("UnstableApiUsage")

package aoc.day16

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken
import com.google.common.graph.GraphBuilder

object Day16Grammar : AocGrammar<Scan>() {
    private val valveToken by literalToken("Valve ")
    private val labelToken by regexToken("[A-Z]{2}")
    private val flowRateToken by literalToken("has flow rate=")
    private val tunnelsToken by regexToken("; tunnels? leads? to valves? ")
    private val sepToken by literalToken(", ")

    private val labelParser by labelToken use { Valve(text) }

    private val valveParser by -valveToken and labelParser
    private val flowRateParser by -flowRateToken and numberParser
    private val tunnelsParser by -tunnelsToken and separatedTerms(labelParser, sepToken)
    private val scanLineParser by valveParser * -spaceToken * flowRateParser * tunnelsParser

    override val rootParser by lineList(scanLineParser) map {
        val flowRates = it.associate { (valve, flowRate, _) -> valve to flowRate }
        val tunnelsBuilder = GraphBuilder.directed().immutable<Valve>()
        for ((start, _, ends) in it) {
            for (end in ends) {
                tunnelsBuilder.putEdge(start, end)
            }
        }
        Scan(flowRates, tunnelsBuilder.build())
    }
}

fun parse(data: String): Scan = Day16Grammar.parseToEnd(data)