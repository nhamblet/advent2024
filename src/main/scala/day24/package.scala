import scala.io.BufferedSource
import scala.io.Source

package object day24 {

  case class Day24Input()

  object Day24Input {
    def apply(src: BufferedSource): Day24Input = {
        ???
    }

    def test(): Day24Input =
        Day24Input(Source.fromResource("day24.test"))

    def actual(): Day24Input =
        Day24Input(Source.fromResource("day24.actual"))
  }
  
}
