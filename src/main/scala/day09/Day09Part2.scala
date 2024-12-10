package day09

import java.util.ArrayList

object Day09Part2 extends App {
  
    def solve(in: Day09Input): Long = {
        var ret = 0l
        var mgaps = new ArrayList[Gap]()
        // zip with an extra gap below, because otherwise the final file is dropped
        val fs = in.files.zip(in.gaps :+ 0).zipWithIndex.foldLeft((0, List.empty[File]))((b: (Int, List[File]), z: ((Int, Int), Int)) => {
            val (pos, fs) = b
            val ((f, g), i) = z
            mgaps.add(new Gap(pos + f, g))
            (pos + f + g, File(i, f, pos) :: fs)
        })._2
        // fs is now, conveniently, starting from the rightmost files
        for {
            file <- fs
        } {
            var mgi = 0
            // initial solution missed the "mgi < file.idx" part of this test and the next one
            while (mgi < mgaps.size && mgi < file.idx && mgaps.get(mgi).size < file.size) {
                mgi = mgi + 1
            }
            val start =
                if (mgi < mgaps.size && mgi < file.idx) {
                    // println(s"Putting $file at gap index $mgi, ${mgaps.get(mgi)}")
                    val s = mgaps.get(mgi).pos
                    mgaps.get(mgi).fill(file.size)
                    s
                } else {
                    // println(s"Leaving $file at its start")
                    file.origPos
                }
            for {
                b <- Range(0, file.size)
            } {
                ret = ret + (file.idx * (start + b))
            }
            // println(s"Ans is now $ret")
        }
        ret
    }

    println("Test: " + solve(Day09Input.test()))
    println("Actual: " + solve(Day09Input.actual()))
    // 8800549860065 too high
}

case class File(idx: Int, size: Int, origPos: Int)

class Gap(var pos: Int, var size: Int) {
    def fill(n: Int) = {
        pos += n
        size -= n
    }

    override def toString(): String = {
        s"Gap($pos, $size)"
    }
}