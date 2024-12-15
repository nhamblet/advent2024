package day13

import org.scalatest.funsuite.AnyFunSuite

class Day13Part1Tests extends AnyFunSuite  {

    test("Solve quick ones after known slow") {
        val m1 = Machine(Button(26,66,3),Button(67,21,1),(12748,12176))
        val p1 = part1(m1)
        val quick = Day13Part2.solve(Day13Part2.DIFF)(m1)
        // quick found 29227295769, 137911795862
        assert(p1 == quick)
    }

    test("Known part 1 matches") {
        // one of the cases where part1 != solve in my Day13Part2 code
        val machine = Machine(Button(57, 21, 3), Button(30, 52, 1), (2841, 3135))
        val p1 = part1(machine)
        val p2 = Day13Part2.solve(0)(machine)
        assert(p1 == p2)
    }

    test("Another") {
        val machine = Machine(Button(19,81,3),Button(91,22,1),(3752,4651)) // yielded (49,31,178) / None
        val p1 = part1(machine)
        val p2 = Day13Part2.solve(0)(machine)
        assert(p1 == p2)
    }

    test("And Another") {
        val machine = Machine(Button(15,41,3),Button(90,53,1),(3420,2593)) // yielded (18,35,89) / None
        val p1 = part1(machine)
        val p2 = Day13Part2.solve(0)(machine)
        assert(p1 == p2)
    }

    test("Last one?") {
        val machine = Machine(Button(27,68,3),Button(96,27,1),(8352,8148)) // yielded (96,60,348) / None
        val p1 = part1(machine)
        val p2 = Day13Part2.solve(0)(machine)
        assert(p1 == p2)
    }

    

    def part1(machine: Machine): Option[(Long, Long)] = {
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
            (a, b, 3 * a + b)
        }).sortBy(_._3).headOption.map(p => (p._1.toLong, p._2.toLong))
    }
}
