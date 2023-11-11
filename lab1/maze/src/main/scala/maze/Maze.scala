package maze

import dsu._

class Maze(val maze: Array[Array[Boolean]], val n: Int) {
  private val dsu = new DSU(n)

  private def calculate(): Unit = {
    for (i <- maze.indices) {
      maze(i).foldLeft((-1, false))((prev, it) => {
        if (it && prev._2) dsu.union_set(id(i, prev._1 + 1), id(i, prev._1))
        (prev._1 + 1, it)
      })
    }
    if (maze.length > 0) maze.foldLeft((-1, Array.fill(maze(0).length)(false)))((prev, it) => {
      for (i <- prev._2.indices) {
        if (it(i) && prev._2(i)) dsu.union_set(id(prev._1, i), id(prev._1 + 1, i))
      }
      (prev._1 + 1, it)
    })
  }

  def check(enter: (Int, Int), exit: (Int, Int)): Boolean = {
    if (enter._1 < 0 || enter._2 < 0 || exit._1 < 0 || exit._2 < 0 ||
        maze.length <= enter._1 || maze(0).length <= enter._2 || maze.length <= exit._1 || maze(0).length <= exit._2)
      false
    else
      maze(enter._1)(enter._2) && maze(exit._1)(exit._2) &&
        (dsu.find_set(id(enter._1, enter._2)) == dsu.find_set(id(exit._1, exit._2)))
  }

  var id = (i: Int, j: Int) => j * maze.length + i
}

object Maze {
  def apply(maze: Array[Array[Boolean]]): Maze = {
    val m = if (maze.length > 0) new Maze(maze, maze.length * maze(0).length)
    else new Maze(maze, 0)
    m.calculate()
    m
  }

  def apply(maze: Array[Array[Boolean]], n: Int): Maze = {
    val m = new Maze(maze, n)
    m.calculate()
    m
  }

  def apply(maze: Array[Array[Char]]): Maze = {
    val bMaze = new Array[Array[Boolean]](maze.length)
    for (i <- bMaze.indices) bMaze(i) = maze(i).map(_ == '.')
    Maze(bMaze)
  }
}
