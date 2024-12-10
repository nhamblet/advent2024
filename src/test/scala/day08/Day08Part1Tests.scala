package day08

import org.scalatest.funsuite.AnyFunSuite

class Day08Part1Tests extends AnyFunSuite  {
  
    test("antipoles seems ok") {
        val actual = antipoles((3, 4), (5, 5))
        assert(actual.size == 2)
        assert(actual.contains((1, 3)))
        assert(actual.contains((7, 6)))
    }

    test("more antipoles seems ok") {
        val actual = antipoles(Set((3, 4), (5, 5), (4, 8)))
        assert(actual.size == 6)
        assert(actual.contains((1, 3)))
        assert(actual.contains((7, 6)))
        assert(actual.contains((2, 0)))
        assert(actual.contains((6, 2)))
    }

    test("parser seems ok") {
        val in = Day08Input.test()
        assert(in.numRows == 12)
        assert(in.numCols == 12)
        assert(in.antennas.size == 2)
        assert(in.antennas('A').size == 3)
        assert(in.antennas('0').size == 4)
    }
}
