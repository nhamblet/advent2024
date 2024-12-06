package day06

object Day06Part2 extends App {
  
    def solve(in: Day06Input): Int = {
        (for {
            row <- Range(0, in.rows)
            col <- Range(0, in.cols) if !in.map(row)(col) && (row, col) != in.guardPos
        } yield {
            (row, col)
        }).count(rc => doesLoop(in, rc, in.guardPos, in.guardDirection))
    }
    
    def doesLoop(in: Day06Input, extraOb: (Int, Int), pos: (Int, Int), dir: Direction, visited: Set[((Int, Int), Direction)] = Set.empty): Boolean = {
        if (offBoard(pos, in.rows, in.cols)) {
            false
        } else if (visited.contains((pos, dir))) {
            true
        } else {
            val (nextPos, nextDir) = move(in.map, extraOb, pos, dir)
            doesLoop(in, extraOb, nextPos, nextDir, visited + ((pos, dir)))
        }
    }

    def move(map: Array[Array[Boolean]], extraOb: (Int, Int), curPos: (Int, Int), curDir: Direction): ((Int, Int), Direction) = {
        val n = curDir.moveFrom(curPos)
        if (offBoard(n, map.size, map(0).size)) {
            (n, curDir)
        } else if (map(n._1)(n._2) || (n == extraOb)) {
            (curPos, Direction.turnRight(curDir))
        } else {
            (n, curDir)
        }
    }

    println("Test: " + solve(Day06Input.test()))
    println("Actual: " + solve(Day06Input.actual()))
}
