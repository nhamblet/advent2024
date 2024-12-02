package day02

import org.scalatest.funsuite.AnyFunSuite

class Day02Part1Tests extends AnyFunSuite {

    test("Monotonic works") {
        assert(!Day02Part1.monotonic(Day02Part1.diffs(List(1, 3, 2, 4, 5))))
    }

    test("AllSmall works") {
        assert(!Day02Part1.allSmall(Day02Part1.diffs(List(1, 3, 2, 4, 5))))
    }
  
}