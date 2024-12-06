import scala.io.BufferedSource
import scala.io.Source

package object day15 {

  case class Day15Input()

  object Day15Input {
    def apply(src: BufferedSource): Day15Input = {
        ???
    }

    def test(): Day15Input =
        Day15Input(Source.fromResource("day15.test"))

    def actual(): Day15Input =
        Day15Input(Source.fromResource("day15.actual"))
  }
  
}
