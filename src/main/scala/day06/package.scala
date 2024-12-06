import scala.io.BufferedSource
import scala.io.Source

package object day06 {

  case class Day06Input()

  object Day06Input {
    def apply(src: BufferedSource): Day06Input = {
        ???
    }

    def test(): Day06Input =
        Day06Input(Source.fromResource("day06.test"))

    def actual(): Day06Input =
        Day06Input(Source.fromResource("day06.actual"))
  }
  
}
