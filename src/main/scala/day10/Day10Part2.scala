package day10

object Day10Part2 extends App {
  
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
        def go(height: Int, fringe: Map[(Int, Int), Int]): Int = {
            if (fringe.isEmpty) {
                0
            } else if (height == 9) {
                fringe.values.sum
            } else {
                val steps = for {
                    pos <- fringe.keySet.toList
                    ns <- nextSteps(in)(pos)
                } yield {
                    (pos, ns)
                }
                val nextFringe = steps.groupBy(_._2).map(t => {
                    val (nextPos, stepsToPos) = t
                    nextPos -> (stepsToPos.map(step => fringe(step._1))).sum
                })
                go(height + 1, nextFringe)
            }
        }
        go(0, Map(trailhead -> 1))
    }

    println("Test: " + solve(Day10Input.test()))
    println("Actual: " + solve(Day10Input.actual()))
}
