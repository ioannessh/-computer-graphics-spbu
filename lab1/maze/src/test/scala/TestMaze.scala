import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import maze.Maze

class TestMaze extends AnyFlatSpec with should.Matchers {
  "enter or exit in wall" should "always return false" in {
    val mazeArr = Array(
      Array('#', '.', '.', '.', '.', '#', '.', '.')
    ) // false - wall, true - void, '.' - void, other symbols - wall
    val maze = Maze(mazeArr, '.')
    maze.check((0, 0), (0, 0)) should be(false)
    maze.check((0, 5), (0, 5)) should be(false)
    maze.check((0, 0), (0, 1)) should be(false)
    maze.check((0, 4), (0, 5)) should be(false)
  }

  "empty maze" should "always return false" in {
    val mazeArr: Array[Array[Boolean]] = Array()
    val maze = Maze(mazeArr)
    maze.check((0, 0), (100, 100)) should be(false)
  }

  "if enter or exit outsize maze" should "be returned false" in {
    val mazeArr = Array(Array('.'))
    val maze = Maze(mazeArr, '.')
    maze.check((-1, 0), (0, 0)) should be(false)
    maze.check((0, 1), (0, 0)) should be(false)
    maze.check((0, 0), (1, 0)) should be(false)
    maze.check((0, 0), (0, 4)) should be(false)
  }

  "if path between enter and exit exist" should "return true" in {
    val mazeArr = Array(
      Array('#', '.', '.', '#', '.', '#', '.', '.'),
      Array('.', '#', '.', '.', '.', '#', '.', '#')
    )
    val maze = Maze(mazeArr, '.')

    maze.check((0, 1), (0, 4)) should be(true)
    maze.check((0, 1), (1, 4)) should be(true)
    maze.check((0, 7), (1, 6)) should be(true)
    maze.check((1, 0), (1, 0)) should be(true)
  }

  "if path between enter and exit don't exist" should "return false" in {
    val mazeArr = Array(
      Array('#', '.', '#', '#', '.', '.', '#', '.'),
      Array('.', '#', '.', '.', '.', '#', '.', '#')
    )
    val maze = Maze(mazeArr, '.')

    maze.check((0, 1), (1, 0)) should be(false)
    maze.check((1, 6), (1, 4)) should be(false)
  }

  "test if path exist in big maze" should "return true" in {
    val n = 10
    var mazeSet: Set[(Int, Int)] = Set.empty
    for (i <- 0 until  n) {
      mazeSet = mazeSet.union(Set((0, i)))
    }
    for (i <- 0 until n) {
      mazeSet = mazeSet.union(Set((i, n - 1)))
    }
    val maze = Maze(mazeSet, n, n)

    maze.check((0, 0), (n - 1, n - 1)) should be(true)
    maze.check((0, 0), (n - 1, n - 1)) should be(true)
    maze.check((0, 0), (n - 1, n - 1)) should be(true)
    maze.check((0, 0), (n - 1, n - 1)) should be(true)
  }

  "test if path not exist in big maze" should "return false" in {
    val n = 1000000
    val mazeSet = Set((0, 0), (n - 1, n - 1))
    val maze = Maze(mazeSet, n, n)

    maze.check((0, 0), (n - 1, n - 1)) should be(false)
    maze.check((0, 0), (n - 1, n - 1)) should be(false)
    maze.check((0, 0), (n - 1, n - 1)) should be(false)
    maze.check((0, 0), (n - 1, n - 1)) should be(false)
  }

}
