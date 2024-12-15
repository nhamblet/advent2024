import scala.io.BufferedSource
import scala.io.Source

package object day14 {

  type CR = (Int, Int)
  case class PosDelta(pos: CR, delta: CR) {
    def next(gridSize: CR): PosDelta = {
      var nx = (pos._1 + delta._1) % (gridSize._1)
      var ny = (pos._2 + delta._2) % (gridSize._2)
      while (nx < 0) {
        nx = nx + gridSize._1
      }
      while (ny < 0) {
        ny = ny + gridSize._2
      }
      this.copy(pos = (nx, ny))
    }
  }
  case class Day14Input(robots: Array[PosDelta], gridSize: CR)

  object Day14Input {
    def apply(src: BufferedSource, gridSize: CR): Day14Input = {
        Day14Input(src.getLines().toArray.filter(_.nonEmpty).map(posDelta), gridSize)
    }

    def posDelta(line: String): PosDelta = {
      val pieces = line.split(Array[Char]('=', ',', ' '))
      PosDelta((pieces(1).toInt, pieces(2).toInt), (pieces(4).toInt, pieces(5).toInt))
    }

    def test(): Day14Input =
        Day14Input(Source.fromResource("day14.test"), (11, 7))

    def actual(): Day14Input =
        Day14Input(Source.fromResource("day14.actual"), (101, 103))
  }
  
}
