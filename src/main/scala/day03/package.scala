import scala.io.BufferedSource
import scala.io.Source

package object day03 {
  case class Day03Input(str: String)

  object Day03Input {
    def apply(src: BufferedSource): Day03Input =
        Day03Input(src.getLines().mkString(""))

    def test(): Day03Input =
        Day03Input(Source.fromResource("day03.test"))

    def actual(): Day03Input =
        Day03Input(Source.fromResource("day03.actual"))
  }
}
