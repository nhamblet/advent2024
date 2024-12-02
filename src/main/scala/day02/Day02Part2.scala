package day02

import scala.io.Source

object Day02Part2 extends App {

    def solve(in: Day02Input): Int = {
        in.lines.count(safe)
    }

    def safe(line: List[Int]): Boolean = {
        if (Day02Part1.safe(line)) {
            true
        } else {
            val ar = line.toArray
            Range(0, ar.size).exists(idx => {
                Day02Part1.safe((ar.slice(0, idx) ++ ar.slice(idx + 1, ar.size)).toList)
            })
        }
    }

    println("Test: " + solve(Day02Input(Source.fromResource("day02.test"))))
    println("Actual: " + solve(Day02Input(Source.fromResource("day02.actual"))))
}
