import scala.io.BufferedSource
import scala.io.Source

package object day11 {

  case class Day11Input()

  object Day11Input {
    def apply(src: BufferedSource): Day11Input = {
        ???
    }

    def test(): Day11Input =
        Day11Input(Source.fromResource("day11.test"))

    def actual(): Day11Input =
        Day11Input(Source.fromResource("day11.actual"))
  }
  
}
