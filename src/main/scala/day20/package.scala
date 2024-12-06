import scala.io.BufferedSource
import scala.io.Source

package object day20 {

  case class Day20Input()

  object Day20Input {
    def apply(src: BufferedSource): Day20Input = {
        ???
    }

    def test(): Day20Input =
        Day20Input(Source.fromResource("day20.test"))

    def actual(): Day20Input =
        Day20Input(Source.fromResource("day20.actual"))
  }
  
}
