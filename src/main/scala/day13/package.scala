import scala.io.BufferedSource
import scala.io.Source

package object day13 {

    case class Button(incX: Int, incY: Int, cost: Int)
    case class Machine(a: Button, b: Button, prize: (Int, Int))
    case class Day13Input(machines: Array[Machine])

    object Day13Input {
        def apply(src: BufferedSource): Day13Input = {
            Day13Input(src.getLines().grouped(4).map(machine).toArray)
        }

        def machine(lines: Seq[String]): Machine = {
            val a = button(lines(0))
            val b = button(lines(1))
            val p = prize(lines(2))
            Machine(a, b, p)
        }

        def button(line: String): Button = {
            val pieces = line.split(" ")
            val cost = if (pieces(1) == "A:") { 3 } else { 1 }
            Button(pieces(2).slice(2, pieces(2).size - 1).toInt, pieces(3).slice(2, pieces(3).size).toInt, cost)
        }

        def prize(line: String): (Int, Int) = {
            val pieces = line.split(" ")
            (pieces(1).slice(2, pieces(1).size - 1).toInt, pieces(2).slice(2, pieces(2).size).toInt)
        }

        def test(): Day13Input =
            Day13Input(Source.fromResource("day13.test"))

        def actual(): Day13Input =
            Day13Input(Source.fromResource("day13.actual"))
    }
}
