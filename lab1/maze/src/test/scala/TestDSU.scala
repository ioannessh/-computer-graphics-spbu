import dsu.DSU
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class TestDSU extends AnyFlatSpec with should.Matchers {
  "set" should "be inicialized" in {
    val dsu = new DSU(10)
    for (i <- (0 until 10))
      dsu.find_set(i) should be(i)
  }

  "difference set" should "return difference set id" in {
    val dsu = new DSU(10)
    dsu.union_set(1, 3)
    dsu.union_set(1, 5)
    dsu.union_set(6, 1)
    dsu.union_set(4, 8)
    dsu.union_set(8, 9)
    val first_set = Array(1, 3, 5, 6)
    val second_set = Array(4, 8, 9)
    for (i <- first_set) {
      for (j <- second_set) {
        (dsu.find_set(i) == dsu.find_set(j)) should be(false)
        first_set.contains(dsu.find_set(i)) should be(true)
        second_set.contains(dsu.find_set(j)) should be(true)
      }
    }
  }

  "union set" should "return equals set with find_set" in {
    val dsu = new DSU(10)
    dsu.union_set(1, 3)
    dsu.union_set(1, 5)
    dsu.union_set(6, 1)
    dsu.union_set(4, 8)
    dsu.union_set(8, 9)
    dsu.union_set(5, 4)
    val unioned_nodes = Array(1, 3, 5, 6, 4, 8, 9)
    for (i <- unioned_nodes) {
      for (j <- unioned_nodes) {
        (dsu.find_set(i) == dsu.find_set(j)) should be(true)
      }
    }
  }
}
