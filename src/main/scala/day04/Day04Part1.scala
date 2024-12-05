package day04

object Day04Part1 extends App {

    def solve(in: Day04Input): Int = {
        (for {
            row <- Range(0, in.grid.size)
            col <- Range(0, in.grid(0).size)
        } yield {
            countFrom(in, row, col)
        }).sum
    }

    val diffs = List(
        (1, 0), // down
        (-1, 0), // up
        (0, 1), // right
        (0, -1), // left
        (-1, 1), // up right
        (1, 1), // down right
        (1, -1), // down left
        (-1, -1), // up left
    )

    def countFrom(in: Day04Input, row: Int, col: Int): Int = {
        diffs.count(d => {
            extract(in, row, col, d._1, d._2) == "XMAS"
        })
    }

    def extract(in: Day04Input, row: Int, col: Int, drow: Int, dcol: Int): String = {
        var str = "" + in.grid(row)(col)
        for {
            d <- Range(1, 4) // "XMAS".size
            r = row + (drow * d) if r >= 0 && r < in.grid.size
            c = col + (dcol * d) if c >= 0 && c < in.grid(0).size
        } {
            str = str + in.grid(r)(c)
        }
        str
    }

    println("Test: " + solve(Day04Input.test()))
    println("Actual: " + solve(Day04Input.actual()))
}
