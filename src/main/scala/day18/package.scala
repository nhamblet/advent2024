import scala.io.BufferedSource
import scala.io.Source

package object day18 {

  case class Day18Input()

  object Day18Input {
    def apply(src: BufferedSource): Day18Input = {
        ???
    }

    def test(): Day18Input =
        Day18Input(Source.fromResource("day18.test"))

    def actual(): Day18Input =
        Day18Input(Source.fromResource("day18.actual"))
  }
  
}
