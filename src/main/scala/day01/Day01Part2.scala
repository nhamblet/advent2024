package day01

import scala.io.Source

object Day01Part2 extends App {
  
    def solve(in: Day01Input): Int = {
        (for {
            l <- in.left
            r = in.right.count(_ == l)
        } yield {
            l * r
        }).sum
    }

    println("Test: " + solve(Day01Input(Source.fromResource("day01.test").getLines.toList)))
    println("Actual: " + solve(Day01Input(Source.fromResource("day01.actual").getLines.toList)))
}
