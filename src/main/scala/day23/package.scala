import scala.io.BufferedSource
import scala.io.Source

package object day23 {

  case class Day23Input()

  object Day23Input {
    def apply(src: BufferedSource): Day23Input = {
        ???
    }

    def test(): Day23Input =
        Day23Input(Source.fromResource("day23.test"))

    def actual(): Day23Input =
        Day23Input(Source.fromResource("day23.actual"))
  }
  
}
