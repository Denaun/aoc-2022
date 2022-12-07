package aoc.day07

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken
import com.github.h0tk3y.betterParse.parser.Parser

object Day07Grammar : AocGrammar<List<Command>>() {
    private val commandToken by literalToken("$ ")
    private val cdToken by literalToken("cd ")
    private val lsToken by literalToken("ls")

    private val parentToken by literalToken("..")
    private val dirToken by literalToken("dir")
    private val nameToken by regexToken("[a-z./]+")

    private val nameParser by nameToken use { text }

    private val cdOutParser: Parser<Command> by (commandToken and cdToken and parentToken) asJust CdOut
    private val cdInParser: Parser<Command> by (-commandToken and -cdToken and nameParser) map {
        CdIn(it)
    }

    private val fileParser: Parser<Element> by (numberParser and -spaceToken and nameParser) map {
        File(it.t2, it.t1)
    }
    private val dirParser: Parser<Element> by (-dirToken and -spaceToken and nameParser) map {
        Dir(it)
    }

    private val contentsParser by separatedTerms(fileParser or dirParser, eolToken)
    private val lsParser: Parser<Command> by (-commandToken and -lsToken and -eolToken and contentsParser) map {
        Ls(it)
    }

    override val rootParser by lineList(cdOutParser or cdInParser or lsParser)
}

fun parse(data: String) = Day07Grammar.parseToEnd(data)