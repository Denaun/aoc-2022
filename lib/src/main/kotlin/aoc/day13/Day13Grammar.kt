package aoc.day13

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.combinators.separatedTerms
import com.github.h0tk3y.betterParse.combinators.times
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.grammar.parser
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.parser.Parser

object Day13Grammar : AocGrammar<List<PacketPair>>() {
    private val listStartToken by literalToken("[")
    private val listEndToken by literalToken("]")
    private val listSepToken by literalToken(",")

    private val integerParser by numberParser.map(::PacketInteger)
    private val listParser by surrounded(
        listStartToken,
        separatedTerms(parser(this::packetParser), listSepToken, acceptZero = true),
        listEndToken,
    ).map(::PacketList)
    private val packetParser: Parser<PacketData> by integerParser or listParser

    private val packetPairParser by separatedPair(
        packetParser,
        eolToken,
        packetParser,
        ::PacketPair,
    )

    override val rootParser by separatedTerms(packetPairParser, eolToken * eolToken).eolTerminated()
}

fun parse(data: String): List<PacketPair> = Day13Grammar.parseToEnd(data)