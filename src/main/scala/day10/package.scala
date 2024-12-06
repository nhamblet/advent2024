import scala.io.BufferedSource
import scala.io.Source

package object day10 {

  case class Day10Input()

  object Day10Input {
    def apply(src: BufferedSource): Day10Input = {
        ???
    }

    def test(): Day10Input =
        Day10Input(Source.fromResource("day10.test"))

    def actual(): Day10Input =
        Day10Input(Source.fromResource("day10.actual"))
  }
  
}
