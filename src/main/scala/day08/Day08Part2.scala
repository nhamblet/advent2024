package day08

object Day08Part2 extends App {
  
    def solve(in: Day08Input): Int =
        (for {
            as <- in.antennas.values
            ap <- harmonicAntipoles(as, in.numRows, in.numCols)
        } yield {
            ap
        }).toSet.size

    println("Test: " + solve(Day08Input.test()))
    println("Actual: " + solve(Day08Input.actual()))
}
