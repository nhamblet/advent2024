package day04

object Day04Part2 extends App {

    def solve(in: Day04Input): Int = {
        (for {
            row <- Range(1, in.grid.size - 1)
            col <- Range(1, in.grid(0).size - 1)
        } yield {
            countFrom(in, row, col)
        }).sum
    }

    def countFrom(in: Day04Input, row: Int, col: Int): Int = {
        if (in.grid(row)(col) == 'A') {
            val down = isMS(in.grid(row - 1)(col - 1), in.grid(row + 1)(col + 1))
            val up = isMS(in.grid(row + 1)(col - 1), in.grid(row - 1)(col + 1))
            if (down && up) {
                1
            } else {
                0
            }
        } else {
            0
        }
    }

    def isMS(c1: Char, c2: Char): Boolean = {
        (c1, c2) match {
            case ('M', 'S') => true
            case ('S', 'M') => true
            case _          => false
        }
    }

    println("Test: " + solve(Day04Input.test()))
    println("Actual: " + solve(Day04Input.actual()))
}
