import scala.io.BufferedSource
import scala.io.Source

package object day04 {
  
  case class Day04Input(grid: Array[Array[Char]])

  object Day04Input {
    def apply(src: BufferedSource): Day04Input =
        Day04Input(src.getLines().toArray.filter(_.nonEmpty).map(s => s.toCharArray()))

    def test(): Day04Input =
        Day04Input(Source.fromResource("day04.test"))

    def actual(): Day04Input =
        Day04Input(Source.fromResource("day04.actual"))
  }
}
