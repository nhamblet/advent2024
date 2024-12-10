package day10

object Day10Part1 extends App {
  
    def solve(in: Day10Input): Int = {
        val trailheads = for {
            r <- Range(0, in.heights.size)
            c <- Range(0, in.heights(0).size) if in.heights(r)(c) == 0
        } yield {
            (r, c)
        }
        trailheads.map(score(in)).sum
    }

    def score(in: Day10Input)(trailhead: (Int, Int)): Int = {
        def go(height: Int, fringe: Set[(Int, Int)]): Int = {
            if (fringe.isEmpty) {
                0
            } else if (height == 9) {
                fringe.size
            } else {
                go(height + 1, fringe.flatMap(nextSteps(in)))
            }
        }
        go(0, Set(trailhead))
    }

    println("Test: " + solve(Day10Input.test()))
    println("Actual: " + solve(Day10Input.actual()))
}
