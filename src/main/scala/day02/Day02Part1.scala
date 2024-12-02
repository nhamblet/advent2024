package day02

import scala.io.Source
import scala.io.BufferedSource

object Day02Part1 extends App {

    def solve(in: Day02Input): Int = {
        in.lines.count(safe)
    }

    def safe(line: List[Int]): Boolean = {
        val ds = diffs(line)
        allSmall(ds) && monotonic(ds)
    }

    def diffs(line: List[Int]): List[Int] =
        line.sliding(2).map(pair => pair.head - pair.tail.head).toList

    def allSmall(diffs: List[Int]): Boolean =
        diffs.forall(d => Math.abs(d) >= 1 && Math.abs(d) <= 3)
    
    def monotonic(diffs: List[Int]): Boolean =
        diffs.forall(d => (diffs.head > 0 && d > 0) || (diffs.head < 0 && d < 0))

    println("Test: " + solve(Day02Input(Source.fromResource("day02.test"))))
    println("Actual: " + solve(Day02Input(Source.fromResource("day02.actual"))))
}

case class Day02Input(lines: List[List[Int]])

object Day02Input {
    def apply(source: BufferedSource): Day02Input = {
        Day02Input(source.getLines.toList.map(l => {
            l.split(" ").map(_.toInt).toList
        }).filter(_.nonEmpty))
    }
}