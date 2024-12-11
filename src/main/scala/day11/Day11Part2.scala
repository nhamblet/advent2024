package day11

object Day11Part2 extends App {
  
    // was curious to see how much space was used by the memory of this solution
    // the response in this method is ((puzzle answer, memory size), time to compute)
    // with 75 blinks, memory size was only 282, smaller than expected
    def solve(in: Day11Input): ((Long, Int), Long) = {
        // on 25 blinks, test takes 60 millis, actual takes 8
        //    30                    52                      9
        //    35                    57                     25
        //    40                    74                     50
        time(blinks(in, 75))
    }

    // number and numblinks => size after num blinks
    type MEM = Map[(Long, Int), Long]

    def printMem(m: Map[(Long, Int), Long]): Unit = {
        val maxTimes = m.keySet.map(_._2).max
        for {
            n <- Range(0, maxTimes)
        } {
            val ks = m.keySet.filter(_._2 == n).toArray.sorted
            // println(s"Level $n:  " + ks.map(k => (k._1, m(k))).mkString("  |  "))
        }
    }

    def blinks(in: Day11Input, numTimes: Int): (Long, Int) = {
        val ans = in.stones.foldLeft((0l, Map.empty[(Long, Int), Long]))((accmem: (Long, MEM), stone: Long) => {
            val (acc, mem) = accmem
            val (another, newmem) = blinks(stone, numTimes, mem)
            (acc + another, newmem)
        })
        (ans._1, ans._2.size)
    }

    // after running this, the response MEM should have (n, numTimes) in it, unless numTimes == 0
    def blinks(n: Long, numTimes: Int, mem: MEM): (Long, MEM) = {
        if (numTimes == 0) {
            (1, mem)
        } else if (mem.contains((n, numTimes))) {
            (mem((n, numTimes)), mem)
        } else {
            evolve(n) match {
                case EvolveSingle(nn) => {
                    val (ans, newmem) = blinks(nn, numTimes - 1, mem)
                    (ans, newmem + ((n, numTimes) -> ans))
                }
                case EvolveTwo(l, r) => {
                    val (left, lmem) = blinks(l, numTimes - 1, mem)
                    val (right, rmem) = blinks(r, numTimes - 1, lmem)
                    (left + right, rmem + ((n, numTimes) -> (left + right)))
                }
            }

        }
    }

    println("Test: " + solve(Day11Input.test()))
    println("Actual: " + solve(Day11Input.actual()))
}
