package day08

object Day08Part1 extends App {
  
    def solve(in: Day08Input): Int =
        (for {
            as <- in.antennas.values
            ap <- antipoles(as) if inBounds(ap, in.numRows, in.numCols)
        } yield {
            ap
        }).toSet.size

    println("Test: " + solve(Day08Input.test()))
    println("Actual: " + solve(Day08Input.actual()))
}
