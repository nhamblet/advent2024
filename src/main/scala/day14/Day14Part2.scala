package day14

import scala.io.Source
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.File
import java.io.FileWriter

object Day14Part2 extends App {

    // helped with looking back
    val f = new BufferedWriter(new FileWriter(new File("day14.out")))
  
    // noticed interesting shapes at 12, another 64, and another 37, on cycles of 101. going in 101s found an answer after a while
    def solve(in: Day14Input): Int = {
        var idx = 0
        var state = in.robots
        while (idx < 11) {
            idx = idx + 1
            state = Day14Part1.step(in.gridSize)(state)
        }
        while (true) {
            printState(state, in.gridSize, idx)
            Thread.sleep(700l)
            var fidx = 0
            while (fidx < 101) {
                fidx = fidx + 1
                idx = idx + 1
                state = Day14Part1.step(in.gridSize)(state)
            }
            printState(state, in.gridSize, idx)
            Thread.sleep(700l)
        }
        idx
    }

    def printState(state: Array[PosDelta], gridSize: CR, idx: Int): Unit = {
        println(s"\n\nSteps: $idx")
        f.append(s"\n\nSteps: $idx")
        val rcs = state.map(_.pos).toSet
        for {
            r <- Range(0, gridSize._1 / 2)
            c <- Range(0, gridSize._2)
        } {
            if (c == 0) {
                println("")
                f.newLine()
            }
            val cout = (rcs.contains((2 * r, c)), rcs.contains((2 * r + 1, c))) match {
                case (true, true) => 'O'
                case (true, false) => '^'
                case (false, true) => 'v'
                case (false, false) => '.'
            }
            print(cout)
            f.append(cout)
            f.flush()
        }
    }


    // println("Test: " + solve(Day14Input.test()))
    println("Actual: " + solve(Day14Input.actual()))
}
