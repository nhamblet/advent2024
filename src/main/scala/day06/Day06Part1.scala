package day06

object Day06Part1 extends App {
  
    def solve(in: Day06Input): Int = {
        def g(visited: Set[(Int, Int)], pos: (Int, Int), dir: Direction): Int = {
            if (offBoard(pos, in.rows, in.cols)) {
                visited.size
            } else {
                val (nextPos, nextDir) = move(in.map, pos, dir)
                g(visited + pos, nextPos, nextDir)
            }
        }
        g(Set.empty[(Int, Int)], in.guardPos, in.guardDirection)
    }
  
    def move(map: Array[Array[Boolean]], curPos: (Int, Int), curDir: Direction): ((Int, Int), Direction) = {
        val n = curDir.moveFrom(curPos)
        if (offBoard(n, map.size, map(0).size) || !map(n._1)(n._2)) {
            (n, curDir)
        } else {
            move(map, curPos, Direction.turnRight(curDir))
        }
    }

    println("Test: " + solve(Day06Input.test()))
    println("Actual: " + solve(Day06Input.actual()))
}
