import scala.io.BufferedSource
import scala.io.Source

package object day14 {

  case class Day14Input()

  object Day14Input {
    def apply(src: BufferedSource): Day14Input = {
        ???
    }

    def test(): Day14Input =
        Day14Input(Source.fromResource("day14.test"))

    def actual(): Day14Input =
        Day14Input(Source.fromResource("day14.actual"))
  }
  
}
