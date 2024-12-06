import scala.io.BufferedSource
import scala.io.Source

package object day19 {

  case class Day19Input()

  object Day19Input {
    def apply(src: BufferedSource): Day19Input = {
        ???
    }

    def test(): Day19Input =
        Day19Input(Source.fromResource("day19.test"))

    def actual(): Day19Input =
        Day19Input(Source.fromResource("day19.actual"))
  }
  
}
