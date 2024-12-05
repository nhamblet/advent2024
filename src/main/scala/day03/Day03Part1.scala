package day03

import scala.io.Source
import scala.io.BufferedSource
import scala.util.matching.Regex

object Day03Part1 extends App {

    def solve(in: Day03Input): Int = {
        val re = "mul\\([0-9]{1,3},[0-9]{1,3}\\)".r
        val ms = re.findAllIn(in.str)
        ms.map(doMul).sum
    }

    def doMul(str: String): Int = {
        val pieces = str.substring("mul(".size, str.size - 1).split(",")
        pieces(0).toInt * pieces(1).toInt
    }

    println("Test: " + solve(Day03Input.test()))
    println("Actual: " + solve(Day03Input.actual()))
}
