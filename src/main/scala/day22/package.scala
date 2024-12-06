import scala.io.BufferedSource
import scala.io.Source

package object day22 {

  case class Day22Input()

  object Day22Input {
    def apply(src: BufferedSource): Day22Input = {
        ???
    }

    def test(): Day22Input =
        Day22Input(Source.fromResource("day22.test"))

    def actual(): Day22Input =
        Day22Input(Source.fromResource("day22.actual"))
  }
  
}
