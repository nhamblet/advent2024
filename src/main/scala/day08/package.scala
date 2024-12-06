import scala.io.BufferedSource
import scala.io.Source

package object day08 {

  case class Day08Input()

  object Day08Input {
    def apply(src: BufferedSource): Day08Input = {
        ???
    }

    def test(): Day08Input =
        Day08Input(Source.fromResource("day08.test"))

    def actual(): Day08Input =
        Day08Input(Source.fromResource("day08.actual"))
  }
  
}
