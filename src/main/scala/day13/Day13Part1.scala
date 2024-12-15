package day13

object Day13Part1 extends App {
  
    def solve(in: Day13Input): Int = {
        in.machines.flatMap(m => solve(m)).sum
    }

    def solve(machine: Machine): Option[Int] = {
        def calcB(a: Int): Option[Int] = {
            val remX = machine.prize._1 - (a * machine.a.incX)
            val remY = machine.prize._2 - (a * machine.a.incY)
            if (remX % machine.b.incX == 0 && remY % machine.b.incY == 0) {
                if ((remX / machine.b.incX) == (remY / machine.b.incY)) {
                    Some(remX / machine.b.incX)
                } else {
                    None
                }
            } else {
                None
            }
        }
        (for {
            a <- Range(0, 101)
            b <- calcB(a)
        } yield {
            3 * a + b
        }).sorted.headOption
    }

    println("Test: " + solve(Day13Input.test()))
    println("Actual: " + solve(Day13Input.actual()))
}
