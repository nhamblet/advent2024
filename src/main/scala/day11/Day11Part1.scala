package day11

object Day11Part1 extends App {
  
    def solve(in: Day11Input): (Long, Long) = {
        // on 25 blinks, test takes 58 millis, actual takes 101
        //    30                    165                     371
        //    35                    881                    2899
        //    40                   4011                   18574
        time(blinks(in, 25))
    }

    def blinks(in: Day11Input, numTimes: Int): Long = {
        in.stones.map(n => blinks(n, numTimes)).sum
    }

    def blinks(n: Long, numTimes: Int): Long = {
        if (numTimes == 0) {
            1
        } else {
            evolve(n) match {
                case EvolveSingle(nn) => blinks(nn, numTimes - 1)
                case EvolveTwo(l, r) => blinks(l, numTimes - 1) + blinks(r, numTimes - 1)
            }

        }
    }

    println("Test: " + solve(Day11Input.test()))
    println("Actual: " + solve(Day11Input.actual()))
}
