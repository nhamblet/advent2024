import scala.io.BufferedSource
import scala.io.Source

package object day05 {

  case class Day05Input(rules: Array[(Int, Int)], updatePages: Array[Array[Int]])

  object Day05Input {
    def apply(src: BufferedSource): Day05Input = {
        val lines = src.getLines.toArray
        val ruleLines = lines.takeWhile(_.nonEmpty)
        val rules = ruleLines.map(l => {
            val p = l.splitAt(l.indexOf('|'))
            (p._1.toInt, p._2.drop(1).toInt)
        })
        val updateLines = lines.slice(ruleLines.size + 1, lines.size)
        val updates = updateLines.map(_.split(",").map(_.toInt))
        Day05Input(rules, updates)
    }

    def test(): Day05Input =
        Day05Input(Source.fromResource("day05.test"))

    def actual(): Day05Input =
        Day05Input(Source.fromResource("day05.actual"))
  }

    def validOrder(rules: Array[(Int, Int)])(pages: Array[Int]): Boolean = {
        !(for {
            leftIdx <- Range(0, pages.size - 1)
            rightIdx <- Range(leftIdx + 1, pages.size)
            left = pages(leftIdx)
            right = pages(rightIdx)
        } yield {
            rules.exists(p => p._1 == right && p._2 == left)
        }).exists(b => b)
    }

    def takeMiddle(ar: Array[Int]): Int =
        ar(ar.size / 2)

}