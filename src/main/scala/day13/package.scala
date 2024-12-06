import scala.io.BufferedSource
import scala.io.Source

package object day13 {

  case class Day13Input()

  object Day13Input {
    def apply(src: BufferedSource): Day13Input = {
        ???
    }

    def test(): Day13Input =
        Day13Input(Source.fromResource("day13.test"))

    def actual(): Day13Input =
        Day13Input(Source.fromResource("day13.actual"))
  }
  
}
