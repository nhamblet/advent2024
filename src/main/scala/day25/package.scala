import scala.io.BufferedSource
import scala.io.Source

package object day25 {

  case class Day25Input()

  object Day25Input {
    def apply(src: BufferedSource): Day25Input = {
        ???
    }

    def test(): Day25Input =
        Day25Input(Source.fromResource("day25.test"))

    def actual(): Day25Input =
        Day25Input(Source.fromResource("day25.actual"))
  }
  
}
