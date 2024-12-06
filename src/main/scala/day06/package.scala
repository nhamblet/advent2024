import scala.io.BufferedSource
import scala.io.Source

package object day06 {

  case class Day06Input(map: Array[Array[Boolean]], guardPos: (Int, Int), guardDirection: Direction) {
    val rows = map.size
    val cols = map(0).size
  }

  sealed trait Direction {
    def moveFrom(pos: (Int, Int)): (Int, Int)
  }
  case object Up extends Direction {
    override def moveFrom(pos: (Int, Int)): (Int, Int) =
      (pos._1 - 1, pos._2)
  }
  case object Down extends Direction {
    override def moveFrom(pos: (Int, Int)): (Int, Int) =
      (pos._1 + 1, pos._2)
  }
  case object Left extends Direction {
    override def moveFrom(pos: (Int, Int)): (Int, Int) =
      (pos._1, pos._2 - 1)
  }
  case object Right extends Direction {
    override def moveFrom(pos: (Int, Int)): (Int, Int) =
      (pos._1, pos._2 + 1)
  }

  object Direction {
    def apply(c: Char): Direction =
      c match {
        case '^' => Up
        case 'v' => Down
        case '<' => Left
        case '>' => Right
        case _   => ???
      }
    
    def turnRight(d: Direction): Direction =
      d match {
        case Up => Right
        case Down => Left
        case Left => Up
        case Right => Down
      }
  }

  object Day06Input {
    def apply(src: BufferedSource): Day06Input = {
        val lines = src.getLines().toArray.filter(_.nonEmpty)
        val map = lines.map(line => line.map(c => c == '#').toArray)
        val guardPos = (for {
          row <- Range(0, lines.size)
          col <- Range(0, lines(0).size) if lines(row)(col) != '.' && lines(row)(col) != '#'
        } yield {
          (row, col)
        }).head
        Day06Input(map, guardPos, Direction(lines(guardPos._1)(guardPos._2)))
    }

    def test(): Day06Input =
        Day06Input(Source.fromResource("day06.test"))

    def actual(): Day06Input =
        Day06Input(Source.fromResource("day06.actual"))
  }

  def offBoard(pos: (Int, Int), nr: Int, nc: Int): Boolean =
      pos._1 < 0 || pos._1 >= nr || pos._2 < 0 || pos._2 >= nc
  
}
