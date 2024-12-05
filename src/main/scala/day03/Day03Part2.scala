package day03

import scala.io.Source

object Day03Part2 extends App {

    def solve(in: Day03Input): Int = {
        var idx = 0
        var isDo = true
        var mulR = "mul\\([0-9]{1,3},[0-9]{1,3}\\)".r
        var sum = 0
        while (idx < in.str.size) {
            mulR.findPrefixOf(in.str.substring(idx)) match {
                case Some(m) =>
                    if (isDo) {
                        sum = sum + Day03Part1.doMul(m)
                    }
                    idx = idx + m.size
                case None =>
                    if (in.str.substring(idx).startsWith("don't()")) {
                        isDo = false
                        idx = idx + 7 // "don't()".size
                    } else if (in.str.substring(idx).startsWith("do()")) {
                        isDo = true
                        idx = idx + 4 // "do()".size
                    } else {
                        idx = idx + 1
                    }
            }
        }
        sum
    }

    println("Test: " + solve(Day03Input(Source.fromResource("day03.test2"))))
    println("Actual: " + solve(Day03Input(Source.fromResource("day03.actual"))))
}
