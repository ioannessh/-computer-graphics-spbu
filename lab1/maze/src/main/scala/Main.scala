import maze.Maze

object Main {
  def main(args: Array[String]): Unit = {
    val m = Array(
      Array('#', '.', '.', '.', '.', '#', '.', '.'),
      Array('.', '#', '.', '.', '.', '#', '.', '#')
    ) // false - wall, '.' - void
    val maze = Maze(m)

    /*for (i <- m.indices) {
      for (j <- m(i).indices) {
        print(f"${maze.dsu.find_set(maze.id(i, j))} ")
      }
      println()
    }*/
  }
}
