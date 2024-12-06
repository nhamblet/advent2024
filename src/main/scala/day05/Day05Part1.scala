package day05

object Day05Part1 extends App {
  
    def solve(in: Day05Input): Int =
        in.updatePages.filter(validOrder(in.rules)).map(takeMiddle).sum

    println("Test: " + solve(Day05Input.test()))
    println("Actual: " + solve(Day05Input.actual()))
}
