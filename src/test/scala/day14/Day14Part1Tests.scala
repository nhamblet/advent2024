package day14

import org.scalatest.funsuite.AnyFunSuite

class Day14Part1Tests extends AnyFunSuite  {
  
    test("First few steps is ok") {
        val r = PosDelta((2, 4), (2, -3))
        val gs = (11, 7)
        val s1 = r.next(gs)
        assert(s1 == PosDelta((4, 1), (2, -3)))
        val s2 = s1.next(gs)
        assert(s2 == PosDelta((6, 5), (2, -3)))
        val s3 = s2.next(gs)
        assert(s3 == PosDelta((8, 2), (2, -3)))
        val s4 = s3.next(gs)
        assert(s4 == PosDelta((10, 6), (2, -3)))
        val s5 = s4.next(gs)
        assert(s5 == PosDelta((1, 3), (2, -3)))
    }
}
