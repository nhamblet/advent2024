import scala.io.BufferedSource
import scala.io.Source

package object day08 {

  case class Day08Input(numRows: Int, numCols: Int, antennas: Map[Char, Set[(Int, Int)]])

  object Day08Input {
    def apply(src: BufferedSource): Day08Input = {
        val lines = src.getLines().toArray.filter(_.nonEmpty)
        val numRows = lines.size
        val numCols = lines(0).size
        val coords = for {
          r <- Range(0, numRows)
          c <- Range(0, numCols) if lines(r)(c) != '.'
        } yield {
          (lines(r)(c), (r, c))
        }
        val ants = coords.groupBy(_._1).mapValues(_.map(_._2).toSet)
        Day08Input(numRows, numCols, ants)
    }

    def test(): Day08Input =
        Day08Input(Source.fromResource("day08.test"))

    def actual(): Day08Input =
        Day08Input(Source.fromResource("day08.actual"))
  }

  def antipoles(as: Set[(Int, Int)]): Set[(Int, Int)] =
      for {
          a1 <- as
          a2 <- as if a1 != a2
          ap <- antipoles(a1, a2)
      } yield {
          ap
      }

  def antipoles(a1: (Int, Int), a2: (Int, Int)): Set[(Int, Int)] = {
      def ap(from: (Int, Int), to: (Int, Int)): (Int, Int) = {
          (to._1 + (to._1 - from._1), to._2 + (to._2 - from._2))
      }
      Set(ap(a1, a2), ap(a2, a1))
  }

  def inBounds(coord: (Int, Int), numRows: Int, numCols: Int) =
      coord._1 >= 0 && coord._1 < numRows && coord._2 >= 0 && coord._2 < numCols
    
  def harmonicAntipoles(as: Set[(Int, Int)], numRows: Int, numCols: Int): Set[(Int, Int)] =
      for {
          a1 <- as
          a2 <- as if a1 != a2
          ap <- harmonicAntipoles(a1, a2, numRows, numCols)
      } yield {
          ap
      }

  def harmonicAntipoles(a1: (Int, Int), a2: (Int, Int), numRows: Int, numCols: Int): Set[(Int, Int)] = {
    val dr = a2._1 - a1._1
    val dc = a2._2 - a1._2
    val outs = Stream.from(0).map(n => (a1._1 + n * dr, a1._2 + n * dc)).takeWhile(coord => inBounds(coord, numRows, numCols)).toSet
    val backs = Stream.from(0).map(n => (a1._1 - n * dr, a1._2 - n * dc)).takeWhile(coord => inBounds(coord, numRows, numCols)).toSet
    outs ++ backs
  }
  
}
