package day07

import org.scalatest.funsuite.AnyFunSuite
import day07.Day07Part1.plausible
import day07.Day07Part1.brute
import day07.Day07Part1.bruteExplain

class Day07Part1Tests extends AnyFunSuite {

    test("All the same") {
        val in = Day07Input.actual()
        for {
            eqn <- in.equations
        } {
            val ans = plausible(eqn) == brute(eqn)
            assert(ans)
        }
    }

    // test case identified by checking plausible == brute for all equations
    test("Plausible true for 3597 with 9 5 3 6 4 81 38 1 237 1") {
        val eqn = Equation(3597, Array(9, 5, 3, 6, 4, 81, 38, 1, 237, 1))
        // bruteExplain returns: *, +, +, +, +, *, *, *, * (which, remember, is backwards)
        // so 9 * 5 * 3 * 6 * 4 + 81 + 38 + 1 + 237 * 1 == 3597 (yes)
        assert(plausible(eqn).nonEmpty)
    }
  
}
