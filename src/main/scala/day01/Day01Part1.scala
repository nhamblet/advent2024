package day01

import java.util.HashMap
import scala.io.Source

object Day01Part1 extends App {

    def solve(in: Day01Input): Int = {
        val left = in.left.sorted
        val right = in.right.sorted
        (for {
            lr <- left.zip(right)
        } yield {
            Math.abs(lr._1 - lr._2)
        }).sum
    }

    println("Test: " + solve(Day01Input(Source.fromResource("day01.test").getLines.toList)))
    println("Actual: " + solve(Day01Input(Source.fromResource("day01.actual").getLines.toList)))
}

case class Day01Input(left: List[Int], right: List[Int])

object Day01Input {
    def apply(lines: List[String]): Day01Input = {
        def splitLine(l: String): (Int, Int) = {
            val pieces = l.split(" ").toVector
            (pieces(0).toInt, pieces(pieces.size-1).toInt)
        }
        val lists = lines.map(splitLine).unzip
        Day01Input(lists._1, lists._2)
    }
}