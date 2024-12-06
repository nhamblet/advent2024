import scala.io.BufferedSource
import scala.io.Source

package object day21 {

  case class Day21Input()

  object Day21Input {
    def apply(src: BufferedSource): Day21Input = {
        ???
    }

    def test(): Day21Input =
        Day21Input(Source.fromResource("day21.test"))

    def actual(): Day21Input =
        Day21Input(Source.fromResource("day21.actual"))
  }
  
}
