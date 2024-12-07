import scala.io.BufferedSource
import scala.io.Source

package object day07 {

  case class Day07Input(equations: List[Equation])
  case class Equation(target: Long, operands: Array[Long])

  object Equation {
    def apply(line: String): Equation = {
      val pieces = line.split(" ")
      val t = pieces(0).stripSuffix(":").toLong
      val os = pieces.slice(1, pieces.size).map(_.toLong)
      Equation(t, os)
    }
  }

  object Day07Input {
    def apply(src: BufferedSource): Day07Input = {
        Day07Input(src.getLines().toList.map(Equation.apply))
    }

    def test(): Day07Input =
        Day07Input(Source.fromResource("day07.test"))

    def actual(): Day07Input =
        Day07Input(Source.fromResource("day07.actual"))
  }
  
}
