package maze

import dsu._

class Maze(val maze: Set[(Int, Int)], val n: Int, val m: Int) {
  private val dsu = new DSU(n * m)

  private def calculate(): Unit = {
    for (i <- maze) {
      val (x, y) = i
      if (maze.contains((x + 1, y))) dsu.union_set(id(x + 1, y), id(x, y))
      if (maze.contains((x - 1, y))) dsu.union_set(id(x - 1, y), id(x, y))
      if (maze.contains((x, y + 1))) dsu.union_set(id(x, y + 1), id(x, y))
      if (maze.contains((x, y - 1))) dsu.union_set(id(x, y - 1), id(x, y))
    }
  }

  def check(enter: (Int, Int), exit: (Int, Int)): Boolean = {
    maze.contains(enter) && maze.contains(exit) && dsu.find_set(id(enter._1, enter._2)) == dsu.find_set(id(exit._1, exit._2))
  }

  var id = (i: Int, j: Int) => j * n + i
}

object Maze {
  def apply(maze: Array[Array[Boolean]]): Maze = {
    var mazeSet: Set[(Int, Int)] = Set.empty
    for (i <- maze.indices) {
      for (j <- maze(i).indices) {
        if (maze(i)(j))
          mazeSet = mazeSet.union(Set((i, j)))
      }
    }
    val m = if (maze.length > 0) new Maze(mazeSet, maze.length, maze(0).length)
    else new Maze(Set.empty, 0, 0)
    m.calculate()
    m
  }

  def apply(maze: Array[Array[Char]], voidCh: Char): Maze = {
    val bMaze = maze.map(i => i.map(_ == voidCh))
    Maze(bMaze)
  }

  def apply(maze: Set[(Int, Int)], n: Int, m: Int): Maze = {
    val rmaze = new Maze(maze, n, m)
    rmaze.calculate()
    rmaze
  }
}
